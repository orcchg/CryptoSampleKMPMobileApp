plugins {
    id("convention.android-app")
}

android {
    defaultConfig {
        applicationId = "com.orcchg.crypto.sample.mobileapp"
        versionCode = 1
        versionName = "1.0"
        resourceConfigurations += setOf("en")
    }
    buildTypes {
        getByName("release") {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    namespace = "com.orcchg.crypto.sample.mobileapp"
}

dependencies {
    implementation(project(":android-coin-details"))
    implementation(project(":android-coin-listview"))
    implementation(projects.shared)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.core)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
