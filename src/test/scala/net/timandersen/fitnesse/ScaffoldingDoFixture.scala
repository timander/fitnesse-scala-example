package net.timandersen.fitnesse

import fitlibrary.DoFixture
import fit.Fixture

class ScaffoldingDoFixture extends DoFixture {
  var mode: Mode = GivenMode

  def given(what: String): Fixture = {
    mode = GivenMode
    what match {
      case "transactions" => TransactionRowEntryFixture
      case _ => null
    }
  }

  def when(what: String): Boolean = {
    what match {
      case "the monthly statements are processed" => {
        net.timandersen.ReportMaker.main(Array())
        true
      }
      case _ => false
    }
  }

  def then(what: String): Fixture = {
    mode = ThenMode
    what match {
      case "the bank statement" => ThenBankStatementFixture
      case _ => null
    }
  }

  def and(what: String): Fixture = {
    mode match {
      case GivenMode => given(what)
      case ThenMode => then(what)
    }
  }

}


sealed trait Mode

case object GivenMode extends Mode

case object ThenMode extends Mode
