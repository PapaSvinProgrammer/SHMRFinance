@file:Suppress("UnstableApiUsage")

includeBuild("build-logic")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SHMRFinance"
include(":app")

listOf(
    "common",
    "connectivityState",
    "data",
    "model",
    "navigationRoute",
    "network",
    "ui",
    "utils"
).forEach {
    include(":$it")
    project(":$it").projectDir = file("core/$it")
}

listOf(
    "bankAccount",
    "category",
    "transaction"
).forEach {
    include(":$it")
    project(":$it").projectDir = file("domain/$it")
}

listOf(
    "articles",
    "bankAccountList",
    "bankAccountScreen",
    "createBankAccount",
    "expenses",
    "income",
    "settings",
    "splash",
    "transactionHistory"
).forEach {
    include(":$it")
    project(":$it").projectDir = file("feature/$it")
}
