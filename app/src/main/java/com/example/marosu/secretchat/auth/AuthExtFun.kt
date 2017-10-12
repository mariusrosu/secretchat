package com.example.marosu.secretchat.auth

import com.example.marosu.secretchat.util.Util.*

fun CharSequence.isNameValid() = this.length > NAME_MIN_LENGTH

fun CharSequence.isPasswordValid() = this.length > PASSWORD_MIN_LENGTH

fun CharSequence.isEmailValid() = EMAIL_PATTERN.matcher(this).matches()