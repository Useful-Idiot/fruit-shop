package com.hmrc

case class MonetaryAmount(value: BigDecimal)

object MonetaryAmount {

  def apply(value: String): MonetaryAmount = new MonetaryAmount(BigDecimal(value))

  val zero: MonetaryAmount = MonetaryAmount("0")

}

sealed trait LineItem {
  def price: MonetaryAmount
}

final case class Orange() extends LineItem {
  override val price = MonetaryAmount("0.25")
}

final case class Apple() extends LineItem() {
  override val price = MonetaryAmount("0.60")
}
