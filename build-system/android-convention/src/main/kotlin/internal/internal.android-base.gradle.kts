@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.BaseExtension
import com.orcchg.crypto.sample.mobileapp.infra.plugin.withVersionCatalogs

plugins {
    id("org.gradle.android.cache-fix")
    id("internal.android-compose-report")
}

withVersionCatalogs {
    configure<BaseExtension> {
        sourceSets {
            named("main").configure {
                java.srcDir("src/main/java")
                java.srcDir("src/main/kotlin")
                res.srcDir("src/test/resources")
            }
            named("debug").configure {
                java.srcDir("src/debug/java")
                java.srcDir("src/debug/kotlin")
            }
            named("release").configure {
                java.srcDir("src/release/java")
                java.srcDir("src/release/kotlin")
            }
            named("androidTest").configure {
                assets.srcDir("src/androidTest/assets")
                java.srcDir("src/androidTest/java")
                java.srcDir("src/androidTest/kotlin")
            }
            named("test").configure {
                java.srcDir("src/test/java")
                java.srcDir("src/test/kotlin")
            }
        }

        compileSdkVersion(versions.android.sdk.compile.get().toInt())

        compileOptions {
            sourceCompatibility = JavaVersion.toVersion(versions.javaVersion.get())
            targetCompatibility = JavaVersion.toVersion(versions.javaVersion.get())
        }

        defaultConfig {
            minSdk = versions.android.sdk.min.get().toInt()
            targetSdk = versions.android.sdk.target.get().toInt()

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            testApplicationId = "com.orcchg.crypto.sample.mobileapp.test"
        }

        buildTypes {
            getByName("release") {
                isCrunchPngs = true
                isDebuggable = false
                isDefault = true // for IDE variant selection
                isEmbedMicroApp = false
                isMinifyEnabled = true
                isTestCoverageEnabled = false
                manifestPlaceholders["fileproviderSuffix"] = ""
            }
            getByName("debug") {
                isCrunchPngs = false
                isDebuggable = true
                isDefault = false
                isEmbedMicroApp = false
                isMinifyEnabled = false
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
