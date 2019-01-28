package com.atguigu.bigdata.scala.chapter06


object Scala12_FoldFunction1 {

    def main(args: Array[String]): Unit = {

        val sentence = "AAAAAAAAAABBBBBBBBCCCCCDDDDDDD"

        // (Map， Char)

        // Map[Char, Int](), A, A, B, B, C, D

        def charCount( map : Map[Char, Int], c : Char ): Map[Char, Int] = {
            map + (c -> (map.getOrElse(c, 0) + 1))
        }

        println(sentence.foldLeft(Map[Char, Int]())(charCount))

    }
}
