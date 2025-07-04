plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:ui"))
    api(project(":domain:bankAccount"))
    implementation(project(":core:localViewModelFactory"))
    implementation(project(":core:network"))
    implementation(libs.bundles.ktor)
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}