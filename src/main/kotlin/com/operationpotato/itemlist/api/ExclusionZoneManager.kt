package com.operationpotato.itemlist.api

interface ExclusionZoneManager {
	fun addExclusionZone(exclusionZone: ExclusionZone)
	fun getExclusionZones(): List<ExclusionZone>
}
