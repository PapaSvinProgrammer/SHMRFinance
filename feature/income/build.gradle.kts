plugins {
    id("android-feature-module")
    alias(libs.plugins.ksp)
}

dependencies {
    api(project(":ui"))
    implementation(project(":transaction"))
    ksp(libs.hilt.android.compiler)
}