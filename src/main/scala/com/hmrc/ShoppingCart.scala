package com.hmrc

case class ShoppingCart(lineItems: Seq[LineItem],
                        priceAdjustment: MonetaryAmount = MonetaryAmount.zero)
