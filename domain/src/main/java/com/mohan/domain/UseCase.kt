package com.mohan.domain

abstract class UseCase<Params, Type> {
    abstract suspend fun execute(params: Params): State<Type>
}