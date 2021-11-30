package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.FarmData
import com.fic.muei.lactachain.model.LactachainRepository
import com.fic.muei.lactachain.model.TransporterData
import com.fic.muei.lactachain.model.exceptions.FarmException
import com.fic.muei.lactachain.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LactachainRepositoryImpl @Inject constructor(
    private val lactachainService : LactachainService,
    private val farmMapper: Mapper<FarmDto, FarmData>,
    private val lactachainAuth: LactachainAuth
):LactachainRepository {
    override fun getFarm(code: Int): Flow<FarmData> {
        return flow{
            try{
                val farm = lactachainService
                    .getFarmInfo(code)
                emit(farmMapper.mapFromDto(farm))
            }catch(e : Exception){
                    throw FarmException(e.message)
            }
        }
    }
    override fun setCredentials(user:String, password:String){
        lactachainAuth.setCredentials(user,password)
    }
//    override fun getTransporter(code: Int): Flow<TransporterData> {
//        return flow{
//            try{
//                val farm = lactachainService
//                    .getFarmInfo(code)
//                emit(farmMapper.mapFromDto(farm))
//            }catch(e : Exception){
//                throw FarmException(e.message)
//            }
//        }
//    }
}