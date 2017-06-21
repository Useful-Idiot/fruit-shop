package com.hmrc

import org.scalatest.{FlatSpecLike, Matchers}

class OrangesThreeForPriceOfTwoSpec extends FlatSpecLike with Matchers {

  behavior of "OrangesThreeForPriceOfTwo offer"

  it should "make no adjustments for empty cart" in {
    val cart = ShoppingCart(Seq())
    val result = OrangesThreeForPriceOfTwo.applyTo(cart)

    result shouldBe cart
  }

  it should "make no adjustments for cart containing only Apples" in {
    val cart = ShoppingCart(Seq(Apple(), Apple()))
    val result = OrangesThreeForPriceOfTwo.applyTo(cart)

    result shouldBe cart
  }

  it should "make correct adjustments for cart containing Oranges" in {
    val cart = ShoppingCart(Seq(Apple()) ++ List.fill(8)(Orange()))
    val result = OrangesThreeForPriceOfTwo.applyTo(cart)

    result.lineItems shouldBe cart.lineItems
    result.priceAdjustment shouldBe MonetaryAmount(
      Orange().price.value * -1 * 2)
  }

}
