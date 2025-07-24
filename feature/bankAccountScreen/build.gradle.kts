plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:coreComponent"))
    api(project(":core:ui"))
    api(project(":domain:category"))
    api(project(":domain:bankAccount"))
    implementation(project(":core:charts"))
    implementation(project(":core:localFactory"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}