plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.tunahankaryagdi.wordmate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tunahankaryagdi.wordmate"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation (libs.androidx.fragment.ktx)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)

    //Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //Gson
    implementation (libs.gson)

    //Swipe refresh layout
    implementation(libs.androidx.swiperefreshlayout)

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:4.8.1")
    testImplementation("org.mockito:mockito-inline:4.8.1")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.0")
    kaptAndroidTest("com.google.dagger:hilt-compiler:2.48")
    testImplementation("io.mockk:mockk:1.13.4")
    testImplementation ("io.mockk:mockk-android:1.13.4")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.0")
}

kapt {
    correctErrorTypes = true
}