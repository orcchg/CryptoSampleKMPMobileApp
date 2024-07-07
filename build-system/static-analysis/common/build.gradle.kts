plugins {
    `kotlin-dsl`
}

group = "com.orcchg.crypto.sample.mobileapp.infra"

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(project(":static-analysis:spotless"))
}
