package com.example.marosu.secretchat.model.body

/**
 * Created by Marius-Andrei Rosu on 10/10/2017.
 */
data class RegisterBody(val firstName: String,
                        val lastName: String,
                        val email: String,
                        val password: String)