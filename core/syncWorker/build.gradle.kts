plugins {
    id("android-core-module")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:utils"))
    api(libs.androidx.work.runtime.ktx)
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}