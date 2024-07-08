plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.compose.multiplatform).apply(false)
    alias(libs.plugins.compose.multiplatform.compiler).apply(false)
    alias(libs.plugins.kt.android).apply(false)
    alias(libs.plugins.kt.multiplatform).apply(false)
    alias(libs.plugins.sqldelight.gradle).apply(false)
}
