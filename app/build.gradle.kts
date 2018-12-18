plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
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
    val appcompatVersion = "1.1.0-alpha01"
    val kodeinVersion = "6.0.1"
    val lifecycleVersion = "2.0.0"
    val navigationVersion = "1.0.0-alpha08"

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Support libs
    implementation("androidx.appcompat:appcompat:$appcompatVersion")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-alpha2")

    // Architecture components
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    implementation("android.arch.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("android.arch.navigation:navigation-ui-ktx:$navigationVersion")

    // Kotlin
    implementation(kotlin("stdlib", rootProject.extra["kotlinVersion"] as String))

    // Rx
    implementation("io.reactivex.rxjava2:rxkotlin:2.3.0")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.0")

    // DI
    implementation("org.kodein.di:kodein-di-generic-jvm:$kodeinVersion")
    implementation("org.kodein.di:kodein-di-framework-android-x:$kodeinVersion")

    // Firebase
    implementation("com.google.firebase:firebase-core:16.0.6")
    implementation("com.firebaseui:firebase-ui-auth:4.2.1")
    implementation("com.google.firebase:firebase-database:16.0.5")

    // Logging
    implementation("com.github.ajalt:timberkt:1.5.1")
}

plugins.apply("com.google.gms.google-services")