package com.orcchg.crypto.sample.mobileapp.infra.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Plugin, which adds the ability to look for the location where Android SDK is installed
 *
 * @author Maxim Alov
 */
class AndroidJarPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.withVersionCatalogs {
            target.extensions.add(
                "androidJar",
                AndroidJarPluginExtension(target).find(versions.android.sdk.compile.get().toInt())
            )
        }
    }
}
