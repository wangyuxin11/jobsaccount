package com.wanda.jobsaccount.kafka

import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream

import scala.util.parsing.json._

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.java.typeutils.TypeExtractor
import org.apache.flink.streaming.util.serialization.DeserializationSchema
import org.apache.flink.streaming.util.serialization.SerializationSchema

case class User(id: Long, organization_id: Long, username: String, role_ids: String, locked: Boolean)

class TestDeserializer extends DeserializationSchema[Test] with SerializationSchema[Test] {

//  def parseLong(s: String): Option[Long] = try { Some(s.toLong) } catch { case _ => None }
  
  override def deserialize(bytes: Array[Byte]): Test = {
    try {
      val jsonStr = new String(bytes, "utf-8")
      println(jsonStr)
      val jsonValue : Option[Any] = JSON.parseFull(jsonStr)
      println(jsonValue)
      val map: Map[String, Any] = jsonValue.get.asInstanceOf[Map[String, Any]]
      println(map)
      val id = map.get("id").get.asInstanceOf[Double]
      val name = map.get("name").get.asInstanceOf[String]
      
      val t = new Test(id, name)
      t
    } catch {
      case e: Exception =>
        println("deSerialize" + e)
        null
      //      case e: MalformedURLException =>
      //        new URL("http://www.scalalang.org")
    }
    
    /**
    try {
      val bais = new ByteArrayInputStream(bytes)
      val ois = new ObjectInputStream(bais)
      val obj = ois.readObject().asInstanceOf[Test]
      bais.close()
      ois.close()
      obj
    } catch {
      case e: Exception =>
        println("deSerialize" + e)
        null
    }
    */
    
  }

  override def isEndOfStream(nextElement: Test): Boolean = {
    false
  }

  override def serialize(t: Test): Array[Byte] = {
    try {
      val baos = new ByteArrayOutputStream();
      val oos = new ObjectOutputStream(baos);
      oos.writeObject(t)
      val array: Array[Byte] = baos.toByteArray
      oos.close()
      baos.close()
      array
    } catch {
      case e: Exception =>
        println(e)
        null
    }
  }

  override def getProducedType: TypeInformation[Test] = {
    TypeExtractor.getForClass(classOf[Test])
  }

}

