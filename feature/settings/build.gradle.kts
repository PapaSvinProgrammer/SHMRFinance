plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:localFactory"))
    implementation(project(":core:coreComponent"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}