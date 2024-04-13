package com.k4tr1n4.marvelcomics.core.network.factor

import android.util.Log
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ResponseCallAdapter<T>(
    private val responseType: Type
) : CallAdapter<T, Flow<LoadingEvent<T>>> {
    override fun adapt(call: Call<T>): Flow<LoadingEvent<T>> {
        return flow {
            emit(LoadingEvent.Loading)

            try {
                val result = suspendCancellableCoroutine { continuation ->
                    call.enqueue(object : Callback<T> {
                        override fun onFailure(call: Call<T>, t: Throwable) {
                            continuation.resumeWithException(t)
                        }

                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            response.body()?.let {
                                Log.d("Response - message:", response.message())
                                Log.d("Response - header:", response.headers().toString())
                                Log.d("Response - body:", response.body().toString())
                                continuation.resume(it)
                            } ?: kotlin.run {
                                continuation.resumeWithException(
                                    Throwable(message = "Null JSON")
                                )
                            }
                        }
                    })
                    continuation.invokeOnCancellation { call.cancel() }
                }
                emit(LoadingEvent.Success(result))
            }catch (t: Throwable){
                Log.e("Response - error message:", t.message.toString())
                Log.e("Response - error cause:", t.cause.toString())
                Log.d("Response - error stack trace:", t.stackTraceToString())
                emit(LoadingEvent.Error(t))
            }
        }
    }

    override fun responseType() = responseType
}


class BodyCallAdapter<T>(private val responseType: Type) : CallAdapter<T, Flow<T>> {
    override fun adapt(call: Call<T>): Flow<T> {
        return flow {
            emit(
                suspendCancellableCoroutine { continuation ->
                    call.enqueue(object : Callback<T> {
                        override fun onFailure(call: Call<T>, t: Throwable) {
                            continuation.resumeWithException(t)
                        }

                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            try {
                                continuation.resume(response.body()!!)
                            } catch (e: Exception) {
                                continuation.resumeWithException(e)
                            }
                        }
                    })
                    continuation.invokeOnCancellation { call.cancel() }
                }
            )
        }
    }

    override fun responseType() = responseType
}