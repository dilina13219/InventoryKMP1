pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven("https://nodejs.org/dist") // මේක අනිවාර්යයි Web build එකට
    }
}

rootProject.name = "InventoryKMP"
include(":composeApp")