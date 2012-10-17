package net.timandersen.fitnesse

import fitlibrary.DoFixture
import fit.Fixture
import fit.Binding
import fit.Parse
import fitnesse.fixtures.RowEntryFixture
import net.timandersen.TransactionFields

class ScaffoldingDoFixture extends DoFixture {
  var mode = Mode.GIVEN

  def given(what: String): Fixture = {
    mode = Mode.GIVEN
    what match {
      case "transactions" => TransactionRowEntryFixture
      case _ => null
    }
  }

  def when(what: String): Boolean = {
    what match {
      case "the monthly statements are processed" => {
        //this would do something a bit more interesting
        true
      }
      case _ => false
    }
  }

  def then(what: String): Fixture = {
    mode = Mode.THEN
    what match {
      case "the bank statement" => ThenBankStatementFixture
      case _ => null
    }
  }

  def and(what: String): Fixture = {
    mode match {
      case Mode.GIVEN => given(what)
      case Mode.THEN => then(what)
    }
  }

}

object Mode extends Enumeration {
  val GIVEN = Value("given")
  val THEN = Value("then")
}


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


abstract class TargetedRowEntryFixture extends RowEntryFixture {
  protected def getTargetObject: AnyRef

  protected override def bind(heads: Parse) {
    super.bind(heads)
    var i: Int = 0
    while (i < columnBindings.length) {
      val binding: Binding = columnBindings(i)
      binding.adapter.target = getTargetObject
      i += 1
    }
  }

  protected override def getTargetClass = getTargetObject.getClass
}