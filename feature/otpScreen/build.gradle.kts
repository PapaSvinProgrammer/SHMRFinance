plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:coreComponent"))
    implementation(project(":core:localFactory"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}