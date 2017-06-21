package com.hmrc

import org.scalatest.{FlatSpecLike, Matchers}

class ApplesAdditionalFreeSpec extends FlatSpecLike with Matchers {

  behavior of "ApplesAdditionalFree offer"

  it should "make no adjustments for empty cart" in {
    val cart = ShoppingCart(Seq())
    val result = ApplesAdditionalFree.applyTo(cart)

    result shouldBe cart
  }

  it should "make no adjustments for cart containing only Oranges" in {
    val cart = ShoppingCart(Seq(Orange(), Orange()))
    val result = ApplesAdditionalFree.applyTo(cart)

    result shouldBe cart
  }

  it should "make correct adjustments for cart containing Apples" in {
    val cart = ShoppingCart(Seq(Orange(), Apple(), Apple(), Orange()))
    val result = ApplesAdditionalFree.applyTo(cart)

    result.lineItems shouldBe Seq(Orange(),
                                  Apple(),
                                  Apple(),
                                  Orange(),
                                  Apple(),
                                  Apple())
    result.priceAdjustment shouldBe MonetaryAmount(
      Apple().price.value * 2 * -1)
  }

}
