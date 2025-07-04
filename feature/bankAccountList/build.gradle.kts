plugins {
    id("android-feature-module")
    alias(libs.plugins.ksp)
}

dependencies {
    api(project(":domain:bankAccount"))
    api(project(":core:ui"))
    implementation(project(":core:localViewModelFactory"))
    implementation(project(":core:network"))
    implementation(libs.bundles.ktor)
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}