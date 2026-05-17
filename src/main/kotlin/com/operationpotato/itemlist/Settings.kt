package com.operationpotato.itemlist

import com.operationpotato.itemlist.utils.SkyBlockItemCategory

object Settings {
	var enabled: Boolean = false
	var scale: Float = 1.0f
	var lastSearch: String = ""
	var lastFilter: SkyBlockItemCategory = SkyBlockItemCategory.ALL
}
