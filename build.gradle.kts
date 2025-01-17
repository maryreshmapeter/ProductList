// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}

buildscript {
    repositories {
        // other repositories...
        google()
        mavenCentral()
    }
    dependencies {
        // other plugins...
        classpath("com.android.tools.build:gradle:8.1.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44.2")
    }
}