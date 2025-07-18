plugins {
    id("android-core-module")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
}

android {
    defaultConfig {
        buildConfigField("String", "API_KEY", "\"${rootProject.extra["apiKey"]}\"")
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    api(project(":core:utils"))
    api(project(":core:model"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.serialization.json)
    api(libs.bundles.ktor)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}