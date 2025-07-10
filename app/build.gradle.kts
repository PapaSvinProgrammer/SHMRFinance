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
}

android {
    defaultConfig {
        applicationId = Const.NAMESPACE
        versionCode = 1
        versionName = "1.0"
        targetSdk = Const.COMPILE_SDK
        multiDexEnabled = true
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
    implementation(project(":feature:bankAccountList"))
    implementation(project(":feature:bankAccountScreen"))
    implementation(project(":feature:createBankAccount"))
    implementation(project(":feature:expenses"))
    implementation(project(":feature:income"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:splash"))
    implementation(project(":feature:transactionHistory"))
    implementation(project(":feature:updateBankAccount"))
    implementation(project(":feature:createTransaction"))
    implementation(project(":core:connectivityState"))

    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}