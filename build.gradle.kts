plugins {
    kotlin("multiplatform") version "1.9.21" apply false
    id("com.android.library") version "8.0.0" apply false
    id("org.jetbrains.dokka") version "1.9.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.21" apply false

    id("com.android.application") version "8.0.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("com.google.devtools.ksp") version "1.9.21-1.0.15" apply false
    id("org.kodein.mock.mockmp") version "1.16.0" apply false
}