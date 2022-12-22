package com.areeb.sekaisheet.utils

interface Constants {
    companion object {
        const val SEKAISHEET_KEY = "sekaiSheet_key"
    }
    interface StatusCode {
        companion object {
            const val NO_CONTENT = 204
            const val SUCCESS = 200
            const val UNAUTHORIZED = 401
            const val FORBIDDEN = 403

            // Bad Request
            const val BAD_REQUEST = 400
        }
    }
}
