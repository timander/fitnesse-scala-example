package net.timandersen.fitnesse

import fitlibrary.DoFixture
import net.timandersen._
import net.timandersen.Transaction
import java.text.SimpleDateFormat
import testutil.TransactionFields

object TransactionRowEntryFixture extends TargetedRowEntryFixture {


  val fields = new TransactionFields()

  override def getTargetObject = fields

  override def enterRow() {

    val transactionType: TransactionType = fields.transactionType match {
      case "Debit" => DEBIT
      case "Credit" => CREDIT
    }

    val transaction = Transaction(
      BigDecimal(fields.amount.replaceAll("\\$", "")),
      transactionType,
      new SimpleDateFormat("MM/dd/yyyy").parse(fields.date)
    )

    DataStore.addTransaction(transaction)
    fields.reset()
  }
}


object ThenBankStatementFixture extends DoFixture {
  def balanceOnWas(date: String): String = {
    ReportMaker.computeBalanceAsOf(new SimpleDateFormat("MM/dd/yyyy").parse(date))
  }
}
