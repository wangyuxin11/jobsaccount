package com.wd.jobsaccount.util

class ChangeUtil {
  
  /**
   * 判断字符串是否是纯数字组成的串，如果是，就返回对应的数值，否则返回0
   * @param str
   * @return
   */
  def strToInt(str: String): Int = {
    val regex = """([0-9]+)""".r
    val res = str match {
      case regex(num) => num
      case _          => "0"
    }
    val resInt = Integer.parseInt(res)
    resInt
  }

  def main(args: Array[String]): Unit = {
    val res = strToInt("012321312")
    println(res)
  }
  
  
    /**  
    * 第一种类型转换方式  
    *  
    * @param s string  
    * @return 转换后的类  
    */  
  def parseDouble(s: String): Option[Double] = try { Some(s.toDouble) } catch { case _ => None }  
  
  def parseLong(s: String): Option[Long] = try { Some(s.toLong) } catch { case _ => None }
  
  
}