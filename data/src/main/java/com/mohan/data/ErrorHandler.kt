package com.mohan.data

import com.mohan.data.ErrorCode.SocketTimeOut
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.net.SocketTimeoutException

open class ErrorHandler {

    fun handleException(e: Exception): String {
        return when (e) {
            is SocketTimeoutException -> getErrorMessage(SocketTimeOut.ordinal)
            is HttpException -> convertErrorBody(e)?.message ?: getErrorMessage(Int.MAX_VALUE)
            else -> getErrorMessage(Int.MAX_VALUE)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            SocketTimeOut.ordinal -> "Request timeout Exception"
            404 -> "Data Not Found"
            else -> "Something went wrong with request"
        }
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                val adapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                adapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }
}

enum class ErrorCode(private val code: Int) {
    SocketTimeOut(-1)
}

data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String
)
