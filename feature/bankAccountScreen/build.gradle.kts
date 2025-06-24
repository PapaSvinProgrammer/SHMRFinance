plugins {
    id("android-feature-module")
    alias(libs.plugins.ksp)
}

dependencies {
    api(project(":ui"))
    implementation(project(":category"))
    implementation(project(":bankAccount"))
    ksp(libs.hilt.android.compiler)
}