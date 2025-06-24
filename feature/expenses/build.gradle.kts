plugins {
    id("android-feature-module")
    alias(libs.plugins.ksp)
}

dependencies {
    api(project(":core:ui"))
    implementation(project(":domain:transaction"))
    ksp(libs.hilt.android.compiler)
}