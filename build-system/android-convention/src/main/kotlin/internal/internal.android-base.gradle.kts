@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.BaseExtension
import com.orcchg.crypto.sample.mobileapp.infra.plugin.withVersionCatalogs

plugins {
    id("org.gradle.android.cache-fix")
    id("internal.android-compose-report")
}

withVersionCatalogs {
    configure<BaseExtension> {
        compileSdkVersion(versions.android.sdk.compile.get().toInt())

        compileOptions {
            sourceCompatibility = JavaVersion.toVersion(versions.javaVersion.get())
            targetCompatibility = JavaVersion.toVersion(versions.javaVersion.get())
        }

        defaultConfig {
            minSdk = versions.android.sdk.min.get().toInt()
            targetSdk = versions.android.sdk.target.get().toInt()

            resourceConfigurations += setOf("en")

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            testApplicationId = "com.orcchg.crypto.sample.mobileapp.test"
        }

        buildTypes {
            getByName("release") {
                enableUnitTestCoverage = false
                isCrunchPngs = true
                isDebuggable = false
                isDefault = true // for IDE variant selection
                isEmbedMicroApp = false
                isJniDebuggable = false
                isMinifyEnabled = true
                isRenderscriptDebuggable = false
                isShrinkResources = false // cannot be used for libraries
                isTestCoverageEnabled = false
                manifestPlaceholders["fileproviderSuffix"] = ""
            }
            getByName("debug") {
                enableUnitTestCoverage = true
                isCrunchPngs = false
                isDebuggable = true
                isDefault = false
                isEmbedMicroApp = false
                isJniDebuggable = true
                isMinifyEnabled = false
                isRenderscriptDebuggable = false
                isShrinkResources = false
                isTestCoverageEnabled = true
                manifestPlaceholders["fileproviderSuffix"] = ".debug"
            }
        }

        lintOptions {
            isAbortOnError = false
            isCheckReleaseBuilds = false
            isWarningsAsErrors = true
            textReport = true
            isQuiet = true
        }

        testOptions {
            animationsDisabled = true
//            execution = "ANDROIDX_TEST_ORCHESTRATOR"
            unitTests {
                isIncludeAndroidResources = true
                isReturnDefaultValues = true
                all { test ->
                    test.jvmArgs("-noverify", "-Xmx7168m")
                }
            }
        }

        @Suppress("UnstableApiUsage")
        with(buildFeatures) {
            aidl = false
            compose = true
            buildConfig = false
            prefab = false
            renderScript = false
            resValues = false
            shaders = false
            viewBinding = false
        }

        composeOptions {
            kotlinCompilerExtensionVersion = versions.version.jetpack.compose.compiler.get()
        }
    }
}
