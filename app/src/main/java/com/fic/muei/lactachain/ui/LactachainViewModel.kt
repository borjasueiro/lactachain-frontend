package com.fic.muei.lactachain.ui

import androidx.lifecycle.*
import com.fic.muei.lactachain.model.FarmData
import com.fic.muei.lactachain.model.LactachainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LactachainViewModel @Inject constructor(
    private val lactachainRepository: LactachainRepository): ViewModel() {
        private var _state =  MutableStateFlow<FarmUIState>(FarmUIState.Empty)
        val state: StateFlow<FarmUIState> get() = _state
        fun getFarmData(code:Int){
            viewModelScope.launch {
                lactachainRepository
                    .getFarm(code)
                    .collect {
                        farm -> _state.value = FarmUIState.Success(farm)
                    }
            }
        }
        fun setAuthData(user:String, password:String){
            lactachainRepository.setCredentials(user, password)
        }
    }


sealed class FarmUIState {
    data class Success(val farm:FarmData):FarmUIState()
    data class Error(val exception: Throwable):FarmUIState()
    object Empty:FarmUIState()
}
