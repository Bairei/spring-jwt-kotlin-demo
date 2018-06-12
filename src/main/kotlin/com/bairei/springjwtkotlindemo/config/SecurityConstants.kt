package com.bairei.springjwtkotlindemo.config



object SecurityConstants {
    const val EXPIRATION_DATE = 864_000_000
    const val HEADER_STRING = "Authorization"
    const val SECRET = "SuperSecretKeyForJWTKey"
    const val TOKEN_PREFIX = "Bearer "
    const val SIGN_UP_URL = "/users/signup"
}