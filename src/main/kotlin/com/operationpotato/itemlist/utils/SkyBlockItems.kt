package com.operationpotato.itemlist.utils

import tech.thatgravyboat.repolib.api.RepoAPI
import tech.thatgravyboat.skyblockapi.api.repo.LazyItemStack
import tech.thatgravyboat.skyblockapi.api.repo.apis.SkyBlockItemsRepo
import tech.thatgravyboat.skyblockapi.utils.lazy.registryBoundLazy

object SkyBlockItems {
	val itemNames by registryBoundLazy { getAllItemNames() }
	val items by registryBoundLazy { getAllItems() }

	val numeralPattern = Regex("[_;]([0-9]+)$")

	fun sortByNameAndNumber(a: String, b: String): Int {
		val aMatch = numeralPattern.find(a)
		val bMatch = numeralPattern.find(b)
		if (aMatch != null && bMatch != null) {
			val aStart = a.substring(0, aMatch.range.first);
			val bStart = b.substring(0, bMatch.range.first)
			val comparison = aStart.compareTo(bStart)
			if (comparison == 0) {
				val aNum = aMatch.groupValues[1].toIntOrNull() ?: return a.compareTo(b)
				val bNum = bMatch.groupValues[1].toIntOrNull() ?: return a.compareTo(b)
				return aNum.compareTo(bNum)
			}
		}
		return a.compareTo(b)
	}

	fun getAllItemNames(): List<String> {
		return RepoAPI.items().items().keys.sortedWith(::sortByNameAndNumber)
	}

	fun getAllItems(): List<LazyItemStack> {
		if (!RepoAPI.isInitialized()) return listOf()
		val allItems: MutableList<LazyItemStack> = mutableListOf()

		itemNames.forEach { key ->
			val stack = SkyBlockItemsRepo.getLazyItemStack(key) ?: return@forEach
			allItems.add(stack)
		}

		return allItems
	}
}
