package com.example.shmrfinance

import com.example.core.BankAccount
import com.example.core.Category
import com.example.core.Transaction

val category1 = Category(
    id = 1,
    name = "Аренда квартиры",
    emoji = "\uD83C\uDFE1",
    isIncome = false
)

val category2 = Category(
    id = 1,
    name = "Одежда",
    emoji = "\uD83D\uDC57",
    isIncome = false
)

val category3 = Category(
    id = 1,
    name = "На собачку",
    emoji = "\uD83D\uDC36",
    isIncome = false
)

val category4 = Category(
    id = 1,
    name = "Ремонт квартиры",
    emoji = "РК",
    isIncome = false
)

val category5 = Category(
    id = 1,
    name = "Продукты",
    emoji = "\uD83C\uDF6D",
    isIncome = false
)

val category6 = Category(
    id = 1,
    name = "Спортзал",
    emoji = "\uD83C\uDFCB\uFE0F\u200D♂\uFE0F",
    isIncome = false
)

val category7 = Category(
    id = 1,
    name = "Медицина",
    emoji = "\uD83D\uDC8A",
    isIncome = false
)

val articleList = listOf(
    category1,
    category2,
    category3,
    category3,
    category4,
    category5,
    category6,
    category7
)

val account = BankAccount(
    id = "asdasdsad",
    name = "Основной счет",
    balance = 1000f,
    currency = "RUB",
    createdAt = "",
    updatedAr = ""
)

val transactionList = listOf(
    Transaction(
        id = 0,
        account = account,
        category = category1,
        amount = 100000f,
        transactionDate = "",
        updatedAt = "",
        createdAt = ""
    ),
    Transaction(
        id = 0,
        account = account,
        category = category2,
        amount = 100000f,
        transactionDate = "",
        updatedAt = "",
        createdAt = ""
    ),
    Transaction(
        id = 0,
        account = account,
        category = category3,
        amount = 100000f,
        comment = "Джек",
        transactionDate = "",
        updatedAt = "",
        createdAt = ""
    ),
    Transaction(
        id = 0,
        account = account,
        category = category3,
        comment = "Энни",
        amount = 100000f,
        transactionDate = "",
        updatedAt = "",
        createdAt = ""
    ),
    Transaction(
        id = 0,
        account = account,
        category = category4,
        amount = 100000f,
        transactionDate = "",
        updatedAt = "",
        createdAt = ""
    ),
    Transaction(
        id = 0,
        account = account,
        category = category5,
        amount = 100000f,
        transactionDate = "",
        updatedAt = "",
        createdAt = ""
    ),
    Transaction(
        id = 0,
        account = account,
        category = category6,
        amount = 100000f,
        transactionDate = "",
        updatedAt = "",
        createdAt = ""
    ),
    Transaction(
        id = 0,
        account = account,
        category = category7,
        amount = 100000f,
        transactionDate = "",
        updatedAt = "",
        createdAt = ""
    )
)

val category8 = Category(
    id = 1,
    name = "Зарплата",
    emoji = "",
    isIncome = true
)

val category9 = Category(
    id = 1,
    name = "Подработка",
    emoji = "",
    isIncome = true
)

val incomeList = listOf(
    Transaction(
        id = 0,
        account = account,
        category = category8,
        amount = 500000f,
        transactionDate = "",
        updatedAt = "",
        createdAt = ""
    ),
    Transaction(
        id = 0,
        account = account,
        category = category9,
        amount = 100000f,
        transactionDate = "",
        updatedAt = "",
        createdAt = ""
    )
)