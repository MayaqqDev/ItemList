package com.operationpotato.itemlist.api

import net.minecraft.client.gui.navigation.ScreenRectangle

data class ExclusionZone(
	val area: ScreenRectangle
) {
	companion object {
		fun create(x: Int, y: Int, w: Int, h: Int): ExclusionZone {
			return ExclusionZone(ScreenRectangle(x, y, w, h))
		}
	}
}
