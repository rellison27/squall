plugins {
    id("com.android.application")
    kotlin("android")
    kotlin(KotlinPlugins.kapt)
    kotlin(KotlinPlugins.serialization) version Kotlin.version
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":shared"))
    implementation(Accompanist.navAnimation)
    implementation(Coil.coil)
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation(Kotlinx.datetime)
    implementation(Compose.runtime)
    implementation(Compose.runtimeLiveData)
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.uiTooling)
    implementation(Compose.foundation)
    implementation(Compose.compiler)
    implementation(Compose.constraintLayout)
    implementation(Compose.activity)
    implementation(Compose.navigation)



    implementation("com.google.dagger:hilt-android:2.36")
    kapt("com.google.dagger:hilt-compiler:2.36")

    // For instrumentation tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.36")
    kaptAndroidTest("com.google.dagger:hilt-compiler:2.36")

    // For local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.36")
    kaptTest("com.google.dagger:hilt-compiler:2.36")
    implementation(Hilt.hiltNavigation)

    implementation(Ktor.android)

    debugImplementation(SquareUp.leakCanary)
    implementation("androidx.compose.ui:ui-tooling:1.0.1")
    val navVersion = "2.3.5"

    // Java language implementation
    implementation("androidx.navigation:navigation-fragment:$navVersion")
    implementation("androidx.navigation:navigation-ui:$navVersion")

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    // Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.example.breezepoc.android"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerVersion = Kotlin.version
        kotlinCompilerExtensionVersion = Compose.composeVersion
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}