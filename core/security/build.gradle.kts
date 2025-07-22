plugins {
    id("android-core-module")
    id("kotlin-kapt")
}

dependencies {
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}