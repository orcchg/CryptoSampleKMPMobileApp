plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

group = "com.orcchg.crypto.sample.mobileapp.infra"

gradlePlugin {
    plugins.register("android-utility") {
        id = "android-utility"
        implementationClass = "com.orcchg.crypto.sample.mobileapp.infra.plugin.AndroidUtilityPlugin"
    }
}

dependencies {
    implementation(libs.kt.core)
    implementation(libs.kt.reflect)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
