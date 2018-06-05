package com.wd.jobsaccount.flink.kafka

import scala.util.parsing.json._

object Te {

  //  def main(args: Array[String]) {
  //    val text = "{\"name\":\"name1\", \"age\":55}"
  //    val json = JSON.parseObject(text)
  //    println(json.get("name"))
  //    println(json.get("age"))
  //    
  //    var t = new Test(1, "tt")
  //    
  //    println()
  //  }

  def main(args: Array[String]): Unit = {

    //    def regJson(json: Option[Any]) = json match {
    //      case Some(map: Map[String, Any]) => map
    //      //      case None => "erro"
    //      //      case other => "Unknow data structure : " + other
    //    }
    //
    //    val str1 = "{\"host\":\"td_test\",\"ts\":1486979192345,\"device\":{\"tid\":\"a123456\",\"os\":\"android\",\"sdk\":\"1.0.3\"},\"time\":1501469230058}"
    //    val jsonS = JSON.parseFull(str1)
    //    val first = regJson(jsonS)
    //    // 获取一级key
    //    println(first.get("host"))
    //    // 获取二级key
    //    val dev = first.get("device")
    //    println(dev)
    //    val sec = regJson(dev)
    //    println(sec.get("tid").toString.replace("Some(", "").replace(")", ""))

    /**
     * scala中自带了一个
     * scala.util.parsing.json.JSON
     * 然后可以通过JSON.parseFull(jsonString:String)来解析一个json字符串，
     * 如果解析成功的话则返回一个Some(map: Map[String, Any])，
     * 如果解析失败的话返回None。
     * 所以我们可以通过模式匹配来处理解析结果：
     */
//        val str2 = "{\"et\":\"kanqiu_client_join\",\"vtm\":1435898329434,\"body\":{\"client\":\"866963024862254\",\"client_type\":\"android\",\"room\":\"NBA_HOME\",\"gid\":\"\",\"type\":\"\",\"roomid\":\"\"},\"time\":1435898329}"
//        val b = JSON.parseFull(str2)
//        b match {
//          // Matches if jsonStr is valid JSON and represents a Map of Strings to Any
//          case Some(map: Map[String, Any]) => println(map)
//          case None                        => println("Parsing failed")
//          case other                       => println("Unknown data structure: " + other)
//        }

    /*
    
        Map里面按照实际的字符串解析成一个复杂的List，Map嵌套结构，但是这句话返回的是一个Option，没有明确的类型，如果要获取其中的结构，你得这样写代码（代码来源：stackoverflow）
    	
        val json:Option[Any] = JSON.parseFull(jsonString)
        val map:Map[String,Any] = json.get.asInstanceOf[Map[String, Any]]
        val languages:List[Any] = map.get("languages").get.asInstanceOf[List[Any]]
        languages.foreach( langMap => {
        val language:Map[String,Any] = langMap.asInstanceOf[Map[String,Any]]
        val name:String = language.get("name").get.asInstanceOf[String]
        val isActive:Boolean = language.get("is_active").get.asInstanceOf[Boolean]
        val completeness:Double = language.get("completeness").get.asInstanceOf[Double]
    
                      相当反人类，解析过程和json字符串是一个紧耦合，换个字符串又要重新写一套代码。该stackoverflow帖子给出的答案，也没做到解耦。
    
    		使用模式匹配如：case Some(map: Map[String, Any]) => println(map)，编译过程会有warning，
    		据分析jvm在执行的时候会擦除类型（作为一个新手不太懂，请指教），这个过程也很繁琐。
    
    */

    
    /**
     * 简单JSON格式
     */
//    val jsonStr : String = """{"username":"Ricky", "age":"21"}"""
//    val jsonValue : Option[Any] = JSON.parseFull(jsonStr)
//
//    
//    val jsonObj = jsonValue match {
//      case Some(map: Map[String, String]) => map
//      case _                              => println("ERROR jsonStr")
//    }
//    println(jsonObj)
//    if(jsonObj.isInstanceOf[Map[String, Any]]){
//    	val username = jsonObj.asInstanceOf[Map[String, Any]].get("username")    //此处的打印不行，难道上面的match有问题      
//    	println(username)
//    	if(username.isInstanceOf[String]){
//    	 val s : String = username.asInstanceOf[String]
//    	 println("--" + s)
//    	} else {
//    	  println("..")
//    	}
//    }

    //    val map: Map[String, Any] = jsonValue.get.asInstanceOf[Map[String, Any]]
    //    println(map)
    //    
    //    val username : Option[Any] = map.get("username")
    //    println(username)

    //    val myMap = Map("key1" -> "value*")
    //    val value1 = myMap.get("key1")
    //    val value2 = myMap.get("key2")
    //    println(value1)
    //    println(value2)

    
    /**
     *多级JSON字符串
     */
//    val jsonStr = """{"username":"Ricky", "attribute":{"age":21, "weight": 60}}"""
//    val jsonValue = JSON.parseFull(jsonStr)
//
////    val jsonObj = jsonValue match {
////      case Some(map: Map[String, Any]) => map
////      case other                       => println("Error jsonStr")
////    }
//
//    val jsonObj: Map[String, Any] = jsonValue.get.asInstanceOf[Map[String, Any]]
//    println(jsonObj)
//    
//    // 将attribute转换成Map
//    val attrObj = jsonObj.get("attribute").get.asInstanceOf[Map[String, String]]
//    val age = attrObj.get("age")
//    println(age)




    
  }

}