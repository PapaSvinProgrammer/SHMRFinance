plugins {
    id("android-feature-module")
    id("com.google.devtools.ksp")
}

dependencies {
    api(project(":ui"))
    implementation(project(":transaction"))
    ksp(libs.hilt.android.compiler)
}