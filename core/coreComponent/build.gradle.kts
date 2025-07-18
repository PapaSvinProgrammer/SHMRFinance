plugins {
    id("android-core-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:utils"))
    api(project(":core:data"))
    api(project(":core:syncWorker"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}