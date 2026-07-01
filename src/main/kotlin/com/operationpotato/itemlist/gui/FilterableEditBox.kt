package com.operationpotato.itemlist.gui

import com.operationpotato.itemlist.utils.SkyBlockItemCategory
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.components.CycleButton
import net.minecraft.client.input.MouseButtonEvent
import net.minecraft.network.chat.Component

class FilterableEditBox(
	font: Font,
	width: Int,
	height: Int,
	narration: Component,
	val filterButton: CycleButton<SkyBlockItemCategory>
) : ClearableEditBox(font, width, height, narration) {

	override fun onClick(event: MouseButtonEvent, doubleClick: Boolean) {
		if (event.x.toInt() in this.x+this.width-filterButton.width..this.x+this.width) {
			filterButton.onClick(event, doubleClick)
		}
		super.onClick(event, doubleClick)
	}

	override fun extractWidgetRenderState(graphics: GuiGraphicsExtractor, mouseX: Int, mouseY: Int, a: Float) {
		super.extractWidgetRenderState(graphics, mouseX, mouseY, a)
		filterButton.extractRenderState(graphics, mouseX, mouseY, a)
	}

	override fun mouseScrolled(x: Double, y: Double, scrollX: Double, scrollY: Double): Boolean {
		if (x.toInt() in this.x+this.width-filterButton.width..this.x+this.width) {
			return filterButton.mouseScrolled(x, y, scrollX, scrollY)
		}
		return super.mouseScrolled(x, y, scrollX, scrollY)
	}

	override fun getInnerWidth(): Int {
		return super.getInnerWidth() - 16
	}
}
