plugins {
    id("android-core-module")
    alias(libs.plugins.kotlin.serialization)
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
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(libs.dagger)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.ktor)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}