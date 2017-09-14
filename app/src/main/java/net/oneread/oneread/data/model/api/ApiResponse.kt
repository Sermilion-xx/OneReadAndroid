package net.oneread.oneread.data.model.api

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
                       val clang: List<String>,
                       val id: Int,
                       val token: String,
                       val error: String,
                       val status: Int,
                       val updated_at: String,
                       val created_at: String)

data class LoginResponse(val data: LoginData,
                         val status: Int,
                         val error: String)

data class LoginData(val id: Int,
                val name: String,
                val email: String,
                val username: String,
                val avatar: String,
                val ilang: String,
                val clang: List<String>,
                val counter: Int,
                val updated_at: String,
                val created_at: String,
                val token: String)


