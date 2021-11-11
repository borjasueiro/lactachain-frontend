package com.fic.muei.lactachain.ui

import androidx.lifecycle.*
import com.fic.muei.lactachain.model.FarmData
import com.fic.muei.lactachain.model.LactachainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LactachainViewModel @Inject constructor(
    private val lactachainRepository: LactachainRepository):
    ViewModel() {
        private var _farm =  MutableLiveData<FarmData>()
        val farm:LiveData<FarmData> get() = _farm
/*        fun getFarmData(code:Int){
            viewModelScope.launch {
                lactachainRepository
                    .getFarm(code)
                    .asLiveData()
                    .let {
                        _farm.value = it.value
                }
            }
        }*/
}
