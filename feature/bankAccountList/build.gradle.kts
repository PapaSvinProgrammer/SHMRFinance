plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":domain:bankAccount"))
    api(project(":core:ui"))
    implementation(project(":core:localViewModelFactory"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}