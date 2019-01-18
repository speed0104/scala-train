package com.speed.spark

object ChangeTest {


def parseDouble(s: String): Option[Double] = try { Some(s.toDouble) } catch { case _ => None }


//def parse[T: ParseOp](s: String): Option[T] = try { Some(implicitly[ParseOp[T]].op(s)) }  catch {case _ => None}
///* 隐式转换类型类*/
//case class ParseOp[T](op: String => T)
//implicit val popDouble = ParseOp[Double](_.toDouble)
//implicit val popInt = ParseOp[Int](_.toInt)
//implicit val popLong = ParseOp[Long](_.toLong)
//implicit val popFloat = ParseOp[Float](_.toFloat)

  def main(args: Array[String]): Unit = {


    val dd02="0.234"
//    println(dd02.isInstanceOf[String])   // 判断是否为String类型

    val eed = parseDouble(dd02) match {            //正确的方式
      case Some(t)=>t
      case None=>None
    }

    println(eed.getClass)

//    println(parse[Double](dd02))         // 正确的转换方式


//    println(dd02.asInstanceOf[Double])   // 错误的转换方式：强制类型转换：java.lang.String cannot be cast to java.lang.Double
//    println(dd02.toFloat)                // 错误的转换方式，会抛出异常：  java.lang.String cannot be cast to java.lang.Double




  }




}