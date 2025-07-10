plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":domain:transaction"))
    implementation(project(":domain:category"))
    implementation(project(":core:ui"))
    implementation(project(":core:localViewModelFactory"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}