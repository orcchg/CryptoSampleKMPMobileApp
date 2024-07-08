plugins {
    `kotlin-dsl`
}

group = "com.orcchg.crypto.sample.mobileapp.infra"

dependencies {
    implementation(libs.kt.core)
    implementation(libs.kt.reflect)
    implementation(libs.android.cachefix)
    implementation(libs.android.gradle) {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk7")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk8")
    }
    implementation(libs.kt.gradle)
    implementation(libs.kt.compose.compiler.gradle)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    implementation(project(":kotlin-convention"))
    implementation(project(":plugins:utility"))
}
