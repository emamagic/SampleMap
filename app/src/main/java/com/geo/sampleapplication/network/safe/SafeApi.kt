package com.geo.sampleapplication.network.safe

import com.geo.sampleapplication.util.NoInternetException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.SocketException
import java.net.UnknownHostException

abstract class SafeApi {

    suspend fun <T> safeApi(call: suspend () -> Response<T>): ApiWrapper<T> {
        return withContext(Dispatchers.IO) { apiTry { call.invoke() } }

    }


    private fun <T> handleResponse(response: Response<T>): ApiWrapper<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return ApiWrapper.Success(
                    data = it,
                    headers = response.headers(),
                    code = response.code()
                )
            }
        }
        return ApiWrapper.ApiError(
            message = response.message(),
            error = response.errorBody()?.string()!!,
            code = response.code(),
            totalError = "${response.message()} // ${
                response.errorBody()?.string()
            } // ${response.code()}"
        )
    }

    private suspend fun <T> apiTry(call: suspend () -> Response<T>): ApiWrapper<T> {
        return try {
            handleResponse(call.invoke())
        } catch (e: NoInternetException) {
            ApiWrapper.NetworkError(message = "${e.message}")
        } catch (e: UnknownHostException){
            ApiWrapper.NetworkError(message = "${e.message}")
        } catch (e: SocketException){
            ApiWrapper.NetworkError(message = "${e.message}")
        } catch (t: Throwable) {
            ApiWrapper.UnknownError(message = "${t.message}//${t.cause}")
        }
    }
}