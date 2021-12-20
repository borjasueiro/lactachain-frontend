package com.fic.muei.lactachain.network

import android.util.Log
import com.fic.muei.lactachain.model.*
import com.fic.muei.lactachain.model.exceptions.FarmException
import com.fic.muei.lactachain.model.exceptions.LactachainException
import com.fic.muei.lactachain.model.exceptions.LoginException
import com.fic.muei.lactachain.network.model.FarmDto
import com.fic.muei.lactachain.network.model.TransportDto
import com.fic.muei.lactachain.network.model.TransportListDto
import com.fic.muei.lactachain.network.model.TransporterDto
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
    private val lactachainAuth: LactachainAuth
):LactachainRepository {
    override fun getFarm(code: Int): Flow<Result<FarmData>> {
        return flow{
            try{
                val farm = lactachainService
                    .getFarmInfo(code)
                val result = Result.Success(farmMapper.mapFromDto(farm))
                emit(result)
            }catch(e : Exception){
                   if("404" in e.message.toString()){
                       emit(Result.Error(FarmException("Farm not exist.")))
                   }else{
                       emit(Result.Error(LactachainException(e.message)))
                   }
            }
        }
    }
    override fun setCredentials(user:String, password:String){
        lactachainAuth.setCredentials(user,password)
    }
    override fun getTransporter(nif: String): Flow<Result<TransporterData?>> {
        return flow {
            try {
                val response = lactachainService
                    .getTransporterInfo(nif)
                val results = response.results
                if (results.isNotEmpty()){
                    emit(Result.Success(transporterMapper.mapFromDto(results[0])))
                }else{
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
        return flow{
            val response = lactachainService
                .getTransportsByTransporter(code)
            val results = response.results
            emit(Result.Success(transportListMapper.mapFromDtoList(results)))
        }
    }

    override fun addTransport(transport: TransportData): Flow<Result<String>> {
        return flow{
            try{
                Log.i("TEST",transport.transporter.toString())
                lactachainService
                    .addTransport(transportMapper.mapToDto(transport))
                emit(Result.Success("Transport added."))
            }catch(e :Exception){
                if("400" in e.message.toString()){
                    emit(Result.Error(FarmException("A field is required.")))
                }else{
                    emit(Result.Error(LactachainException(e.message)))
                }
            }
        }
    }

}