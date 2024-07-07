enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://kotlin.bintray.com/kotlinx")
        maven("https://jitpack.io")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CryptoSampleKMPMobileApp"

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

includeBuild("build-system")

include(":android-coin-details")
include(":android-coin-listview")
include(":androidApp")
include(":shared")
