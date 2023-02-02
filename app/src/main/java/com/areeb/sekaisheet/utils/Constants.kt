package com.areeb.sekaisheet.utils

interface Constants {
    companion object {
        const val SEKAISHEET_KEY = "sekaiSheet_key"
    }

    interface ApiObjects {
        companion object {
            const val CLIENT_ID = "tnV-FAsxWgslcb_CTGIWKQoifqnUM8iG71HO8nTiz3M"
            const val TRENDING = "trending"
        }
    }

    interface ActivityToFragment {
        companion object {
            const val WALLPAPER_ID = "wallpaperId"
        }
    }
    interface  Collection {
        companion object {
            const val COLLECTION_FILE = "collection.json"
        }
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
