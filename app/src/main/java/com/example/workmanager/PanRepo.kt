package com.example.workmanager

import androidx.lifecycle.MutableLiveData

class PanRepo(private val apiService: ApiService) {
    private val _statusData= MutableLiveData<PanResp?>()
    val statusSharedData = _statusData

    suspend fun panAPiCall(panRequest: PanRequest?) {
        val getResp = apiService.panApiCall(panRequest!!)
         if (getResp.body()!=null){
             _statusData.postValue(getResp.body())
         }else{
             _statusData.postValue(null)
         }
    }


}