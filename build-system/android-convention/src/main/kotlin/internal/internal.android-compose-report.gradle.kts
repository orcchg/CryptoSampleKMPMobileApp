import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * https://developer.android.com/develop/ui/compose/performance/stability/diagnose#kotlin
 */
tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        if (project.findProperty("composeCompilerReports") == "true") {
            freeCompilerArgs.add(
                "-Pplugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${project.buildDir.absolutePath}/compose_compiler"
            )
        }
        if (project.findProperty("composeCompilerMetrics") == "true") {
            freeCompilerArgs.add(
                "-Pplugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${project.buildDir.absolutePath}/compose_compiler"
            )
        }
    }
}
