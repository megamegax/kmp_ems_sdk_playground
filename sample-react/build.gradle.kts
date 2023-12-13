plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

repositories {
    google()
    mavenCentral()
    maven(url = "https://www.jitpack.io")
}
fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig(Action {
                outputFileName = "index.js"
            })
        }
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(enforcedPlatform(kotlinw("wrappers-bom:1.0.0-pre.624")))

                implementation(kotlinw("react"))
                implementation(kotlinw("react-dom"))
                implementation(kotlinw("react-router-dom"))

                implementation(kotlinw("emotion"))


                implementation(project(":emarsys-sdk"))
            }
        }
    }
}

afterEvaluate {
    rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
        versions.webpackCli.version = "4.10.0"
    }
}