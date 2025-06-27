plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":core:ui"))
    api(project(":domain:transaction"))
    implementation(libs.dagger)
}