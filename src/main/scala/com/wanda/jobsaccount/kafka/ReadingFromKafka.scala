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

//flink 1.4
//import org.apache.flink.api.common.serialization.SerializationSchema
//import org.apache.flink.api.common.serialization.DeserializationSchema
//import org.apache.flink.api.common.serialization.SimpleStringSchema

/**
 * https://blog.csdn.net/lmalds/article/details/51780950
 *
 *
 * 1、执行
 * /app/data/flink/flink-1.3.1/bin/flink run -c com.wanda.jobsaccount.kafka.ReadingFromKafka /app/data/jar/jobjaccount/jobsaccount-1.0.jar prod.properties
 *
 * 2、启动一台客户端发message
 * ./bin/kafka-console-producer.sh --broker-list  10.213.128.98:9092,10.213.128.58:9092,10.213.128.59:9092 --topic first_topic
 * ./bin/kafka-console-producer.sh --broker-list  localhost:9092 --topic new
 *
 * 3、启动一台客户端收message
 * ./bin/kafka-console-consumer.sh --zookeeper  10.213.128.98:2181,10.213.129.58:2181,10.213.129.59:2181  --from-beginning --topic first_topic
 *
 *
 * 创建topic
 * ./kafka-topics.sh  --create --zookeeper 10.213.128.98:2181,10.213.129.58:2181,10.213.129.59:2181 --replication-factor 1 --partitions 1 --topic first_topic
 *
 * 查看topic
 * ./kafka-topics.sh --list --zookeeper 10.213.128.98:2181
 *
 *
 *
 *
 * 用Flink消费kafka
 * exchange 已有job 路径启动脚本：/home/exchange
 *
 *
 */
object ReadingFromKafka {

  //  private val ZOOKEEPER_HOST = "master:2181,worker1:2181,worker2:2181"
  //  private val KAFKA_BROKER = "master:9092,worker1:9092,worker2:9092"

  private val ZOOKEEPER_HOST = "10.213.128.98:2181,10.213.129.58:2181,10.213.129.59:2181"
  private val KAFKA_BROKER = "10.213.129.58:9092,10.213.129.59:9092,10.213.129.60:9092"
  private val TRANSACTION_GROUP = "transaction"

  def main(args: Array[String]) {
    
    //properties在kafka 0.9.x中，只需要配置两个选项即可,例如：bootstrap.servers\group.id
    val kafkaProps = new Properties()
    kafkaProps.setProperty("zookeeper.connect", ZOOKEEPER_HOST)
    kafkaProps.setProperty("bootstrap.servers", KAFKA_BROKER)
    kafkaProps.setProperty("group.id", TRANSACTION_GROUP)
    //    kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    //    kafkaProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    
    
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    
    env.getConfig.enableSysoutLogging();
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.enableCheckpointing(1000L)      //程序中如何设置检查点？  start a checkpoint every 1000 ms
    // 高级
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE) // set mode to exactly-once (this is the default)
    env.getCheckpointConfig.setCheckpointTimeout(60000) // checkpoints have to complete within one minute, or are discarded
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1) // allow only one checkpoint to be in progress at the same time
//    env.setParallelism(2)
    
    val topic = "new"

    //topicd的名字是new，schema默认使用SimpleStringSchema()即可
//    val transaction = env
//      .addSource(
//        //new FlinkKafkaConsumer08[String]("new", new SimpleStringSchema(), kafkaProps)
//        new FlinkKafkaConsumer09[String](topic, new SimpleStringSchema(), kafkaProps)).name("test.readingFromKafka")

    
    
    val flinkKafkaConsumer = new FlinkKafkaConsumer09(topic, new SimpleStringSchema(), kafkaProps)
    val transaction = env.addSource(flinkKafkaConsumer).name("test_")

    
    
    //反序列化Schema，指明如何将kafka中的序列化的数据转换为Flink中的对象
//    val TestDeserializer : DeserializationSchema[String] = new KeyedDeserializationSchemaWrapper[Test]();
    
    
//    val flinkKafkaConsumer = new FlinkKafkaConsumer09(topic, new TestDeserializer, kafkaProps)
    
    /*
     * Flink提供了一个high level的API来消费kafka的topic中的数据：
     */
//    val transaction: DataStream[Test] = env.addSource(flinkKafkaConsumer)//.name("test_")
    
    
    
//    transaction.writeAsText("/tmp/ReadingFromKafka.txt");
    transaction.print()

    env.execute("read from kafka example");

    /*

Flink1.4.0中反序列化及序列化类变化
Flink1.4.0中，反序列化及序列化时继承的类，有一些被标记为了“@deprecated”，路径上也有变化：

1.AbstractDeserializationSchema

以前路径
org.apache.flink.streaming.util.serialization.AbstractDeserializationSchema

现在路径：
org.apache.flink.api.common.serialization.AbstractDeserializationSchema
 


2.SerializationSchema

以前路径：
org.apache.flink.streaming.util.serialization.SerializationSchema

现在路径：
org.apache.flink.api.common.serialization.SerializationSchema
 
*/

  }
}