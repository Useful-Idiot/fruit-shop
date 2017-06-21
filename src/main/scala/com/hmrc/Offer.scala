package com.hmrc

sealed trait Offer {

  def applyTo(cart: ShoppingCart): ShoppingCart

}

case object ApplesAdditionalFree extends Offer {

  override def applyTo(cart: ShoppingCart): ShoppingCart = {
    val numberOfApples = cart.lineItems.count {
      case _: Apple => true
      case _        => false
    }

    val newItems = cart.lineItems ++ List.fill(numberOfApples)(Apple())
    val priceAdjustment = MonetaryAmount(
      cart.priceAdjustment.value +
        Apple().price.value * -1 * numberOfApples)

    ShoppingCart(newItems, priceAdjustment)
  }

}

case object OrangesThreeForPriceOfTwo extends Offer {

  override def applyTo(cart: ShoppingCart): ShoppingCart = {
    val numberOfOranges = cart.lineItems.count {
      case _: Orange => true
      case _         => false
    }

    val priceAdjustment = MonetaryAmount(
      cart.priceAdjustment.value +
        Orange().price.value * (numberOfOranges / 3) * -1)

    ShoppingCart(cart.lineItems, priceAdjustment)
  }

}
