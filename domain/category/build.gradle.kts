plugins {
    id("android-core-module")
}

dependencies {
    api(project(":data"))
    implementation(libs.hilt.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}