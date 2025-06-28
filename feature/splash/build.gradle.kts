plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":core:ui"))
    implementation(libs.lottie.compose)
}