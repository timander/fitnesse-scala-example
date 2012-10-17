package net.timandersen.fitnesse

import fitlibrary.DoFixture
import fit.Fixture
import fit.Binding
import fit.Parse
import fitnesse.fixtures.RowEntryFixture
import net.timandersen.TransactionFields

object TransactionRowEntryFixture extends TargetedRowEntryFixture {

  override def getTargetObject = new TransactionFields()

  override def enterRow() {
    //setup test data here
  }
}


object ThenBankStatementFixture extends DoFixture {
  def balanceOnWas(date: String): String = {
    date match {
      //faking it for now
      case "08/01/2012" => "$130.00"
      case "09/01/2012" => "$127.75"
    }

  }
}
