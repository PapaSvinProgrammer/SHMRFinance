plugins {
    id("android-feature-module")
    id("com.google.devtools.ksp")
}

dependencies {
    api(project(":ui"))
    api(project(":category"))
    implementation(project(":bankAccount"))
    ksp(libs.hilt.android.compiler)
}