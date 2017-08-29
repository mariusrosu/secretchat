package com.example.marosu.secretchat.model.body

import com.google.gson.annotations.SerializedName

/**
 * Created by Marius-Andrei Rosu on 8/29/2017.
 */
class LoginBody(@SerializedName("email") val email: String,
                @SerializedName("password") val password: String)