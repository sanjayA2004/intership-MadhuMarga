plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinCompose)
    alias(libs.plugins.kotlinKapt)
}

android {
    namespace = "com.example.madhumarga"   // ⚠️ You can keep your own package
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.madhumarga"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    // ✅ Enable Jetpack Compose
    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // ✅ Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)

    // ✅ Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.compose.ui.tooling.preview)

    // ✅ ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // ✅ Navigation
    implementation(libs.androidx.navigation.compose)

    // ✅ Room Database
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // ✅ Debug (optional but useful)
    debugImplementation(libs.androidx.compose.ui.tooling)
}