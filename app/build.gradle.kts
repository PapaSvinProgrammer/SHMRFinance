plugins {
    id("android-app-module")
    alias(libs.plugins.graph)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    defaultConfig {
        applicationId = Const.NAMESPACE
        versionCode = 1
        versionName = "1.0"
        targetSdk = Const.COMPILE_SKD
    }
}

dependencies {
    implementation(project(":articles"))
    implementation(project(":bankAccountList"))
    implementation(project(":bankAccountScreen"))
    implementation(project(":createBankAccount"))
    implementation(project(":expenses"))
    implementation(project(":income"))
    implementation(project(":settings"))
    implementation(project(":splash"))
    implementation(project(":transactionHistory"))
    implementation(project(":connectivityState"))
    implementation(project(":network"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}