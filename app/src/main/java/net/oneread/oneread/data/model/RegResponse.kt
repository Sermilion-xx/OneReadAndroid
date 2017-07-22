package net.oneread.oneread.data.model

/**
 * ---------------------------------------------------
 * Created by Sermilion on 22/07/2017.
 * Project: OneRead
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
data class RegResponse(val email: String,
                       val username: String,
                       val name: String,
                       val ilang: String,
                       val clang: String,
                       val id: Int,
                       val token: String,
                       val error: String,
                       val status: Int)