plugins {
    id("android-core-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:utils"))
    api(project(":core:data"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}