plugins {
    id("android-core-module")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":core:network"))
    api(project(":core:model"))
    api(project(":core:common"))
    implementation(libs.dagger)
    implementation(libs.bundles.ktor)
    kapt(libs.dagger.compiler)
    implementation(libs.androidx.datastore.preferences)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}