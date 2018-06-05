package com.wanda.jobsaccount.scala.parse

import scala.util.parsing.combinator._

//class Expr
//case class Number(value:Int) extends Expr
//case class Operator(op:String,left:Expr,right:Expr) extends Expr
//
//class ExprParser4 extends RegexParsers{
//	val wholeNumber="[0-9]+".r
//	def expr:Parser[Expr]=term ~ opt(("+" | "-") ~ expr) ^^ {
//		case t ~ None => t
//		case t ~ Some("+" ~ e) => Operator("+",t,e)
//		case t ~ Some("-" ~ e) => Operator("-",t,e)
//	}
//	def term:Parser[Expr]=(factor ~ opt("*" ~> term)) ^^ {
//		case a ~ None => a
//		case a ~ Some(b) => Operator("*",a,b)
//	}
//	def factor:Parser[Expr]=wholeNumber ^^ (n => Number(n.toInt)) | "(" ~> expr <~ ")"
//}
//
//object Parser_ex4 extends App{
//	val parser=new ExprParser4
//	val result=parser.parseAll(parser.expr,"3-4*5") //"3-4-5"会出错，后面例子有解决办法
//	if(result.successful) 
//		println(result.get)
//}