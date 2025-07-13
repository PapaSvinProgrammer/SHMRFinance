plugins {
    id("android-core-module")
    id("kotlin-kapt")
}

dependencies {
    api(project(":core:room"))
    api(project(":core:network"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.androidx.datastore.preferences)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}