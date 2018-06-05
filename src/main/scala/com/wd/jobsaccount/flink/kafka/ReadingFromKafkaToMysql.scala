package com.wd.jobsaccount.flink.kafka

import java.util.Properties
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09
import org.apache.flink.streaming.util.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.api.common.functions.FilterFunction
import org.apache.flink.api.common.functions.MapFunction


/**
 * 
 * /app/data/flink/flink-1.3.1/bin/flink run -c com.wanda.jobsaccount.kafka.ReadingFromKafkaToMysql /app/data/jar/jobjaccount/jobsaccount-1.0.jar prod.properties
 * 
 * /app/data/flink/flink-1.5.0/bin/flink run -p 4 -d -c com.wanda.jobsaccount.kafka.ReadingFromKafkaToMysql /app/data/jar/jobjaccount/jobsaccount-1.0.jar prod.properties
 * 
 * 
 * String : BigDecimal : String
 * 
 * 
  DROP TABLE IF EXISTS `flink_test`;
  CREATE TABLE `flink_test` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) DEFAULT NULL,
    `bal` decimal(17,2) DEFAULT NULL,
    `note` varchar(200) DEFAULT NULL,
    PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
 * 
 * 
 */
object ReadingFromKafkaToMysql {

  private val ZOOKEEPER_HOST = "10.213.128.98:2181,10.213.129.58:2181,10.213.129.59:2181"
  private val KAFKA_BROKER = "10.213.129.58:9092,10.213.129.59:9092,10.213.129.60:9092"
  private val TRANSACTION_GROUP = "transaction"
  
  def main(args : Array[String]){
    
	  val kafkaProps = new Properties()
			  kafkaProps.setProperty("zookeeper.connect", ZOOKEEPER_HOST)
			  kafkaProps.setProperty("bootstrap.servers", KAFKA_BROKER)
			  kafkaProps.setProperty("group.id", TRANSACTION_GROUP)
//    kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
//    kafkaProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
			  
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.enableCheckpointing(1000L)
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE)
    env.getConfig.enableSysoutLogging();
//	  env.setParallelism(2)
	  
    val topic = "new"
    
    val flinkKafkaConsumer = new FlinkKafkaConsumer09(topic, new SimpleStringSchema(), kafkaProps)
    
    val sourceStream = env.addSource(flinkKafkaConsumer).name("flinkKafkaConsumer")
    
    sourceStream.print()
    
    val regex="""^\d+$""".r  
    
    val sourceStreamTra = sourceStream.filter(new FilterFunction[String]{
        override def filter(value: String): Boolean = {
          value != null && value.length() > 0 && regex.findFirstMatchIn(value.split(":")(1)) != None
        }
      }).map(new MapFunction[String, Tuple3[String, java.math.BigDecimal, String]]{
        override def map(value : String) : Tuple3[String, java.math.BigDecimal, String]={
          val a : Array[String] = value.split(":")
          println(a(0), a(1), a(2))
          return new Tuple3[String, java.math.BigDecimal, String](a(0), new java.math.BigDecimal(a(1)), a(2))
        }
      }).name("filterMapTuple3")
   
    sourceStreamTra.addSink(new MySQLSink).name("mySQLSink")
    
    env.execute("example-read-from-kafka-simpleStringSchema-to-mysql");
  }

}