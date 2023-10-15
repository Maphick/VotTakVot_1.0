import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application") //version "8.02" apply false
    //id("com.android.library") //version "8.02" apply false
    id("org.jetbrains.kotlin.android") //version "8.02" apply false
}

android {
    namespace = "com.example.vottakvot"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vottakvot"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.0-beta01")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.5.0-alpha02")
    implementation("javax.inject:javax.inject:1")
    implementation("androidx.datastore:datastore-core:1.0.0")

    // Pager and Indicators - Accompanist
    implementation("com.google.accompanist:accompanist-pager:0.32.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.32.0")

    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")
}