plugins {
    id("android-core-module")
}

dependencies {
    implementation(project(":core:network"))
    api(project(":core:model"))
    api(project(":core:common"))
    implementation(libs.dagger)
    implementation(libs.androidx.datastore.preferences)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}