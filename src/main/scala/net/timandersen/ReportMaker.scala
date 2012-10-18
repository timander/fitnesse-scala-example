package net.timandersen

import java.util.Date


object ReportMaker extends App {
  def computeBalanceAsOf(date: Date): String = {

    val credits = DataStore.findTransactionsBefore(date)
      .filter(_.transactionType == CREDIT)
      .foldLeft(0.0: BigDecimal)((x, y) => x + y.amount)

    val debits = DataStore.findTransactionsBefore(date)
      .filter(_.transactionType == DEBIT)
      .foldLeft(0.0: BigDecimal)((x, y) => x + y.amount)

    val balance = credits - debits

    "$%.2f".format(balance)
  }
}