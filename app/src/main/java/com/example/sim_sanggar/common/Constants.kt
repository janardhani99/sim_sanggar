package com.example.sim_sanggar.common

object Constants {

    object Validations {
        const val VALIDATION_EMPTY = 0
        const val VALIDATION_PHONE = 1
        const val VALIDATION_NAME = 2
        const val VALIDATION_EMAIL = 3
        const val VALIDATION_MINIMUM = 4
        const val VALIDATION_ADDRESS = 5
        const val VALIDATION_VALUE_ZERO = 6
        const val VALIDATION_DISCOUNT = 7
    }

    object Action {
        const val CREATE = 0
        const val EDIT = 1
    }

    object Intent {
        const val ACTION = "action"
    }

    object Url {
        const val baseUrl =  "https://sanggar.widianapw.com/api/customer/"
    }
}