plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":domain:bankAccount"))
    api(project(":core:ui"))
    implementation(libs.dagger)
}