import com.diffplug.gradle.spotless.JavaExtension
import com.diffplug.gradle.spotless.KotlinExtension
import com.diffplug.gradle.spotless.SpotlessExtension
import com.orcchg.crypto.sample.mobileapp.infra.plugin.withVersionCatalogs

plugins {
    id("com.diffplug.spotless")
}

configure<SpotlessExtension> {
    withVersionCatalogs {
        format("misc") {
            target("*.gradle", "*.md", ".gitignore")
            trimTrailingWhitespace()
            indentWithSpaces()
            endWithNewline()
        }
        java {
            importOrder() // standard import order
            removeUnusedImports()
//            googleJavaFormat("1.11.0").aosp()
            configureJavaExt(this)
        }
        kotlin {
            val ktlintVersion = versions.version.debug.ktlint.get()
            ktlint(ktlintVersion)
                .editorConfigOverride(
                    mapOf(
                        "ktlint_standard_kdoc-wrapping" to "disabled",
                        "ktlint_standard_comment-wrapping" to "disabled",
                        "ktlint_standard_property-naming" to "disabled",
                        "ktlint_standard_value-parameter-comment" to "disabled",
                        "max_line_length" to Int.MAX_VALUE,
                    )
                )
            configureKotlinExt(this)
        }
    }
}

val excludedProjects = listOf("app")

fun configureJavaExt(ext: JavaExtension) {
    val targets = arrayOf("src/*/java/**/*.java")
    if (project.name in excludedProjects) {
        ext.targetExclude(*targets)
    } else {
        ext.target(*targets)
    }
}

fun configureKotlinExt(ext: KotlinExtension) {
    val targets = arrayOf("src/*/java/**/*.kt", "src/*/kotlin/**/*.kt")
    if (project.name in excludedProjects) {
        ext.targetExclude(*targets)
    } else {
        ext.target(*targets)
    }
}
