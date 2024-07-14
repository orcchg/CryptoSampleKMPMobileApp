import com.orcchg.crypto.sample.mobileapp.infra.plugin.withVersionCatalogs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    id("convention.static-analysis")
}

withVersionCatalogs {
    dependencies {
        add("implementation", kt.core)
        add("implementation", kt.reflect)
    }

    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            allWarningsAsErrors = false
            jvmTarget = JvmTarget.fromTarget(versions.javaVersion.get())
            languageVersion = KotlinVersion.KOTLIN_2_0
            freeCompilerArgs.add("-Xexpect-actual-classes")
        }
    }

    tasks.withType<Test>().configureEach {
        maxParallelForks = Runtime.getRuntime().availableProcessors()
    }
}
