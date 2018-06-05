package com.wanda.jobsaccount.kafka

//import scala.util.parsing.json._

import java.io.ObjectInputStream
import java.io.ByteArrayInputStream
import java.io.ObjectOutputStream
import java.io.ByteArrayOutputStream

import com.alibaba.fastjson.JSONObject

class Test {

  var id: Double = _
  var name: String = _

  //  def this(name : String){
  //    //辅助构造器
  //    this()
  //    this.name = name
  //  }

  def this(id: Double, name: String) {
    this()
    this.id = id
    this.name = name
  }

  def main(args: Array[String]) {
//    JSONObject json = new JSONObject();
//    json.put("page", 1);
//    json.put("pageSize", 10);
//    json.toJSONString();
  }

}