@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-system"

include(":kotlin-convention")
include(":android-convention")
include(":plugins:android-jar")
include(":plugins:utility")
include(":static-analysis:common")
include(":static-analysis:spotless")
