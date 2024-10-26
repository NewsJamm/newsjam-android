package com.example.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureHiltAndroid() {
    with(pluginManager) {
        pluginManager.apply("dagger.hilt.android.plugin")
        pluginManager.apply("org.jetbrains.kotlin.kapt")
    }
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        "implementation"(libs.findLibrary("hilt.android").get())
        "kaptAndroidText"(libs.findLibrary("hilt.android.compiler").get())
        "kapt"(libs.findLibrary("hilt.android.compiler").get())

    }

}

internal class AndroidHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            configureHiltAndroid()
        }
    }
}