buildscript {
    val kotlinVersion by rootProject.extra { "1.3.11" }

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.5.0-alpha01")
        classpath(kotlin("gradle-plugin", kotlinVersion))
        classpath("android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0-alpha09")
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
