plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:coreComponent"))
    api(project(":core:ui"))
    implementation(project(":core:localFactory"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}