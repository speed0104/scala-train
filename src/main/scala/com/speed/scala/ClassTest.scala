class niu{

}



class Apply extends niu{
  def apply = {
    println(" class apply")
  }

  def test = println("class test")
}

object Apply{
  def apply() = {//注意这里的apply()函数定义哪怕没有参数，也不能省略()
    println("object apply")
    new Apply
  }

}

object main_ extends App{
  val apply = Apply()
  apply.test
}