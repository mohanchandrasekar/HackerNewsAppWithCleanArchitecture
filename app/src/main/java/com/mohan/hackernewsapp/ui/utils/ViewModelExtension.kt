package com.mohan.hackernewsapp.ui.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohan.domain.State
import com.mohan.domain.UseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun <Params, Type> ViewModel.startUseCase(
    useCase: UseCase<Params, Type>,
    result: MutableLiveData<State<Type>>,
    params: Params
) {
    result.postValue(State.Loading())
    viewModelScope.launch {
        result.postValue(useCase.execute(params))
    }
}

fun <Params, Type> ViewModel.startUseCaseWithResult(
    useCase: UseCase<Params, Type>,
    params: Params
) :State<Type>{

    return runBlocking {
        useCase.execute(params)
    }
}