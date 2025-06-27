plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:ui"))
    api(project(":domain:category"))
    api(project(":domain:bankAccount"))
    implementation(project(":core:viewModelFactory"))

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}