plugins {
    alias(libs.plugins.kt.multiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqldelight.gradle)
}

kotlin {
    jvmToolchain(18)
    androidTarget()
    jvm()
    
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
        commonMain.dependencies {
            //put your multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kt.test)
        }
    }
}

android {
    val appId = project.property("custom.applicationIdPrefix") as? String ?: "com.orcchg.crypto.sample.mobileapp"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()
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
}
