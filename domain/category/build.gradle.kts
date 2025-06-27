plugins {
    id("android-core-module")
}

dependencies {
    api(project(":core:data"))
    implementation(libs.dagger)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}