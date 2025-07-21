plugins {
    id("android-feature-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:utils"))
    api(project(":core:data"))
    api(project(":core:syncWorker"))
    api(project(":core:localFactory"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}