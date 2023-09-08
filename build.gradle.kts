plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.dokka")
}

group = "com.emarsys"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "com.emarsys"
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        // testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

kotlin {
    jvmToolchain(17)

    targetHierarchy.default()
    js {
        nodejs {}
    }

    androidTarget {
        //   publishLibraryVariants("release")
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jsMain by getting
        val jsTest by getting

        val androidMain by getting
        val androidInstrumentedTest by getting
    }
}

dependencies {

    commonMainImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

}