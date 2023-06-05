package com.example.oophandbook.ui.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController

fun NavController.navigateToUnitScreen(unitId: Int) {
    navigate("unit/$unitId") {
        popUpTo("unit/{unit_id}") { inclusive = true }
    }
}

data class UnitNavArgs(val unitId: Int) {
    companion object {
        fun from(savedStateHandle: SavedStateHandle) = UnitNavArgs(
            unitId = checkNotNull(savedStateHandle["unit_id"])
        )
    }
}