package com.oila.oneaccount.ui.navdrawer

import net.oneread.oneread.data.model.DrawerItem
import java.util.*

fun getDrawerItems(profileUrl: String,
                   name: String,
                   icons: Array<Int>,
                   titles: Array<String>): List<DrawerItem> {
    val items = ArrayList<DrawerItem>()
    items.add(DrawerItem.createHearderItem(profileUrl, name))
    titles.indices.mapTo(items) { DrawerItem.createItem(icons[it], titles[it]) }
    return items
}

