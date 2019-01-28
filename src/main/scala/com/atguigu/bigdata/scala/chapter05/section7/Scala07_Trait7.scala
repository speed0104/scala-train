package com.atguigu.bigdata.scala.chapter05.section7

object Scala07_Trait7 {

    def main(args: Array[String]): Unit = {

        val ff = new FF() with CC with DD

        /*
          E....
          F....
          A....
          B....
          C....
          D....
         */
        //println(ff)

        val ff1 = new FF()
        println(ff1)

        /*
          E....
          A....
          B....
          C....
          D....
          F....
         */

    }
}

trait AA {
    println("A...")
}

trait BB extends  AA {
    println("B....")
}

trait CC extends  BB {
    println("C....")
}
trait DD extends  BB {
    println("D....")
}

class EE {
    println("E...")
}
class FF extends EE with CC with DD {
    println("F....")
}

