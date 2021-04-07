package com.nativecitizens.hukola.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class LoginViewModel: ViewModel(){
    //CoroutineScope and Job
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean> get() = _navigate


    init {
        _navigate.value = false
        navigate()
    }


    private fun navigate(){
        uiScope.launch {
            delay(4000)
            _navigate.value = true
        }
    }
    fun onNavigateCompleted(){
        _navigate.value = false
    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}




/*
class IntroSliderFragmentViewModelFactory(
    private val listOfSlideTitles: List<String>,
    private val listOfSlideInfo: List<String>): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = IntroSliderFragmentViewModel(listOfSlideTitles, listOfSlideInfo) as T
}*/