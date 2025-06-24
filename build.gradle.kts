import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.graph) apply false
}

buildscript {
    dependencies {
        classpath(libs.ruler.plugin)
    }
}

val properties = Properties()
val file = rootProject.file("local.properties")
properties.load(FileInputStream(file))

extra.apply {
    set("apiKey", getApiKey())
}

private fun getApiKey(): String {
    return properties.getProperty("API_KEY", "")
}