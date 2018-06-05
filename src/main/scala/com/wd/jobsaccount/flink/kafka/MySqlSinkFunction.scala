package com.wd.jobsaccount.flink.kafka

import java.sql.DriverManager
import java.sql.ResultSet
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction

class MySqlSinkFunction extends RichSinkFunction[Test] {
  
  override def invoke(t: Test): Unit = {
		  Class.forName("org.mariadb.jdbc.Driver")
    //classOf[org.mariadb.jdbc.Driver]

    //    val url = "jdbc:mariadb://10.213.128.98:13306/idc_apiaccount"
    //    val username = new String("idc_apiaccount")
    //    val password = new String("123456")

    val url = "jdbc:mariadb://localhost:3306/apiaccount"
    val username = new String("test")
    val password = new String("123456")

    val conn = DriverManager.getConnection(url, username, password)
    val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)
    
    // do database insert
    try {
      val prep = conn.prepareStatement("INSERT INTO flink_test2(id, name) VALUES (?, ?) ")
      println(t.id)
      println(t.name)
      prep.setDouble(1, t.id)
      prep.setString(2, t.name)
      prep.executeUpdate
    } catch {
      case e: Exception => e.printStackTrace
    } finally {
      conn.close
    }
  }

}