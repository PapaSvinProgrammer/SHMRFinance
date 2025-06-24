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
    include(":core:$it")
}

listOf(
    "bankAccount",
    "category",
    "transaction"
).forEach {
    include(":domain:$it")
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
    include(":feature:$it")
}
