plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
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
    val appcompatVersion = "1.1.0-alpha04"
    val koinVersion = "2.0.0-beta-1"
    val lifecycleVersion = "2.1.0-alpha04"
    val bindingAdapterVersion = "3.0.0"
    val firebaseUIVersion = "4.3.2"
    val leakCanaryVersion = "1.6.3"

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation("androidx.appcompat:appcompat:$appcompatVersion")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-alpha3")
    implementation("androidx.cardview:cardview:1.0.0")

    // Architecture components
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:${rootProject.extra["navigationVersion"]}")
    implementation("androidx.navigation:navigation-ui-ktx:${rootProject.extra["navigationVersion"]}")
    implementation("androidx.core:core-ktx:1.1.0-alpha03")

    // Kotlin
    implementation(kotlin("stdlib", rootProject.extra["kotlinVersion"] as String))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.1.0")

    // DI
    implementation("org.koin:koin-android:$koinVersion")
    implementation("org.koin:koin-androidx-viewmodel:$koinVersion")

    // Firebase
    implementation("com.google.firebase:firebase-core:16.0.6")
    implementation("com.google.firebase:firebase-database:16.0.5")
    implementation("com.firebaseui:firebase-ui-auth:$firebaseUIVersion")
    implementation("com.firebaseui:firebase-ui-database:$firebaseUIVersion")

    // Data Binding
    implementation("me.tatarka.bindingcollectionadapter2:bindingcollectionadapter:$bindingAdapterVersion")
    implementation("me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-recyclerview:$bindingAdapterVersion")
    implementation("me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-ktx:$bindingAdapterVersion")

    // Leak canary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion")
    debugImplementation("com.squareup.leakcanary:leakcanary-support-fragment:$leakCanaryVersion")
    releaseImplementation("com.squareup.leakcanary:leakcanary-android-no-op:$leakCanaryVersion")

    // Logging
    implementation("com.github.ajalt:timberkt:1.5.1")
}

plugins.apply("com.google.gms.google-services")