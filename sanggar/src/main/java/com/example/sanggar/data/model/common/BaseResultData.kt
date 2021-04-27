package com.example.sanggar.data.model.common

import com.google.gson.annotations.Expose

open class BaseResultData {
    @Expose
    var meta: Any? = null

    @Expose
    var link: Any? = null

}

open class APIError {
    val error: ErrorData = ErrorData()
}

class ErrorData {
    val code: Int = 0
    val title: String = ""
    val errors: List<ErrorItem> = listOf()
}

class ErrorItem {
    @Expose
    val title : String?=null

    @Expose
    val message : String?=null
}


