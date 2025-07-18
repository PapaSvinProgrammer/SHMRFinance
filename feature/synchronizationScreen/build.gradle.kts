plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:syncWorker"))
    implementation(project(":core:localFactory"))
    implementation(project(":core:coreComponent"))
    implementation(project(":core:ui"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}