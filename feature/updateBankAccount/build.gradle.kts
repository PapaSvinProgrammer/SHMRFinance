plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:coreComponent"))
    api(project(":domain:bankAccount"))
    implementation(project(":core:ui"))
    implementation(project(":core:localViewModelFactory"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}