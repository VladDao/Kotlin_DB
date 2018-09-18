package com.test.bd.kotlin_db

import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @get:GET("/v2/59c92a123f0000780183f72d")
    val friendList: Call<List<ContactList>>
    // Call<ContactList> getResponce(@Path("id") String userId, @Path("friends") String fiendList);
}
