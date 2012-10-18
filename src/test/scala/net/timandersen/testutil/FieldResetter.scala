package net.timandersen.testutil

object FieldResetter {
  def reset(o: AnyRef) {
    val tempClass = o.getClass
    val template = tempClass.newInstance
    tempClass.getDeclaredFields.foreach(field => {
      field.setAccessible(true)
      field.set(o, field.get(template))
    })
  }
}