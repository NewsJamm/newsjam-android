plugins {
    id("com.example.android.library")
    id("com.example.hilt")
}

android {
    namespace = "com.newsjam.data"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(libs.androidx.datastore.preferences.v100)
    implementation(libs.androidx.datastore.core)
    // okHttp
    implementation(libs.okhttp)
    implementation(platform(libs.okhttp.bom))
    implementation (libs.logging.interceptor)
    implementation (libs.okhttp.urlconnection)
    implementation (libs.play.services.auth)
    implementation (libs.google.api.client.android)
    implementation (libs.api.client.google.api.client.gson)
    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // gson
    implementation(libs.gson)
}

