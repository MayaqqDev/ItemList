package com.operationpotato.itemlist.api

interface Plugin {
	fun registerExclusionZones(exclusionZoneManager: ExclusionZoneManager)
}
