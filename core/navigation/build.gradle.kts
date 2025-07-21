plugins {
    id("android-feature-module")
}

dependencies {
    implementation(project(":feature:articles"))
    implementation(project(":feature:bankAccountList"))
    implementation(project(":feature:bankAccountScreen"))
    implementation(project(":feature:createBankAccount"))
    implementation(project(":feature:expenses"))
    implementation(project(":feature:income"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:splash"))
    implementation(project(":feature:transactionHistory"))
    implementation(project(":feature:updateBankAccount"))
    implementation(project(":feature:createTransaction"))
    implementation(project(":feature:updateTransaction"))
    implementation(project(":feature:transactionAnalysis"))
    implementation(project(":feature:synchronizationScreen"))
}