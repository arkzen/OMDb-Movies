// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven {
            url = uri("https://maven.fabric.io/public")
        }

    }
    dependencies {
        val nav_version = "2.7.7"
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.1")
        classpath("com.google.gms:google-services:4.4.2")
        classpath("com.google.dagger:dagger:2.48.1")
        classpath("org.hamcrest:hamcrest-core:1.3")
        classpath("androidx.test:monitor:1.6.1@aar")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")


    }
}
