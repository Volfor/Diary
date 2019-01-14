plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
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
    val bindingAdapterVersion = "1c7e24ea7a"
    val firebaseUIVersion = "4.3.0"
    val leakCanaryVersion = "1.6.2"

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation("androidx.appcompat:appcompat:$appcompatVersion")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-alpha3")
    implementation("androidx.cardview:cardview:1.0.0")

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
    implementation("com.google.firebase:firebase-database:16.0.5")
    implementation("com.firebaseui:firebase-ui-auth:$firebaseUIVersion")
    implementation("com.firebaseui:firebase-ui-database:$firebaseUIVersion")

    // Data Binding
    implementation("com.github.Volfor.binding-collection-adapter:bindingcollectionadapter:$bindingAdapterVersion")
    implementation("com.github.Volfor.binding-collection-adapter:bindingcollectionadapter-recyclerview:$bindingAdapterVersion")
    implementation("com.github.Volfor.binding-collection-adapter:bindingcollectionadapter-ktx:$bindingAdapterVersion")

    // Leak canary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion")
    debugImplementation("com.squareup.leakcanary:leakcanary-support-fragment:$leakCanaryVersion")
    releaseImplementation("com.squareup.leakcanary:leakcanary-android-no-op:$leakCanaryVersion")

    // Logging
    implementation("com.github.ajalt:timberkt:1.5.1")
}

plugins.apply("com.google.gms.google-services")