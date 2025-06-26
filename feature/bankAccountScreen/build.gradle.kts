plugins {
    id("android-feature-module")
}

dependencies {
    api(project(":core:ui"))
    api(project(":domain:category"))
    api(project(":domain:bankAccount"))
    implementation(project(":core:localViewModelFactory"))
    implementation(libs.dagger)
}