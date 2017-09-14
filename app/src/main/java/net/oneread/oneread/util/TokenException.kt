package net.oneread.oneread.util

/**
 * Created by sermilion on 9/12/17.
 */
class TokenException : Exception {
    constructor(message: String) : super(message)
    constructor(error: Exception) : super(error)
}