package com.orcchg.crypto.sample.mobileapp.infra.plugin

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the

/**
 * Make 'catalog' extension (for version catalog) accessible from convention plugins.
 *
 * https://github.com/gradle/gradle/issues/15383
 */
inline fun Project.withVersionCatalogs(block: LibrariesForLibs.() -> Unit) {
    if (name != "gradle-kotlin-dsl-accessors") {
        val libs = the<LibrariesForLibs>()
        block(libs)
    }
}
