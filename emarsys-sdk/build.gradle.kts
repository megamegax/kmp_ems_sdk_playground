plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.dokka")
    id("org.kodein.mock.mockmp")
    id("com.google.devtools.ksp")
}

group = "com.emarsys"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven(url = "https://www.jitpack.io")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "com.emarsys"
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
}

kotlin {
    jvmToolchain(8)

    targetHierarchy.default()
    js(IR) {
     
        nodejs()
        browser()
        binaries.executable()

    }

    androidTarget {
        //   publishLibraryVariants("release")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                implementation("com.benasher44:uuid:0.8.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
                implementation("io.ktor:ktor-client-core:2.3.4")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.4")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
                implementation("io.ktor:ktor-serialization:2.3.4")
                implementation("io.github.oshai:kotlin-logging:5.1.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("io.ktor:ktor-client-mock:2.3.4")
                implementation("io.kotest:kotest-framework-engine:5.8.0")
                implementation("io.kotest:kotest-assertions-core:5.8.0")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:2.3.4")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
                implementation("io.ktor:ktor-client-android:2.3.4")
                implementation("androidx.appcompat:appcompat:1.6.1")
                implementation("com.google.crypto.tink:tink-android:1.9.0")
            }
        }
        val androidInstrumentedTest by getting
    }
}

mockmp {
    usesHelper = true
    installWorkaround()
}