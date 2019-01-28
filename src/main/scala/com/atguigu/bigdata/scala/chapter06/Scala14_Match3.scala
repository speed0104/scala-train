package com.atguigu.bigdata.scala.chapter06

object Scala14_Match3 {

    def main(args: Array[String]): Unit = {

        // 样例类
        /*
        for (amt <- Array(Dollar(1000.0), Currency(1000.0, "EUR"), Nothing)) {
            val result = amt match {
                case Dollar(v) => "$" + v
                case Currency(_, u) => u
                case Nothing => ""
            }
            println(amt + ": " + result)
        }
        */

        // copy方法
        /*
        val c = Currency(1000.0, "EUR")
        val c1 = c.copy(unit="XXXX")

        println(c1.unit + "=" + c1.value)
        */

        val sale = Bundle("书籍", 10,  Article("漫画", 40), Bundle("文学作品", 20, Article("《阳关》", 80), Article("《围城》", 30)))

        /*
        val result1 = sale match {
            case Bundle(_, _, Article(descr, _), _*) => descr
        }
        println(result1)

        val result2 = sale match {
            case Bundle(_, _, art @ Article(_, _), rest) => (art, rest)
        }
        println(result2)
        */

        def price(it: Item): Double = {
            it match {
                case Article(_, p) => p
                case Bundle(_, disc, its@_*) => its.map(price _).sum - disc
            }
        }

        println(price(sale))

    }
}
abstract sealed class Amount
case class Dollar(value: Double) extends Amount
case class Currency(value: Double, unit: String) extends Amount
case object Nothing extends Amount
case class TestCase1(s : String) extends Amount

abstract class Item
case class Article(description: String, price: Double) extends Item
case class Bundle(description: String, discount: Double, item: Item*) extends Item


