package com.wd.jobsaccount.util

import java.sql.{ Connection, PreparedStatement, ResultSet }
import com.alibaba.druid.pool.DruidDataSource

object ConnectPoolUtil {

  private var ds: DruidDataSource = null

  /**
   * 创建数据源
   * @return
   */
  def getDataSource(): DruidDataSource = {
    if (ds == null) {
      ds = new DruidDataSource()
      ds.setDriverClassName("com.mysql.jdbc.Driver")
      ds.setUrl("jdbc:mysql://localhost:3306/school")
      ds.setUsername("root")
      ds.setPassword("123456")
      ds.setMaxActive(200) //设置最大并发数
      ds.setInitialSize(30) //数据库初始化时，创建的连接个数
      ds.setMinIdle(50) //最小空闲连接数
      ds.setMaxIdle(200) //数据库最大连接数
      ds.setMaxWait(1000)
      ds.setMinEvictableIdleTimeMillis(60 * 1000) //空闲连接60秒中后释放
      ds.setTimeBetweenEvictionRunsMillis(5 * 60 * 1000) //5分钟检测一次是否有死掉的线程
      ds.setTestOnBorrow(true)
    }
    ds
  }

  /**
   * 释放数据源
   */
  def shutDownDataSource() {
    if (ds != null) {
      ds.close()
    }
  }

  /**
   * 获取数据库连接
   * @return
   */
  def getConnection(): Connection = {
    var con: Connection = null
    try {
      if (ds != null) {
        con = ds.getConnection()
      } else {
        con = getDataSource().getConnection()
      }
    } catch {
      case e: Exception => println(e.getMessage)
    }
    con
  }

  /**
   * 关闭连接
   */
  def closeCon(rs: ResultSet, ps: PreparedStatement, con: Connection) {
    if (rs != null) {
      try {
        rs.close()
      } catch {
        case e: Exception => println(e.getMessage)
      }
    }
    if (ps != null) {
      try {
        ps.close()
      } catch {
        case e: Exception => println(e.getMessage)
      }
    }
    if (con != null) {
      try {
        con.close()
      } catch {
        case e: Exception => println(e.getMessage)
      }
    }
  }
}