package com.example.sanggar.data.model.common

import com.google.gson.annotations.Expose

data class EmptyResponse(@Expose var data: Any): BaseResultData() {
}