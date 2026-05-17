package com.operationpotato.itemlist

import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper.registerKeyMapping
import net.minecraft.client.KeyMapping
import net.minecraft.resources.Identifier
import org.lwjgl.glfw.GLFW

object Keybinds {
	val category: KeyMapping.Category = KeyMapping.Category.register(
		Identifier.fromNamespaceAndPath("skyblock-item-list", "main")
	)

	val hideOverlay: KeyMapping = registerKeyMapping(
		KeyMapping(
			"key.skyblock-item-list.hideOverlay",
			GLFW.GLFW_KEY_O,
			category
		)
	)

	fun init() {}
}
