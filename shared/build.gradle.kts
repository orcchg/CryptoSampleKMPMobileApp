@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    id("convention.android-lib-kmm")
    kotlin("plugin.serialization") version "2.0.0"
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.multiplatform.compiler)
//    alias(libs.plugins.sqldelight.gradle)
}

kotlin {
    wasmJs {
        moduleName = "shared"
        browser {
            commonWebpackConfig {
                outputFileName = "shared.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
        binaries.executable()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val androidAndJvm by creating {
            dependsOn(commonMain.get())
        }

        val androidMain by getting {
            dependsOn(androidAndJvm)
            dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)
                implementation(libs.koin.android.kmm)
                implementation(libs.ktor.client.okhttp)
            }
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.bundles.ktor.all)
            implementation(libs.coil.compose)
            implementation(libs.koin.core.kmm)
            implementation(libs.kt.serialization)
            implementation(libs.paging)
            implementation(libs.paging.compose)
        }
        commonTest.dependencies {
            implementation(libs.koin.test.kmm)
            implementation(libs.kt.test)
            implementation(libs.ktor.client.mock)
            implementation(libs.paging.testing)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.paging.runtime.ios)
        }
        val jvmMain by getting {
            dependsOn(androidAndJvm)
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.ktor.client.java)
            }
        }
        wasmJsMain.dependencies {
            implementation(libs.bundles.ktor.all.new)
            implementation(libs.ktor.client.js.new)
        }
        wasmJsTest.dependencies {
            implementation(libs.ktor.client.mock.new)
        }
    }
}

android {
    namespace = "com.orcchg.crypto.sample.mobileapp.shared"

    with(buildFeatures) {
        buildConfig = true
    }

    defaultConfig {
        val appId = project.property("custom.applicationIdPrefix") as? String ?: "com.orcchg.crypto.sample.mobileapp"
        buildConfigField("String", "APP_ID_PREFIX", "\"$appId\"")
    }
    buildTypes {
        getByName("release") {
            buildConfigField("String", "BACKEND_URL", "\"${project.findProperty("custom.backendUrl") ?: "http://localhost:8080"}\"")
            buildConfigField("String", "BUILD_TYPE_SUFFIX", "\".release\"")
        }
        getByName("debug") {
            buildConfigField("String", "BACKEND_URL", "\"http://localhost:8080\"")
            buildConfigField("String", "BUILD_TYPE_SUFFIX", "\".debug\"")
        }
    }
    externalNativeBuild {
        cmake {
            path = file("CMakeLists.txt")
        }
    }

    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

compose.desktop {
    application {
        mainClass = "com.orcchg.crypto.sample.mobileapp.MainKt"

        nativeDistributions {
//            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.orcchg.crypto.sample.mobileapp"
            packageVersion = "1.0.0"
        }
    }
}
