import com.orcchg.crypto.sample.mobileapp.infra.plugin.withVersionCatalogs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

plugins {
    id("convention.static-analysis")
}

withVersionCatalogs {
    dependencies {
        add("implementation", kt.core)
        add("implementation", kt.reflect)
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = versions.javaVersion.get()
            allWarningsAsErrors = false
        }
    }

    tasks.withType<Test>().configureEach {
        maxParallelForks = Runtime.getRuntime().availableProcessors()
    }
}
