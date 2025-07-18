plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:localFactory"))
    api(project(":core:coreComponent"))
    api(project(":core:ui"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}