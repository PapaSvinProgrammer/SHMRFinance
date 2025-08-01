package com.example.tranasctionanalysis.domain

import com.example.model.Transaction
import com.example.utils.UseCase
import com.example.utils.format.ConvertData
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PrepareChartData @Inject constructor(
) : UseCase<List<Transaction>, List<Pair<Float, String>>>(Dispatchers.Default) {
    override suspend fun run(params: List<Transaction>): List<Pair<Float, String>> {
        val totalAmount = params.sumOf { it.amount }
        val groupTransactions = params.groupBy { it.category.id }

        val result = mutableListOf<Pair<Float, String>>()

        for ((_, value) in groupTransactions) {
            val amount = value.sumOf { it.amount }
            val sumPercent = amount / totalAmount

            val prettyPercent = ConvertData.createPrettyPercent(amount, totalAmount)
            val categoryName = value[0].category.name

            val label = "$prettyPercent $categoryName"
            result.add(sumPercent.toFloat() to label)
        }

        return result.sortedByDescending { it.first }
    }
}