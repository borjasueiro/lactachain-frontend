package com.fic.muei.lactachain.ui

import android.R
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.*
import com.fic.muei.lactachain.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LactachainViewModel @Inject constructor(
    private val lactachainRepository: LactachainRepository
) : ViewModel() {
    //State variables
    private var _farmState = MutableStateFlow<FarmUIState>(FarmUIState.Empty)
    private var _loginState = MutableStateFlow<LoginUIState>(LoginUIState.Empty)
    private var _transportState = MutableStateFlow<TransportUIState>(TransportUIState.Empty)
    private var _transportStateCreated =
        MutableStateFlow<TransportUIStateCreated>(TransportUIStateCreated.Empty)
    private var _milkCollectionUIState =
        MutableStateFlow<MilkCollectionUIState>(MilkCollectionUIState.Empty)
    private var _listItemUIState = MutableStateFlow<ListItemUIState>(ListItemUIState.Loading)
    private var _milkDeliveryState =
        MutableStateFlow<MilkDeliveryUIState>(MilkDeliveryUIState.Empty)
    private var _receptionSiloState =
        MutableStateFlow<ListSiloItemUIState>(ListSiloItemUIState.Empty)

    val farmState: StateFlow<FarmUIState> get() = _farmState
    val loginState: StateFlow<LoginUIState> get() = _loginState
    val transportState: StateFlow<TransportUIState> get() = _transportState
    val transportStateCreated: StateFlow<TransportUIStateCreated> get() = _transportStateCreated
    val milkCollectionUIState: StateFlow<MilkCollectionUIState> get() = _milkCollectionUIState
    val listItemUIState: StateFlow<ListItemUIState> get() = _listItemUIState
    val milkDeliveryState: StateFlow<MilkDeliveryUIState> get() = _milkDeliveryState
    val receptionSiloState: StateFlow<ListSiloItemUIState> get() = _receptionSiloState

    //Data variables
    private var _transporter: MutableLiveData<TransporterData?> = MutableLiveData()
    private var _farm: MutableLiveData<FarmData?> = MutableLiveData()
    private var _milkCollectionInTransport: MutableLiveData<MilkCollectionDataItem?> = MutableLiveData()
    private var _listItemTransport: MutableLiveData<List<MilkCollectionDataItem>?> =
        MutableLiveData()
    val transporter: LiveData<TransporterData?> get() = _transporter
    val farm: LiveData<FarmData?> get() = _farm
    val listItemTransport: LiveData<List<MilkCollectionDataItem>?> get() = _listItemTransport
    val milkCollectionInTransport: LiveData<MilkCollectionDataItem?> get()= _milkCollectionInTransport

    fun getFarmData(code: Int) {
        viewModelScope.launch {
            lactachainRepository
                .getFarm(code)
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _farmState.value = FarmUIState.Success
                            _farm.value = result.data
                        }
                        is Result.Error -> _farmState.value = FarmUIState.Error(result.exception)
                    }
                }
        }
    }

    fun setAuthData(user: String, password: String) {
        lactachainRepository.setCredentials(user, password)
    }

    fun getTransporterData(nif: String) {
        viewModelScope.launch {
            lactachainRepository
                .getTransporter(nif)
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _transporter.value = result.data
                            _loginState.value = LoginUIState.Success(result.data == null)
                        }
                        is Result.Error -> _loginState.value = LoginUIState.Error(result.exception)
                    }
                }
        }
    }

    fun getTransportData() {
        viewModelScope.launch {
            lactachainRepository
                .getTransportByTransporter(_transporter.value!!.code)
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _transportState.value = TransportUIState.Success(result.data)
                        }
                        is Result.Error -> _transportState.value =
                            TransportUIState.Error(result.exception)
                    }
                }
        }
    }

    fun addTransport(carRegistration: String, tankCode: String, capacity: Int, current: Boolean) {
        val transport =
            TransportData(carRegistration, tankCode, current, capacity, _transporter.value!!.code)
        viewModelScope.launch {
            lactachainRepository
                .addTransport(transport)
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _transportStateCreated.value = TransportUIStateCreated.Success
                        }
                        is Result.Error -> _transportStateCreated.value =
                            TransportUIStateCreated.Error(result.exception)
                    }
                }
        }
    }

    fun addMilkCollection(volumn: Int, test: Boolean) {
        val milkCollection =
            MilkCollectionData(null, test, volumn, _farm.value!!.code, _transporter.value!!.code)
        viewModelScope.launch {
            lactachainRepository
                .addMilkCollection(milkCollection)
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _milkCollectionUIState.value =
                                MilkCollectionUIState.Success(result.data)
                        }
                        is Result.Error -> _milkCollectionUIState.value =
                            MilkCollectionUIState.Error(result.exception)
                    }
                }
        }
    }

    fun getTracesLists() {
        viewModelScope.launch {
            lactachainRepository
                .getMilkCollections()
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _listItemTransport.value = result.data
                            _listItemUIState.value = ListItemUIState.Success
                        }
                        is Result.Error -> {
                            _listItemUIState.value = ListItemUIState.Error(result.exception)
                        }
                    }
                }
        }
    }

    fun getReceptionSilosData() {
        viewModelScope.launch {
            lactachainRepository
                .getReceptionSilosData()
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _receptionSiloState.value = ListSiloItemUIState.Success(result.data)
                        }
                        is Result.Error -> _receptionSiloState.value =
                            ListSiloItemUIState.Error(result.exception)
                    }

                }
        }
    }

    fun setSelectedMilkColletion(milkCollection: MilkCollectionDataItem) {
        viewModelScope.launch {
            _milkCollectionInTransport.value = milkCollection
        }
    }

    fun addMilkDelivery(
        test: Boolean,
        temp: Int,
        silo: Int
    ) {
        viewModelScope.launch {
            val milkDelivery =
                MilkDeliveryData(null, test,milkCollectionInTransport.value!!.volumn, temp, silo,milkCollectionInTransport.value!!.transporterCode)
            lactachainRepository
                .addMilkDelivery(milkDelivery)
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _milkDeliveryState.value =
                                MilkDeliveryUIState.Success(result.data)
                            lactachainRepository.updateMilkCollection(milkCollectionInTransport.value!!.code.toString())
                        }
                        is Result.Error -> _milkDeliveryState.value =
                            MilkDeliveryUIState.Error(result.exception)
                    }
                }

        }
    }
}

sealed class FarmUIState {
    object Success : FarmUIState()
    data class Error(val exception: Throwable) : FarmUIState()
    object Empty : FarmUIState()
}

sealed class LoginUIState {
    data class Success(val data: Boolean) : LoginUIState()
    data class Error(val exception: Throwable) : LoginUIState()
    object Empty : LoginUIState()
}

sealed class TransportUIState {
    data class Success(val data: List<TransportListData>) : TransportUIState()
    data class Error(val exception: Throwable) : TransportUIState()
    object Empty : TransportUIState()
}

sealed class TransportUIStateCreated {
    object Success : TransportUIStateCreated()
    data class Error(val exception: Throwable) : TransportUIStateCreated()
    object Empty : TransportUIStateCreated()
}

sealed class MilkCollectionUIState {
    data class Success(val code: Int) : MilkCollectionUIState()
    data class Error(val exception: Throwable) : MilkCollectionUIState()
    object Empty : MilkCollectionUIState()
}

sealed class ListItemUIState {
    object Success : ListItemUIState()
    data class Error(val exception: Throwable) : ListItemUIState()
    object Loading : ListItemUIState()
}

sealed class MilkDeliveryUIState {
    data class Success(val code: Int) : MilkDeliveryUIState()
    data class Error(val exception: Throwable) : MilkDeliveryUIState()
    object Empty : MilkDeliveryUIState()
}

sealed class ListSiloItemUIState {
    data class Success(val data: List<ReceptionSiloData>) : ListSiloItemUIState()
    data class Error(val exception: Throwable) : ListSiloItemUIState()
    object Empty : ListSiloItemUIState()
}