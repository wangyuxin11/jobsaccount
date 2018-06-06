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
import org.apache.flink.api.common.io.OutputFormat

class ReadingFromKafkaWriteToKafka {

  private val ZOOKEEPER_HOST = "10.213.128.98:2181,10.213.129.58:2181,10.213.129.59:2181"
  private val KAFKA_BROKER = "10.213.129.58:9092,10.213.129.59:9092,10.213.129.60:9092"
  private val TRANSACTION_GROUP = "transaction"

  def main(args: Array[String]) {

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

    val flinkKafkaConsumer = new FlinkKafkaConsumer09(topic, new TestOutputDeserializer, kafkaProps)

    val sourceStream = env.addSource(flinkKafkaConsumer).name("flinkKafkaConsumer")

    sourceStream.print()

    //org.apache.flink.api.common.io.OutputFormat  是接口
    sourceStream.writeUsingOutputFormat(new TestOutputFormat(kafkaProps, true)).name("writeUsingOutputFormat_")

    env.execute("example-read-from-kafka-simpleStringSchema-to-mysql");
  }

}