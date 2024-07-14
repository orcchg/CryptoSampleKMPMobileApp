import com.android.build.gradle.LibraryExtension
import com.orcchg.crypto.sample.mobileapp.infra.plugin.withVersionCatalogs
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.gradle.kotlin.dsl.configure

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
    id("internal.kotlin-base")
    id("internal.android-base")
}

withVersionCatalogs {
    configure<KotlinMultiplatformExtension> {
        jvmToolchain(versions.javaVersion.get().toInt())
        applyDefaultHierarchyTemplate()
        androidTarget()
        jvm()

        targets.all {
            compilations.all {
                compileTaskProvider.configure {
                    compilerOptions {
                        allWarningsAsErrors.set(true)
                        freeCompilerArgs.add("-Xexpect-actual-classes")
                    }
                }
            }
        }
    }

    configure<LibraryExtension> {
        sourceSets {
            named("main").configure {
                manifest.srcFile("src/androidMain/AndroidManifest.xml")
                res.srcDirs("src/androidMain/res")
                resources.srcDirs("src/commonMain/resources")
            }
        }

        testCoverage {
            jacocoVersion = versions.version.jacoco.get()
        }
    }
}
