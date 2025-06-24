plugins {
    id("android-feature-module")
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(":domain:bankAccount"))
    api(project(":core:ui"))
    ksp(libs.hilt.android.compiler)
}