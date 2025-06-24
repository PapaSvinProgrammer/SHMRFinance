plugins {
    id("android-feature-module")
    alias(libs.plugins.ksp)
}

dependencies {
    api(project(":ui"))
    api(project(":category"))
    implementation(project(":bankAccount"))
    ksp(libs.hilt.android.compiler)
}