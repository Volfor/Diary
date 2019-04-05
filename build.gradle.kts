buildscript {
    val kotlinVersion by rootProject.extra { "1.3.21" }
    val navigationVersion by rootProject.extra { "2.1.0-alpha02" }

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.5.0-alpha10")
        classpath(kotlin("gradle-plugin", kotlinVersion))
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
        classpath("com.google.gms:google-services:4.2.0")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
    }
}

val clean by tasks.creating(Delete::class) {
    delete(rootProject.buildDir)
}
