plugins {
    id("android-feature-module")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(":bankAccount"))
    api(project(":ui"))
    ksp(libs.hilt.android.compiler)
}