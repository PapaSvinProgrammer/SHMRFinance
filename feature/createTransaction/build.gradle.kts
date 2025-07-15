plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:coreComponent"))
    api(project(":core:ui"))
    api(project(":domain:transaction"))
    implementation(project(":domain:category"))
    implementation(project(":domain:bankAccount"))
    implementation(project(":core:localFactory"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}