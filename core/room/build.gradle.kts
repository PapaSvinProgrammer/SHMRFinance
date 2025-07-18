plugins {
    id("android-core-module")
    alias(libs.plugins.ksp)
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:utils"))
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}