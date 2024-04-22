package com.hudyweas.workouttogether.common.static

import com.hudyweas.workouttogether.FRIENDS_SCREEN
import com.hudyweas.workouttogether.R
import com.hudyweas.workouttogether.SETTINGS_SCREEN
import com.hudyweas.workouttogether.WEATHER_SCREEN
import com.hudyweas.workouttogether.model.NavigationItem

val navigationItemsList = listOf<NavigationItem>(
    NavigationItem(
        title = "Home",
        description = "Home Screen",
        itemId = "homeScreen",
        icon = R.drawable.ic_check
    ),
    NavigationItem(
        title = "Settings",
        description = "Settings Screen",
        itemId = SETTINGS_SCREEN,
        icon = R.drawable.ic_settings
    ),
    NavigationItem(
        title = "Friends",
        description = "Friends Screen",
        itemId = FRIENDS_SCREEN,
        icon = R.drawable.ic_check
    ),
    NavigationItem(
        title = "Weather",
        description = "Chek the weather",
        itemId = WEATHER_SCREEN,
        icon = R.drawable.ic_check
    )
)