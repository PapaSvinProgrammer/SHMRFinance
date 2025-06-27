
plugins {
    id("android-core-module")
    alias(libs.plugins.kotlin.compose)
}

dependencies {
    api(project(":core:common"))
    api(project(":core:model"))
    api(project(":core:utils"))
    api(project(":core:navigationRoute"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}