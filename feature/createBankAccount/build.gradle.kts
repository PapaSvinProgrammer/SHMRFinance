plugins {
    id("android-feature-module")
    id("com.google.devtools.ksp")
}

dependencies {
    api(project(":ui"))
    implementation(project(":bankAccount"))
    ksp(libs.hilt.android.compiler)
}