@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kt.multiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.multiplatform.compiler)
//    alias(libs.plugins.sqldelight.gradle)
}

kotlin {
    jvmToolchain(18)
    androidTarget()
    jvm()

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
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }
        commonTest.dependencies {
            implementation(libs.kt.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

android {
    val appId = project.property("custom.applicationIdPrefix") as? String ?: "com.orcchg.crypto.sample.mobileapp"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()
        targetSdk = libs.versions.android.sdk.target.get().toInt()
        buildConfigField("String", "APP_ID_PREFIX", "\"$appId\"")
    }
    buildFeatures {
        aidl = false
        compose = false
        buildConfig = true
        prefab = false
        renderScript = false
        resValues = false
        shaders = false
        viewBinding = false
    }
    buildTypes {
        getByName("release") {
            enableUnitTestCoverage = false
            isJniDebuggable = false
            isMinifyEnabled = true
            isRenderscriptDebuggable = false
            isShrinkResources = false // cannot be used for libraries
            isTestCoverageEnabled = false
            buildConfigField("String", "BACKEND_URL", "\"https://crypto-sample.herokuapp.com\"")
            buildConfigField("String", "BUILD_TYPE_SUFFIX", "\".release\"")
        }
        getByName("debug") {
            enableUnitTestCoverage = true
            isJniDebuggable = true
            isMinifyEnabled = false
            isRenderscriptDebuggable = false
            isShrinkResources = false
            isTestCoverageEnabled = true
            buildConfigField("String", "BACKEND_URL", "\"http://localhost:8080\"")
            buildConfigField("String", "BUILD_TYPE_SUFFIX", "\".debug\"")
        }
    }
    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
        }
    }
    testCoverage {
        jacocoVersion = libs.versions.version.jacoco.get()
    }
    externalNativeBuild {
        cmake {
            path = file("CMakeLists.txt")
        }
    }
    namespace = "com.orcchg.crypto.sample.mobileapp.shared"

    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
//            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.orcchg.crypto.sample.desktop"
            packageVersion = "1.0.0"
        }
    }
}
