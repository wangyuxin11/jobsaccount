package com.wd.jobsaccount.scala.parse

import scala.util.parsing.combinator._

//class ExprParser1 extends RegexParsers {
//  val number = "[0-9]+".r
//  def expr: Parser[Any] = term ~ opt(("+" | "-") ~ expr)
//  def term: Parser[Any] = factor ~ rep("*" ~ factor)
//  def factor: Parser[Any] = number | "(" ~ expr ~ ")"
//}
//
//object Parser_ex1 extends App {
//  val parser = new ExprParser1
//  val result = parser.parseAll(parser.expr, "3-4*5") //"3-4-5"会出错，后面例子有解决办法
//  if (result.successful)
//    println(result.get)
//  val result2 = parser.parse(parser.expr, "3-4/5") //从左解析，直到找不到可以解析的。解析不了"/"，会停下来。
//  if (result2.successful)
//    println(result2.get)
//}

