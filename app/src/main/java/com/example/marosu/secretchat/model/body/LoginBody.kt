package com.example.marosu.secretchat.model.body

import com.google.gson.annotations.SerializedName

/**
 * Created by Marius-Andrei Rosu on 8/29/2017.
 */
data class LoginBody(@SerializedName("username") val username: String,
                     @SerializedName("password") val password: String)