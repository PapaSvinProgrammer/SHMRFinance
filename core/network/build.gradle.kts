plugins {
    id("android-core-module")
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-kapt")
}

android {
    defaultConfig {
        buildConfigField("String", "API_KEY", "\"${rootProject.extra["apiKey"]}\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(project(":core:model"))
    api(project(":core:common"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.kotlinx.serialization.json)
    api(libs.bundles.ktor)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}