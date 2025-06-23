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
include(":core:model")
include(":core:common")
include(":core:data")
include(":core:network")
include(":domain")
include(":domain:transaction")
include(":domain:bankAccount")
include(":domain:category")
include(":feature")
include(":feature:articles")
include(":feature:bankAccountList")
include(":feature:createBankAccount")
include(":feature:expenses")
include(":feature:income")
include(":feature:settings")
include(":feature:transactionHistory")
include(":feature:splash")
include(":core:ui")
include(":core:utils")
include(":core:navigationRoute")
include(":feature:bankAccountScreen")
include(":core:connectivityState")
