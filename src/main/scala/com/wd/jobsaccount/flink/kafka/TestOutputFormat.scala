package com.wd.jobsaccount.flink.kafka

import java.util.Properties

//import com.wanda.dams.exchange.common.SqlJooq
//import com.wanda.idc.exchange.common.LoggerWrapperUtil._
//import com.wanda.idc.exchange.generated.jooq.tables.records.Top5PriceRecord
import org.apache.flink.api.common.io.OutputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.slf4j.LoggerFactory



//Top5PriceOutputFormat
class TestOutputFormat (properties: Properties, buySide: Boolean) extends OutputFormat[(String, Array[(BigDecimal, BigDecimal)])] {

  private val logger = LoggerFactory.getLogger(classOf[TestOutputFormat])
  
  override def configure(configuration: org.apache.flink.configuration.Configuration): Unit = {}

  override def open(i: Int, i1: Int): Unit = {}

  override def close(): Unit = {}

  override def writeRecord(record: (String, Array[(BigDecimal, BigDecimal)])): Unit = {
    writeTop5PriceRecord(record: (String, Array[(BigDecimal, BigDecimal)]))
  }

  //write Record use phoenix
  def writeTop5PriceRecord(record: (String, Array[(BigDecimal, BigDecimal)])): Unit = {
    val serialValue=if(record._2.length!=0) record._2.map(x=>{x._1+":"+x._2}).mkString(",") else ""
    
    logger.info(s"contracode:[${record._1}],  buySide:[${buySide}], top5:[${serialValue}]")

//    if (SqlJooq.isTop5PriceExist(record._1)) {
//      if (buySide) SqlJooq.updateBuyTop5Price(record._1, serialValue) else SqlJooq.updateSellTop5Price(record._1, serialValue)
//    } else {
//      val top5Record: Top5PriceRecord = new Top5PriceRecord(); top5Record.setContractcode(record._1)
//      if (buySide) top5Record.setBuytop5(serialValue) else top5Record.setSelltop5(serialValue)
//      SqlJooq.insertTop5Price(top5Record)
//    }
    
  }

  def serializeTop5Price(prices: Array[(BigDecimal, BigDecimal)]): Array[Byte] = {
    var result = Bytes.toBytes(prices.size)
    prices.foreach {
      tuple =>
        result = Bytes.add(result, Bytes.toBytes(tuple._1.toString))
        result = Bytes.add(result, Bytes.toBytes(tuple._2.toString))
    }
    result
  }
  
  def serializeTop5Price2String(prices: Array[(BigDecimal, BigDecimal)]): String = {
    var result = Bytes.toBytes(prices.size)
    prices.foreach {
      tuple =>
        result = Bytes.add(result, Bytes.toBytes(tuple._1.toString))
        result = Bytes.add(result, Bytes.toBytes(tuple._2.toString))
    }
    result
    prices.flatMap(x=>{x._1+"_"+x._2}).mkString(",")
  }

}