package com.example.sanggar.common

import com.example.sanggar.data.handler.common.BaseHandler
import com.example.sanggar.data.model.common.APIError
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

object Utilities {
    fun parseError(response: Response<*>): APIError {
        val converter: Converter<ResponseBody, APIError> = BaseHandler().getClient()
                .responseBodyConverter(APIError::class.java, arrayOfNulls<Annotation>(0))

        val error: APIError

        try {
            error = converter.convert(response.errorBody())!!
        } catch (e: IOException) {
            e.printStackTrace()
            return APIError()
        }
        return error
    }


}