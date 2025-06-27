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
    id("com.spotify.ruler")
    id("kotlin-kapt")
}

android {
    defaultConfig {
        applicationId = Const.NAMESPACE
        versionCode = 1
        versionName = "1.0"
        targetSdk = Const.COMPILE_SKD
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
    implementation(project(":feature:articles"))
    implementation(project(":feature:connectivityState"))
    implementation(project(":core:network"))
    implementation(project(":core:viewModelFactory"))

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}