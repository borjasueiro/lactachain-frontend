package com.fic.muei.lactachain.network

import android.util.Log
import com.fic.muei.lactachain.model.*
import com.fic.muei.lactachain.model.exceptions.*
import com.fic.muei.lactachain.network.model.*
import com.fic.muei.lactachain.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LactachainRepositoryImpl @Inject constructor(
    private val lactachainService : LactachainService,
    private val farmMapper: Mapper<FarmDto, FarmData>,
    private val transporterMapper: Mapper<TransporterDto, TransporterData>,
    private val transportMapper: Mapper<TransportDto, TransportData>,
    private val transportListMapper: Mapper<TransportListDto, TransportListData>,
    private val milkCollectionMapper: Mapper<MilkCollectionDto, MilkCollectionData>,
    private val milkDeliveryMapper: Mapper<MilkDeliveryDto, MilkDeliveryData>,
    private val receptionSiloMapper: Mapper<ReceptionSiloDto, ReceptionSiloData>,
    private val finalSiloMapper: Mapper<FinalSiloDto, FinalSiloData>,
    private val lactachainAuth: LactachainAuth
):LactachainRepository {
    override fun getFarm(code: Int): Flow<Result<FarmData>> {
        return flow {
            try {
                val farm = lactachainService
                    .getFarmInfo(code)
                val result = Result.Success(farmMapper.mapFromDto(farm))
                emit(result)
            } catch (e: Exception) {
                if ("404" in e.message.toString()) {
                    emit(Result.Error(FarmException("Farm not exist.")))
                } else {
                    emit(Result.Error(LactachainException(e.message)))
                }
            }
        }
    }

    override fun setCredentials(user: String, password: String) {
        lactachainAuth.setCredentials(user, password)
    }

    override fun getTransporter(nif: String): Flow<Result<TransporterData?>> {
        return flow {
            try {
                val response = lactachainService
                    .getTransporterInfo(nif)
                val results = response.results
                if (results.isNotEmpty()) {
                    emit(Result.Success(transporterMapper.mapFromDto(results[0])))
                } else {
                    emit(Result.Success(null))
                }

            } catch (e: Exception) {
                if ("401" in e.message.toString()) {
                    emit(Result.Error(LoginException("Not valid credentials")))
                } else {
                    emit(Result.Error(LactachainException(e.message)))
                }
            }
        }
    }

    override fun getTransportByTransporter(code: Int): Flow<Result<List<TransportListData>>> {
        return flow {
            val response = lactachainService
                .getTransportsByTransporter(code)
            val results = response.results
            emit(Result.Success(transportListMapper.mapFromDtoList(results)))
        }
    }

    override fun addTransport(transport: TransportData): Flow<Result<String>> {
        return flow {
            try {
                lactachainService
                    .addTransport(transportMapper.mapToDto(transport))
                emit(Result.Success("Transport added."))
            } catch (e: Exception) {
                if ("400" in e.message.toString()) {
                    emit(Result.Error(FarmException("A field is required.")))
                } else {
                    emit(Result.Error(LactachainException(e.message)))
                }
            }
        }
    }

    override fun addMilkCollection(milkCollection: MilkCollectionData): Flow<Result<Int>> {
        return flow {
            try {
                val milkCollectionDto = lactachainService
                    .addMilkCollection(milkCollectionMapper.mapToDto(milkCollection))
                emit(Result.Success(milkCollectionDto.code!!))
            } catch (e: Exception) {
                if ("400" in e.message.toString()) {
                    emit(Result.Error(MilkCollectionException("Volumn exceeded.")))
                } else {
                    emit(Result.Error(LactachainException(e.message)))
                }
            }
        }
    }

    override fun getMilkCollections(): Flow<Result<List<MilkCollectionDataItem>>> {
        return flow {
            try {
                val milkCollections = lactachainService
                    .getMilkCollections()
                var result: MutableList<MilkCollectionDataItem> = mutableListOf()
                val milkCollectionsMap: MutableMap<String, String> = mutableMapOf()
                for (milkCollection in milkCollections) {
                    val transporterId = milkCollection.transporter.toString()
                    var transporterName = milkCollectionsMap[transporterId]
                    if (transporterName == null) {
                        val transporter =
                            lactachainService.getTransporterInfoById(milkCollection.transporter.toString())
                        transporterName = transporter.name
                        milkCollectionsMap[transporterId] = transporterName
                    }
                    result.add(
                        MilkCollectionDataItem(
                            milkCollection.code,
                            milkCollection.volumn,
                            transporterName,
                            transporterId.toInt(),
                            milkCollection.date!!
                        )
                    )
                }
                emit(Result.Success(result))
            } catch (e: Exception) {
                if ("400" in e.message.toString()) {
                    emit(Result.Error(MilkCollectionException("Error traces.")))
                } else {
                    emit(Result.Error(LactachainException(e.message)))
                }
            }
        }
    }

    override fun addMilkDelivery(milkDelivery: MilkDeliveryData): Flow<Result<Int>> {
        return flow {
            try {
                val milkDeliveryDto = lactachainService
                    .addMilkDelivery(milkDeliveryMapper.mapToDto(milkDelivery))
                emit(Result.Success(milkDeliveryDto.code!!))
            } catch (e: Exception) {
                if ("400" in e.message.toString()) {
                    emit(Result.Error(MilkDeliveryException("Not exists.")))
                } else {
                    emit(Result.Error(LactachainException(e.message)))
                }
            }
        }
    }

    override fun getReceptionSilosData(): Flow<Result<List<ReceptionSiloData>>> {
        return flow {
            val response = lactachainService
                .getReceptionSilos()
            val results = response.results
            emit(Result.Success(receptionSiloMapper.mapFromDtoList(results)))
        }
    }

    override suspend fun updateMilkCollection(code: String): EmptyResult {
            try {
                lactachainService.updateMilkCollection(code)
                return EmptyResult.Success
            } catch (e: Exception) {
                if ("404" in e.message.toString()) {
                    return EmptyResult.Error(MilkCollectionException("Not exists milk collection."))
                } else {
                     return EmptyResult.Error(LactachainException(e.message))
                }
            }

    }
}