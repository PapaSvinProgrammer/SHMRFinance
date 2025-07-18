plugins {
    id("android-core-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:utils"))
    api(project(":core:data"))
    api(libs.androidx.work.runtime.ktx)
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
}