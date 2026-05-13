package com.operationpotato.itemlist.gui

import com.operationpotato.itemlist.utils.SkyBlockItemCategory
import com.operationpotato.itemlist.utils.SkyBlockItems
import tech.thatgravyboat.skyblockapi.utils.lazy.registryBoundLazy

class EntireListWidget(width: Int, height: Int) : AbstractItemList(width, height) {
	var visibleChildren: List<StackDisplay> = listOf()
	var currentFilter: SkyBlockItemCategory = SkyBlockItemCategory.ALL
	var currentSearch: String = ""

	fun filterChildren(category: SkyBlockItemCategory) {
		currentFilter = category
		visibleChildren = if (currentFilter == SkyBlockItemCategory.ALL) {
			children
		} else {
			children.filter { it.type == currentFilter }
		}
	}

	fun searchChildren(search: String) {
		val lower = search.lowercase()
		if (search.isEmpty() || !lower.startsWith(currentSearch)) {
			filterChildren(currentFilter)
		}
		currentSearch = lower
		if (lower.isEmpty()) return
		visibleChildren = visibleChildren.filter { it.matchesSearch(search) }
	}

	override fun getItems(): List<StackDisplay> {
		if (visibleChildren.isEmpty()) searchChildren(currentSearch)
		return visibleChildren
	}

	companion object {
		private val children: List<StackDisplay> by registryBoundLazy { getItems() }

		fun getItems(): List<StackDisplay> {
			val displays: MutableList<StackDisplay> = mutableListOf()

			SkyBlockItems.items.forEach { (stack, category) ->
				val display = StackDisplay(stack, category)
				displays.add(display)
			}

			return displays
		}
	}
}
