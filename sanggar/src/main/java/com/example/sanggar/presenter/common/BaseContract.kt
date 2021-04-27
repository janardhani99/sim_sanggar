package com.example.sanggar.presenter.common

interface BaseContract {
    interface View {
        fun showError(title: String, message: String?)
    }

    interface Presenter {
        fun showError(title: String, message: String?)
        fun updateFirebaseToken(token: String)
    }

//    interface Handler {
//        @FormUrlEncoded
//        @PATCH("firebase-token")
//        fun updateFireBase(
//                @Field(
//                        value = Constants.REQUEST_NAME_FIREBASE_TOKEN,
//                        encoded = true
//                ) firebaseToken: String
//        ): Observable<BaseResultData>
//    }
}
