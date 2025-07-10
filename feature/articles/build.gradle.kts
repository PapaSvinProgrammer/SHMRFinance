plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:ui"))
    api(project(":domain:category"))
    implementation(project(":core:localViewModelFactory"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}