package net.timandersen.fitnesse

import fit.Binding
import fit.Parse
import fitnesse.fixtures.RowEntryFixture

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










