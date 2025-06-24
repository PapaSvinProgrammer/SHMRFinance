plugins {
    id("android-core-module")
}

dependencies {
    implementation(project(":network"))
    api(project(":model"))
    api(project(":common"))
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.hilt.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}