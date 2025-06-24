plugins {
    id("android-feature-module")
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(":bankAccount"))
    api(project(":ui"))
    ksp(libs.hilt.android.compiler)
}