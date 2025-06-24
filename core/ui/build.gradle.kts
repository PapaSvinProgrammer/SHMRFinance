
plugins {
    id("android-core-module")
    alias(libs.plugins.kotlin.compose)
}

dependencies {
    api(project(":common"))
    api(project(":model"))
    api(project(":utils"))
    api(project(":navigationRoute"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}