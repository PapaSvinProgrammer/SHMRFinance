import com.asarkar.gradle.buildtimetracker.BarPosition
import com.asarkar.gradle.buildtimetracker.Output
import com.asarkar.gradle.buildtimetracker.Sort
import java.time.Duration

plugins {
    id("android-app-module")
    alias(libs.plugins.graph)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.time.tracker)
    alias(libs.plugins.ksp)
    id("com.spotify.ruler")
    id("kotlin-kapt")
}

android {
    defaultConfig {
        applicationId = Const.NAMESPACE
        versionCode = Const.VERSION_CODE
        versionName = Const.VERSION_NAME
        targetSdk = Const.COMPILE_SDK
        multiDexEnabled = true
    }

    androidResources {
        @Suppress("UnstableApiUsage")
        generateLocaleConfig = true
    }
}

buildTimeTracker {
    barPosition = BarPosition.TRAILING
    sortBy = Sort.ASC
    output = Output.CSV
    minTaskDuration = Duration.ofSeconds(1)
    reportsDir.set(File(layout.buildDirectory.get().asFile, "reports/buildTimeTracker"))
}

ruler {
    abi.set("arm64-v8a")
    locale.set("en")
    screenDensity.set(480)
    sdkVersion.set(27)
}

dependencies {
    implementation(project(":core:coreComponent"))
    implementation(project(":core:network"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}