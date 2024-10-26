// buildSrc/src/main/kotlin/com/example/convention/AndroidApplicationConventionPlugin.kt
package com.example.convention

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                // targetSdk를 제거하고 lint와 testOptions에 설정
                lint {
                    targetSdk = 34
                }
            }
        }
    }
}