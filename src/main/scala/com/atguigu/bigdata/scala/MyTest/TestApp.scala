package com.atguigu.bigdata.scala.MyTest

object MyTest {


  def main(args: Array[String]): Unit = {

        var speed_list = scala.collection.mutable.ListBuffer[Double]()
    //
        speed_list += 1
        speed_list += 2
        speed_list += 2
        speed_list += 2
        speed_list += 3

    for(i <- speed_list){
      println(i.getClass)

    }

    //    speed_list += (11,12,13)
    //    speed_list ++= List[Float](1, 2, 3, 4, 5)
    //    speed_list.insert(6,7,8)
        println(speed_list)


//    var dou:Double = 12.34562
//
//    dou.asInstanceOf[Float]
//
//    print(dou)


  }


}

