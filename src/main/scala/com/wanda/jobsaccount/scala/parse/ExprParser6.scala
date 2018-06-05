package com.wanda.jobsaccount.scala.parse

import scala.util.parsing.input._
import scala.util.parsing.combinator._

//class ExprParser6 extends RegexParsers with PackratParsers {
//  lazy val ones: PackratParser[Any] = ones ~ "1" ^^ { case a ~ b => a + b } | "1"
//
//  def parseAll[T](p: Parser[T], input: String) =
//    phrase(p)(new PackratReader(new CharSequenceReader(input)))
//
//  val number = "[0-9]+".r
//  /*
//	lazy val expr:PackratParser[Any]=opt(log(expr)("expr:") ~ ("+" | "-")) ~ term
//	lazy val term:PackratParser[Any]=factor ~ rep("*" ~ factor)
//	lazy val factor:PackratParser[Any]=number | "(" ~ expr ~ ")"
//	*/
//  //(Some(((Some(((None~(3~List()))~-))~(4~List()))~-))~(5~List())) //在不知道如何做的时候，参考parser_ex.scala打印出解析结果
//  //opt(log(expr)("expr:") ~ ("+" | "-")) ~ term
//  /*
//	trying expr: at scala.util.parsing.combinator.PackratParsers$PackratReader@16b83
//	cc
//	expr: --> [1.1] failure: Base Failure
//	
//	3-4-5
//	^
//	trying expr: at scala.util.parsing.combinator.PackratParsers$PackratReader@16b83
//	cc
//	expr: --> [1.2] parsed: (None~(3~List()))
//	trying expr: at scala.util.parsing.combinator.PackratParsers$PackratReader@16b83
//	cc
//	expr: --> [1.4] parsed: (Some(((None~(3~List()))~-))~(4~List()))
//	trying expr: at scala.util.parsing.combinator.PackratParsers$PackratReader@16b83
//	cc
//	expr: --> [1.6] parsed: (Some(((Some(((None~(3~List()))~-))~(4~List()))~-))~(5~L
//	ist()))
//	(Some(((Some(((None~(3~List()))~-))~(4~List()))~-))~(5~List()))
//	*/
//  lazy val expr: PackratParser[Int] = opt(expr ~ ("+" | "-")) ~ term ^^ {
//    case (None ~ (r))            => r //记住这个吧：(3~List())是用(r)来匹配，并且r就表示3，试出来的。
//    case (Some((e ~ "+")) ~ (r)) => e + r
//    case (Some((e ~ "-")) ~ (r)) => e - r
//  }
//  lazy val term: PackratParser[Int] = factor ~ rep("*" ~> factor) ^^ {
//    case f ~ r => f * r.product
//  }
//  lazy val factor: PackratParser[Int] = number ^^ { _.toInt } | "(" ~> expr <~ ")"
//}
//
//object Parser_ex6 extends App {
//  val parser = new ExprParser6
//  val result = parser.parseAll(parser.expr, "3-4-5")
//  if (result.successful)
//    println(result.get)
//  val result2 = parser.parseAll(parser.expr, "3-4*5")
//  if (result2.successful)
//    println(result2.get)
//  val result3 = parser.parseAll(parser.ones, "111111")
//  if (result3.successful)
//    println(result3.get)
//}
