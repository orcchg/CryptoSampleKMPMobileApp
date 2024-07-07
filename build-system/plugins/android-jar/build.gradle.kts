plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

group = "com.orcchg.crypto.sample.mobileapp.infra"

gradlePlugin {
    plugins.register("android-jar") {
        id = "android-jar"
        implementationClass = "com.orcchg.crypto.sample.mobileapp.infra.plugin.AndroidJarPlugin"
    }
}

dependencies {
    implementation(libs.kt.core)
    implementation(libs.kt.reflect)
    implementation(libs.kt.gradle)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    implementation(project(":plugins:utility"))
}
