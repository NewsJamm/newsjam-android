
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}
group = "com.example.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.firebase.performance.gradlePlugin)

}

gradlePlugin {
    plugins {
        register("android-hilt") {
            id = "com.example.hilt"
            implementationClass = "com.example.convention.HiltConventionPlugin"
        }
        register("androidApplication") {
            id = "com.example.android.application"
            implementationClass = "com.example.convention.AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "com.example.android.library"
            implementationClass = "com.example.convention.AndroidLibraryConventionPlugin"
        }
    }
}