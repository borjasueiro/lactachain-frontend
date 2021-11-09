package com.fic.muei.lactachain.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fic.muei.lactachain.model.FarmData
import com.fic.muei.lactachain.model.LactachainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LactachainViewModel @Inject constructor(private val lactachainRepository: LactachainRepository): ViewModel() {
    private var _farm =  MutableLiveData<FarmData>()
    val farm:LiveData<FarmData> get() = _farm
    fun getFarmData(code:Int){
        viewModelScope.launch {
            lactachainRepository.getFarm(code).let{
                _farm.value = it
            }
        }
    }

}
