plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":domain:transaction"))
    api(project(":core:coreComponent"))
    api(project(":core:ui"))
    implementation(project(":core:charts"))
    implementation(project(":core:localFactory"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}