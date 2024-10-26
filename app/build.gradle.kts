plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
//    id("com.google.dagger.hilt.android")
//    id("kotlin-kapt")
    id("com.example.hilt")

}

android {
    namespace = "com.example.newsjam_android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.newsjam_android"
        minSdk = 26
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
    // 뷰 바인딩 활성화
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.google.flexbox)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.viewpager2)
    implementation(libs.firebase.messaging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.github.jolenechong:androidWordCloud:1.0.0") {
        exclude(group = "com.sun.xml.bind", module = "jaxb-core")
        exclude(group = "com.sun.xml.bind", module = "jaxb-impl")
    }
    implementation("com.github.bumptech.glide:glide:4.9.0") {
        exclude(group = "com.android.support")
    }
    implementation(libs.support.fragment)
    implementation("com.github.AnyChart:AnyChart-Android:1.1.2")
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.messaging)
    implementation (libs.androidx.datastore.preferences)
//    implementation(libs.hilt.android)
//    kapt(libs.hilt.android.compiler)

}