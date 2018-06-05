package com.wanda.jobsaccount.kafka

import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Date

import org.apache.flink.streaming.api.functions.sink.RichSinkFunction

/**
 * scala bigdecimal 文档
 * https://www.scala-lang.org/api/current/scala/math/BigDecimal$.html
 *
 */
class MySQLSink extends RichSinkFunction[Tuple3[String, java.math.BigDecimal, String]] {
  override def invoke(value: Tuple3[String, java.math.BigDecimal, String]): Unit = {

    val url = "jdbc:mariadb://10.213.128.98:13306/idc_apiaccount"
    val username = new String("idc_apiaccount")
    val password = new String("123456")
    val driver = "org.mariadb.jdbc.Driver"

    //		val url = "jdbc:mariadb://localhost:3306/apiaccount"
    //    val username = new String("test")
    //    val password = new String("123456")

    Class.forName(driver)
    //classOf[org.mariadb.jdbc.Driver]
    val conn = DriverManager.getConnection(url, username, password)
    val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)
    
    // do database insert
    try {
      val prep = conn.prepareStatement("INSERT INTO flink_test(name, bal, note) VALUES (?, ?, ?) ")
      println(value._1)
      println(value._2)
      println(value._3)
      prep.setString(1, value._1)
      prep.setBigDecimal(2, value._2)
      prep.setString(3, value._3)
      prep.executeUpdate
    } catch {
      case e: Exception => e.printStackTrace
    } finally {
      conn.close
    }
  }
}