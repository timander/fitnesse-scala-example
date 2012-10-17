package net.timandersen.fitnesse

import fitlibrary.DoFixture
import fit.Fixture
import fit.Binding
import fit.Parse
import fitnesse.fixtures.RowEntryFixture
import net.timandersen.TransactionFields

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










