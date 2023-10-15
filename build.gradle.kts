// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
  //  ext {
       // compose_version = '1.1.0'
        //accompanist_version = '0.24.2-alpha'
    //}
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
            id("com.android.library") version "8.1.2" apply false
            id("com.android.application") version "8.1.2" apply false
            id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}