package com.wanda.jobsaccount.kafka

import java.io.ObjectInputStream
import java.io.ByteArrayInputStream
import java.io.ObjectOutputStream
import java.io.ByteArrayOutputStream

//https://blog.csdn.net/pztyz314151/article/details/52958495
object JsonSer {
  
  /**
    * 序列化
    * @param obj
    * @return
    */
  def serialize(obj: Object): Array[Byte] = {
    try {
      val baos = new ByteArrayOutputStream();
      val oos = new ObjectOutputStream(baos);
      oos.writeObject(obj)
      val array: Array[Byte] = baos.toByteArray
      oos.close()
      baos.close()
      array
    }
    catch {
      case e: Exception =>
        println(e)
        null
    }
  }

/**
    * 反序列化
    * @param bytes
    * @return
    */
  def deSerialize(bytes : Array[Byte]) :Object ={
    try{
      val bais = new ByteArrayInputStream(bytes)
      val ois=new ObjectInputStream(bais)
      val obj=ois.readObject()
      bais.close()
      ois.close()
      obj
    }catch {
      case e: Exception =>
        println("deSerialize" + e)
        null
    }
  }
}