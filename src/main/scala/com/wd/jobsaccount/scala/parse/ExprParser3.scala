package com.wd.jobsaccount.scala.parse


import scala.util.parsing.combinator._

//class ExprParser3 extends RegexParsers{
//	val number="[0-9]+".r
//	def expr:Parser[Int]=term ~ opt(("+" | "-") ~ expr) ^^ {
//		case t ~ None => t
//		case t ~ Some("+" ~ e) => t+e
//		case t ~ Some("-" ~ e) => t-e
//	}
//	def term:Parser[Int]=factor ~ rep("*" ~> factor) ^^ {
//		case f ~ r => f*r.product
//	}
//	def factor:Parser[Int]=number ^^ {_.toInt} | "(" ~> expr <~ ")"
//}
//
//object Parser_ex3 extends App{
//	val parser=new ExprParser3
//	val result=parser.parseAll(parser.expr,"3-4*5") //"3-4-5"会出错，后面例子有解决办法
//	if(result.successful) 
//		println(result.get)
//	val result2=parser.parse(parser.expr,"3-4/5") //从左解析，直到找不到可以解析的。解析不了"/"，会停下来。
//	if(result2.successful) 
//		println(result2.get)
//}