plugins {
    `kotlin-dsl`
}

group = "com.orcchg.crypto.sample.mobileapp.infra"

dependencies {
    implementation(libs.kt.core)
    implementation(libs.kt.reflect)
    implementation(libs.kt.gradle) // to access 'kotlin' plugin further
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    implementation(project(":plugins:android-jar"))
    implementation(project(":plugins:utility"))
    implementation(project(":static-analysis:common"))
}
