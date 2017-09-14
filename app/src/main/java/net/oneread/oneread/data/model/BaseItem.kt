package net.oneread.oneread.data.model

/**
 * Created by sermilion on 9/10/17.
 */
abstract class BaseItem (var id: String, var name: String, var cretedUtc: Long): Item {
    constructor() : this("", "", 0L)
}
