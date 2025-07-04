plugins {
    id("android-feature-module")
    alias(libs.plugins.ksp)
}

dependencies {
    api(project(":core:ui"))
    api(project(":domain:category"))
    api(project(":domain:bankAccount"))
    implementation(project(":core:localViewModelFactory"))
    implementation(project(":core:network"))
    implementation(libs.bundles.ktor)
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}