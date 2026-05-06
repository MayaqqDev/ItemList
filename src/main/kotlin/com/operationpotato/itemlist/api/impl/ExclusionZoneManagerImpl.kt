package com.operationpotato.itemlist.api.impl

import com.operationpotato.itemlist.api.ExclusionZone
import com.operationpotato.itemlist.api.ExclusionZoneManager

class ExclusionZoneManagerImpl : ExclusionZoneManager {
	private var exclusionZones: MutableList<ExclusionZone> = mutableListOf()
	private var previous: List<ExclusionZone> = listOf()

	private var previousHash = previous.hashCode()
	private var hasChanged: Boolean = false

	override fun addExclusionZone(exclusionZone: ExclusionZone) {
		exclusionZones.add(exclusionZone)
	}

	override fun getExclusionZones(): List<ExclusionZone> {
		return exclusionZones
	}

	fun clearExclusionZones() {
		previous = exclusionZones
		exclusionZones = mutableListOf()
		val hash = previous.hashCode()
		hasChanged = previousHash != hash
		previousHash = hash
	}

	fun getHasChanged(): Boolean {
		return hasChanged
	}
}
