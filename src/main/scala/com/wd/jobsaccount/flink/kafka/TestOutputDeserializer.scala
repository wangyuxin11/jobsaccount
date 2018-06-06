package com.wd.jobsaccount.flink.kafka

import java.io._
import java.util.zip.{GZIPInputStream, GZIPOutputStream}

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.java.typeutils.TypeExtractor
import org.apache.flink.streaming.util.serialization.{DeserializationSchema, SerializationSchema}

import scala.util.Try


class TestOutputDeserializer extends DeserializationSchema[(String, Array[(BigDecimal, BigDecimal)])] with SerializationSchema[(String, Array[(BigDecimal, BigDecimal)])] {
  
  override def isEndOfStream(nextElement: (String, Array[(BigDecimal, BigDecimal)])): Boolean = {
    false
  }

  override def deserialize(message: Array[Byte]): (String, Array[(BigDecimal, BigDecimal)]) = {
    val r = Try {
      val objectIn = new ObjectInputStream(new GZIPInputStream(new ByteArrayInputStream(message)))
      val restored = objectIn.readObject.asInstanceOf[(String, Array[(BigDecimal, BigDecimal)])]
      objectIn.close()

      restored
    } recover {
      case _ => deserializeOldMessage(message)
    }

    r.get
  }

  private def deserializeOldMessage(message: Array[Byte]): (String, Array[(BigDecimal, BigDecimal)]) = {
    val buffer = new DataInputStream(new ByteArrayInputStream(message))
    val exLength = buffer.readInt
    val exBytes = new Array[Byte](exLength)
    buffer.read(exBytes)
    val exCode = new String(exBytes)
    val size = buffer.readInt
    val prices = new Array[(BigDecimal, BigDecimal)](size)
    for (i <- 0 until size) {
      val price = buffer.readLong()
      val volume = buffer.readLong()
      prices(i) = (price, volume)
    }
    buffer.close()
    (exCode, prices)
  }

  override def serialize(element: (String, Array[(BigDecimal, BigDecimal)])): Array[Byte] = {
    val byteStream = new ByteArrayOutputStream
    val zipStream = new GZIPOutputStream(byteStream)
    val out = new ObjectOutputStream(zipStream)
    out.writeObject(element)
    out.close()

    byteStream.toByteArray
  }

  override def getProducedType: TypeInformation[(String, Array[(BigDecimal, BigDecimal)])] = {
    TypeExtractor.getForClass(classOf[(String, Array[(BigDecimal, BigDecimal)])])
  }

}