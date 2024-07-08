@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.orcchg.crypto.sample.mobileapp.infra.plugin.withVersionCatalogs

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("internal.kotlin-base")
    id("internal.android-sourcesets")
    id("internal.android-base")
}

withVersionCatalogs {
    configure<BaseAppModuleExtension> {
        signingConfigs {
            create("release") {
                storeFile = file("${rootDir}/cryptosample-release.keystore")
                storePassword = "1q@W3e4r5t"
                keyAlias = "orcchg"
                keyPassword = "1q@W3e4r5t"
            }
        }
        buildTypes {
            getByName("release") {
                isShrinkResources = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "${rootDir}/proguard-rules.pro",
                    "proguard-rules.pro"
                )
            }
            getByName("debug") {
                applicationIdSuffix = ".debug"
                isShrinkResources = false
            }
        }
        buildFeatures {
            buildConfig = true
        }
        applicationVariants.all {
            outputs.withType<BaseVariantOutputImpl>().all {
                this.outputFileName = "CRYPTOSAMPLE-$versionName.apk"
            }
        }

        packaging {
            resources {
                excludes += "META-INF/LICENSE.md"
                excludes += "META-INF/LICENSE-notice.md"
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}
