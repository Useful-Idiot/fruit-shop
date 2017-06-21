package com.hmrc

import org.scalatest.{FlatSpecLike, Matchers}

import scala.util.Failure

class CheckoutSpec extends FlatSpecLike with Matchers {

  behavior of "Checkout"

  val success = 'Success
  val failure = 'Failure

  it should "fail for null input" in {

    val result = Checkout.calculatePrice(null)

    result shouldBe failure
  }

  it should "return 0 for empty shopping cart" in {
    val total = Checkout.calculatePrice(ShoppingCart(Seq()))

    total shouldBe success
    total.get shouldBe MonetaryAmount.zero
  }

  it should "calculate correct price" in {
    val items: Seq[LineItem] = Seq(Apple(), Apple(), Orange(), Apple())
    val total = Checkout.calculatePrice(ShoppingCart(items))

    total shouldBe success
    total.get shouldBe MonetaryAmount("2.05")
  }

}
