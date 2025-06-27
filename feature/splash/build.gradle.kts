plugins {
    id("android-feature-module")
    id("com.google.devtools.ksp")
}

dependencies {
    api(project(":core:ui"))
    implementation(libs.lottie.compose)
}