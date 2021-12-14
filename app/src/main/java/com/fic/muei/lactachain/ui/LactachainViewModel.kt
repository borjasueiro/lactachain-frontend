package com.fic.muei.lactachain.ui

import androidx.lifecycle.*
import com.fic.muei.lactachain.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LactachainViewModel @Inject constructor(
    private val lactachainRepository: LactachainRepository
) : ViewModel() {
    private var _farmState = MutableStateFlow<FarmUIState>(FarmUIState.Empty)
    private var _loginState = MutableStateFlow<LoginUIState>(LoginUIState.Empty)
    private var _transportState = MutableStateFlow<TransportUIState>(TransportUIState.Empty)
    private var _transporter: MutableLiveData<TransporterData?> = MutableLiveData()
    val farmState: StateFlow<FarmUIState> get() = _farmState
    val loginState: StateFlow<LoginUIState> get() = _loginState
    val transportState: StateFlow<TransportUIState> get() = _transportState
    val transporter: LiveData<TransporterData?> get() = _transporter
    fun getFarmData(code: Int) {
        viewModelScope.launch {
            lactachainRepository
                .getFarm(code)
                .collect { farm ->
                    when (farm) {
                        is Result.Success -> _farmState.value = FarmUIState.Success(farm.data)
                        is Result.Error -> _farmState.value = FarmUIState.Error(farm.exception)
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
                        is Result.Error -> _transportState.value = TransportUIState.Error(result.exception)
                    }
                }
        }
    }
}

sealed class FarmUIState {
    data class Success(val farm: FarmData) : FarmUIState()
    data class Error(val exception: Throwable) : FarmUIState()
    object Empty : FarmUIState()
}

sealed class LoginUIState {
    data class Success(val data: Boolean) : LoginUIState()
    data class Error(val exception: Throwable) : LoginUIState()
    object Empty : LoginUIState()
}

sealed class TransportUIState {
    data class Success(val data: List<TransportData>) : TransportUIState()
    data class Error(val exception: Throwable) : TransportUIState()
    object Empty : TransportUIState()
}
