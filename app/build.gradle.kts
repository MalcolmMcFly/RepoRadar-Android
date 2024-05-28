/**
 * Developed by Malcolm Woods
 */

import java.io.FileNotFoundException
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp")
    id("org.jetbrains.dokka")
}

// Extension function to read properties file
fun Project.loadProperties(fileName: String): Properties {
    val propertiesFile = rootProject.file(fileName)
    if (!propertiesFile.exists()) {
        throw FileNotFoundException("The required file '$fileName' was not found in the project root.")
    }
    return Properties().apply {
        load(propertiesFile.inputStream())
    }
}

android {
    namespace = "co.kidcasa.reporadar"
    compileSdk = 34

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "co.kidcasa.reporadar"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    val properties = loadProperties("apikeys.properties")
    val githubToken = properties.getProperty("github.token")

    buildTypes {
        debug {
            buildConfigField("String", "GITHUB_TOKEN", "\"$githubToken\"")
            buildConfigField("String", "GITHUB_BASE_URL", "\"https://api.github.com/\"")
        }
        release {
            isMinifyEnabled = false
            buildConfigField("String", "GITHUB_TOKEN", "\"$githubToken\"")
            buildConfigField("String", "GITHUB_BASE_URL", "\"https://api.github.com/\"")
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
        kotlinCompilerExtensionVersion = "1.5.1"
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
    implementation(libs.androidx.material)
    implementation(libs.androidx.material3)
    implementation(libs.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Koin Dependency Injection
    implementation(libs.bundles.koin)

    // JSON
    implementation(libs.bundles.moshi)
    ksp(libs.moshi.kotlin.codegen)

    // Image Processing
    implementation(libs.bundles.coil)

    // Network
    implementation(libs.bundles.network)

    // Testing dependencies
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("org.robolectric:robolectric:4.9.2")
    testImplementation("io.mockk:mockk:1.13.3")
    androidTestImplementation("io.mockk:mockk-android:1.13.3")
}
