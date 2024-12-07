plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.iamAsikur.omdbmovies"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.iamAsikur.omdbmovies"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"886038ba\"")
            buildConfigField("String", "BASE_URL", "\"https://www.omdbapi.com/\"")
            isDebuggable = true
        }
        release {
            isMinifyEnabled = false
            buildConfigField("String", "API_KEY", "\"886038ba\"")
            buildConfigField("String", "BASE_URL", "\"https://www.omdbapi.com/\"")
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
}

dependencies {

    val hilt_version = "2.48.1"
    val lifecycle_version = "2.6.2"
    val retrofit_version = "2.9.0"
    val coroutine_version = "1.7.3"
    val glide_version = "4.13.1"
    val exoplayer_version = "2.19.1"

    implementation(libs.androidx.hilt.common)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.activity:activity-ktx:1.8.1")
    implementation ("androidx.fragment:fragment-ktx:1.8.5")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_version")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.retrofit2:converter-scalars:$retrofit_version")

    //image loader
    implementation ("com.github.bumptech.glide:glide:$glide_version")
    annotationProcessor ("com.github.bumptech.glide:compiler:$glide_version")

    //exoplayer
    implementation ("com.google.android.exoplayer:exoplayer:$exoplayer_version")

}