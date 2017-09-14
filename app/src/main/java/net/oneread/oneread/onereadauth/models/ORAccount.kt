package net.oneread.oneread.onereadauth.models

import net.oneread.oneread.data.model.BaseItem

/**
 * Created by sermilion on 9/10/17.
 */
class ORAccount(val email: String,
                val username: String,
                val ilang: String,
                val clang: List<String>,
                val token: String,
                val error: String,
                val status: Int,
                val updated_at: String,
                val created_at: String,
                val is_premium: Boolean,
                val is_friend: Boolean,
                val is_suspended: Boolean,
                val hide_from_robots: Boolean,
                val has_verified_email: Boolean,
                var inbox_count: Int,
                val has_mail: Boolean,
                val hide_ads: Boolean) : BaseItem() {
}