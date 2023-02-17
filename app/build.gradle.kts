plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = AppConfig.applicationId
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles (
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Android KTX
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.lifecycleViewModelKtx)

    // Room
    implementation(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomKtx)

    // Hilt
    implementation(Dependencies.hilt)
    implementation(Dependencies.hiltNavigationCompose)
    kapt(Dependencies.hiltCompiler)

    // Compose
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiUtil)
    implementation(Dependencies.composeUiPreview)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeMaterialIcons)
    implementation(Dependencies.composeMaterialIconsExtended)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeNavigation)
    implementation(Dependencies.composeRuntime)
    implementation(Dependencies.composeViewModel)

    // Splash
    implementation(Dependencies.splahScreen)

    // Library
    implementation(Dependencies.composeProgressItem)

    // Logging
    implementation(Dependencies.timber)

    // Testing
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitTest)
    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(Dependencies.junitCompose)
    debugImplementation(Dependencies.composeTooling)
}