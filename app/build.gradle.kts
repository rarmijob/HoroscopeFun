plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.dagger.hilt.android)
    id("kotlin-kapt")
}

android {
    namespace = "dev.rarmijo.horoscopefun"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.rarmijo.horoscopefun"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //coroutines
    implementation(libs.kotlinx.coroutines.android)

    // ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
   //implementation "androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version"

    //Navigation
    implementation (libs.androidx.navigation.compose)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    //materials icons extended
    implementation (libs.androidx.material.icons.extended)

    //retrofit
    implementation(libs.squareup.retrofit2)
    //retrofit moshi converter
    implementation(libs.squareup.retrofit2.converter.moshi)

    //moshi kotlin
    //implementation(libs.squareup.moshi.kotlin)
    //moshi codegen
    kapt(libs.squareup.moshi.kotlin.codegen)


    //logging interceptor
    implementation(libs.okhttp3.logging.interceptor)


    //camera
    implementation (libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    // If you want to additionally use the CameraX Lifecycle library
    implementation(libs.androidx.camera.lifecycle)
    // If you want to additionally use the CameraX VideoCapture library
    implementation(libs.androidx.camera.video)
    // If you want to additionally use the CameraX View class
    implementation(libs.androidx.camera.view)
    // If you want to additionally use the CameraX Extensions library
    implementation(libs.androidx.camera.extensions)


    //library to handle permissions
    implementation(libs.accompanist.permissions)

    implementation(libs.androidx.core.splashscreen)

}

kapt {
    correctErrorTypes = true
}