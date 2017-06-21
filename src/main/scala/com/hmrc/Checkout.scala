package com.hmrc

import scala.util.{Failure, Try}

object Checkout {

  def calculatePrice(cart: ShoppingCart): Try[MonetaryAmount] = {
    if (cart == null)
      Failure(new IllegalArgumentException("shopping cart cannot be null!"))
    else
      Try {
        cart.lineItems.foldLeft(MonetaryAmount.zero)(
          (currentTotal, nextItem) =>
            MonetaryAmount(currentTotal.value + nextItem.price.value))
      }
  }

}
