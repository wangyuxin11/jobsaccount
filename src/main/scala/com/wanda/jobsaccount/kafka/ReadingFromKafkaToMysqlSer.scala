package com.wanda.jobsaccount.kafka

import java.util.Properties

import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09
import org.apache.flink.streaming.util.serialization.SimpleStringSchema
import org.apache.flink.streaming.util.serialization.DeserializationSchema
import org.apache.flink.streaming.util.serialization.KeyedDeserializationSchemaWrapper

/*
 * 
 * /app/data/flink/flink-1.5.0/bin/flink run -p 4 -d -c com.wanda.jobsaccount.kafka.ReadingFromKafkaToMysqlSer /app/data/jar/jobjaccount/jobsaccount-1.0.jar prod.properties
 * 
 * 
 * {"name":"test","id":1}
 * 
 * 
 */
object ReadingFromKafkaToMysqlSer {

  private val ZOOKEEPER_HOST = "10.213.128.98:2181,10.213.129.58:2181,10.213.129.59:2181"
  private val KAFKA_BROKER = "10.213.129.58:9092,10.213.129.59:9092,10.213.129.60:9092"
  private val TRANSACTION_GROUP = "transaction"

  def main(args: Array[String]) {
    
    /**
     * properties在kafka 0.9.x中，只需要配置两个选项即可, bootstrap.servers\group.id
     */
    val kafkaProps = new Properties()
 // kafkaProps.setProperty("zookeeper.connect", ZOOKEEPER_HOST)
    kafkaProps.setProperty("bootstrap.servers", KAFKA_BROKER)
    kafkaProps.setProperty("group.id", TRANSACTION_GROUP)
 // kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
 // kafkaProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    
    
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.getConfig.enableSysoutLogging();
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.enableCheckpointing(1000L)      //程序中如何设置检查点？  start a checkpoint every 1000 ms
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE) // set mode to exactly-once (this is the default)
    env.getCheckpointConfig.setCheckpointTimeout(60000) // checkpoints have to complete within one minute, or are discarded
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1) // allow only one checkpoint to be in progress at the same time
    
    
    //反序列化Schema，指明如何将kafka中的序列化的数据转换为Flink中的对象
    //val TestDeserializer = new KeyedDeserializationSchemaWrapper[Test]();
    
    val topic = "new"
    
    val flinkKafkaConsumer = new FlinkKafkaConsumer09(topic, new TestDeserializer, kafkaProps)
    
    //Flink提供了一个high level的API来消费kafka的topic中的数据：
    val transaction: DataStream[Test] = env.addSource(flinkKafkaConsumer).name("DataStreamSource_")
    
    transaction.addSink(new MySqlSinkFunction).name("MysqlSink_")
    
    transaction.print()

    env.execute("read from kafka example");
    
  }
}