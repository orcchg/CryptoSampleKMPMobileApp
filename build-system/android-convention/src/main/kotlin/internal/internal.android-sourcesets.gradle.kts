import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    sourceSets {
        named("main").configure {
            java.srcDir("src/main/java")
            java.srcDir("src/main/kotlin")
            res.srcDir("src/test/resources")
        }
        named("debug").configure {
            java.srcDir("src/debug/java")
            java.srcDir("src/debug/kotlin")
        }
        named("release").configure {
            java.srcDir("src/release/java")
            java.srcDir("src/release/kotlin")
        }
        named("androidTest").configure {
            assets.srcDir("src/androidTest/assets")
            java.srcDir("src/androidTest/java")
            java.srcDir("src/androidTest/kotlin")
        }
        named("test").configure {
            java.srcDir("src/test/java")
            java.srcDir("src/test/kotlin")
        }
    }
}




