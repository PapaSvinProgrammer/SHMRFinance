plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

android {
    defaultConfig {
        buildConfigField("String", "VERSION_NAME", "\"${Const.VERSION_NAME}\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:localFactory"))
    implementation(project(":core:coreComponent"))
    implementation(libs.lottie.compose)
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}