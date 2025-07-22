@file:Suppress("UnstableApiUsage")

include(":core:security")


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
    "data",
    "model",
    "network",
    "ui",
    "utils",
    "coreComponent",
    "localFactory",
    "room",
    "syncWorker",
    "navigation",
    "charts"
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
    "transactionHistory",
    "updateBankAccount",
    "updateTransaction",
    "createTransaction",
    "synchronizationScreen",
    "transactionAnalysis",
    "otpScreen"
).forEach {
    include(":feature:$it")
}
