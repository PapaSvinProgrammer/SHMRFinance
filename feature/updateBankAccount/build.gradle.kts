plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":domain:bankAccount"))
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":core:localViewModelFactory"))
    implementation(libs.dagger)
}