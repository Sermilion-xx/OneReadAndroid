package net.oneread.oneread.data.model

/**
 * ---------------------------------------------------
 * Created by Sermilion on 21/07/2017.
 * Project: OneRead
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
data class Article(val title: String,
                   val text: String,
                   val blog: String,
                   val time: String,
                   val saved: Boolean,
                   val tags: List<String>): BaseItem()