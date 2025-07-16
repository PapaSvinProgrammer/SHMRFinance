@file:Suppress("UnstableApiUsage")

include(":feature:transactionAnalysis")


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
    "room"
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
    "createTransaction"
).forEach {
    include(":feature:$it")
}
