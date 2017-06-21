package com.hmrc

import scala.util.{Failure, Try}

object Checkout {

  def calculatePrice(
      cart: ShoppingCart,
      applicableOffers: Seq[Offer] = Seq()): Try[MonetaryAmount] = {
    if (cart == null)
      Failure(new IllegalArgumentException("shopping cart cannot be null!"))
    else
      Try {
        val cartAfterOffers = applyOffers(cart, applicableOffers)
        MonetaryAmount(
          calculateBasePrice(cartAfterOffers).value + cartAfterOffers.priceAdjustment.value)
      }
  }

  private def applyOffers(cart: ShoppingCart,
                          appliedOffers: Seq[Offer]): ShoppingCart = {
    if (appliedOffers.isEmpty) cart
    else
      appliedOffers.foldLeft(cart)((currentCart, offer) =>
        offer.applyTo(currentCart))
  }

  private def calculateBasePrice(cart: ShoppingCart): MonetaryAmount = {
    cart.lineItems.foldLeft(MonetaryAmount.zero)((currentTotal, nextItem) =>
      MonetaryAmount(currentTotal.value + nextItem.price.value))
  }
}
