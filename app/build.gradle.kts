plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(28)

    defaultConfig {
        applicationId = "com.github.volfor.diary"

        minSdkVersion(21)
        targetSdkVersion(28)

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    dataBinding {
        isEnabled = true
    }
}

dependencies {
    val supportLibraryVersion = "28.0.0"

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Support libs
    implementation("com.android.support:appcompat-v7:$supportLibraryVersion")
    implementation("com.android.support.constraint:constraint-layout:1.1.3")

    // Kotlin
    implementation(kotlin("stdlib", rootProject.extra["kotlinVersion"] as String))

    // Logging
    implementation("com.github.ajalt:timberkt:1.5.1")
}