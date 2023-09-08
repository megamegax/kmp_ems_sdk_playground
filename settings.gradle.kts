pluginManagement {
    resolutionStrategy {
        eachPlugin {
            val regex = "com.android.(library|application)".toRegex()
            if (regex matches requested.id.id) {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://www.jitpack.io")
    }
}
plugins {
    kotlin("multiplatform") version "1.9.10" apply false
    id("com.android.library") version "8.0.0" apply false
    id("org.jetbrains.dokka") version "1.9.0" apply false
}

rootProject.name = "UnifiedEmarsysSDK"