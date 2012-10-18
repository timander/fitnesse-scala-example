package net.timandersen

import collection.mutable.ListBuffer
import java.util.Date

object DataStore {

  private val transactions = new ListBuffer[Transaction]

  def addTransaction(transaction: Transaction) {
    transactions += transaction
  }

  def findTransactionsBefore(date: Date): List[Transaction] = {
    transactions.filter(_.date.before(date)).toList
  }

}

case class Transaction(amount: BigDecimal, transactionType: TransactionType, date: Date)

sealed trait TransactionType

case object DEBIT extends TransactionType

case object CREDIT extends TransactionType
