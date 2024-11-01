plugins {
    id("com.example.android.library")

    id("com.example.hilt")
}

android {
    namespace = "com.newsjam.data"
}

dependencies {
    implementation(project(":core:model")) // `data` 모듈을 `domain` 모듈의 종속성으로 추가
    implementation(project(":core:data")) // `data` 모듈을 `domain` 모듈의 종속성으로 추가
}