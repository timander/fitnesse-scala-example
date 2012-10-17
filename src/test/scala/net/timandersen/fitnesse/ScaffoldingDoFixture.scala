package net.timandersen.fitnesse

import fitlibrary.DoFixture
import fit.Fixture
import fit.Binding
import fit.Parse
import fitnesse.fixtures.RowEntryFixture
import net.timandersen.TransactionFields

class ScaffoldingDoFixture extends DoFixture {
  var mode: Mode = Given

  def given(what: String): Fixture = {
    mode = Given
    what match {
      case "transactions" => TransactionRowEntryFixture
      case _ => null
    }
  }

  def when(what: String): Boolean = {
    what match {
      case "the monthly statements are processed" => {
        net.timandersen.Main.main(Array())
        true
      }
      case _ => false
    }
  }

  def then(what: String): Fixture = {
    mode = Then
    what match {
      case "the bank statement" => ThenBankStatementFixture
      case _ => null
    }
  }

  def and(what: String): Fixture = {
    mode match {
      case Given => given(what)
      case Then => then(what)
    }
  }

}


sealed trait Mode
case object Given extends Mode
case object Then extends Mode
