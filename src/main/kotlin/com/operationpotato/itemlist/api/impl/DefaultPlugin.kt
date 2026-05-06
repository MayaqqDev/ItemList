package com.operationpotato.itemlist.api.impl

import com.operationpotato.itemlist.api.ExclusionZone
import com.operationpotato.itemlist.api.ExclusionZoneManager
import com.operationpotato.itemlist.api.Plugin
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen
import net.minecraft.client.gui.screens.inventory.InventoryScreen
import tech.thatgravyboat.skyblockapi.helpers.McPlayer
import tech.thatgravyboat.skyblockapi.helpers.McScreen
import tech.thatgravyboat.skyblockapi.utils.extentions.right
import tech.thatgravyboat.skyblockapi.utils.extentions.top

class DefaultPlugin : Plugin {
	override fun registerExclusionZones(exclusionZoneManager: ExclusionZoneManager) {
		val screen = McScreen.self ?: return
		val player = McPlayer.self ?: return
		if ((screen !is InventoryScreen && screen !is CreativeModeInventoryScreen) || !screen.showsActiveEffects()) return
		if (player.activeEffects.isEmpty()) return

		val x = screen.right + 2
		val availableWidth = screen.width - (x)
		val width = if (availableWidth >= 120) 120 else 32
		var y = screen.top
		val step = if (player.activeEffects.size > 5) 132 / (player.activeEffects.size) else 33
		player.activeEffects.forEach { _ ->
			exclusionZoneManager.addExclusionZone(ExclusionZone.create(x, y, width, 32))
			y += step
		}
	}
}
