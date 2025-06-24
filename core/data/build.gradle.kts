plugins {
    id("android-core-module")
}

dependencies {
    implementation(project(":core:network"))
    api(project(":core:model"))
    api(project(":core:common"))
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.hilt.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}