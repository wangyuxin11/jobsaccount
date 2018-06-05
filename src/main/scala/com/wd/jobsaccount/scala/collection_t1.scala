package com.wd.jobsaccount.scala

/**
 * 
 * 
 * 
 * scala中map与flatMap浅析
 * https://blog.csdn.net/bitcarmanlee/article/details/52983607
 * 
 * 
 */
object collection_t1 {
  
  def flatMap1(): Unit = {
    val li = List(1,2,3)
    val res = li.flatMap(x => x match {
      case 3 => List('a','b')
      case _ => List(x*5)     //_表示其他的每一个集合中的元素
    })
    println(res)
  }

  def map1(): Unit = {
    val li = List(1,2,3)
    val res = li.map(x => x match {
      case 3 => List('a','b')
      case _ => x*2
    })
    println(res)
  }

  def main(args: Array[String]): Unit = {
    flatMap1()
    map1()
  }
  
}