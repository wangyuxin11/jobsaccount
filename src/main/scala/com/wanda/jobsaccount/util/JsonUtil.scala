package com.wanda.jobsaccount.util

import com.alibaba.fastjson.JSONObject
import com.alibaba.fastjson.serializer.JSONSerializer
import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.JSON

import java.util.HashMap
import com.wanda.jobsaccount.kafka.Test

object JsonUtil {
  def main(args: Array[String]) {

    //json对象转成json字符串
    val json = new JSONObject();
    json.put("page", 1)
    json.put("pageSize", 10)
    println(json.toString())

    //还有Map集合在放进JSONObject，变成json字符串
    val m = new HashMap[String, Any]
    m.put("id", 1)
    m.put("name", "test")
    json.clear()
    json.putAll(m)
    println(json.toString())
    
    
//    val t = new Test(10, "ttt")
    //val j = JSON.toJSONString(t, SerializerFeature)
    //println(JSON.toJSON(t))
//    JSON.toJ
    

    
    /**
     * 
     * 
     */
    
    //json字符串变成一个类对象，要求字符串必须符合json格式
    //JSONObject json = JSONObject.parseObject(str)
    
    
    //后面都是在json类型的字符串已经转出JSONObject的基础上。
    //Object o = JSON.toJavaObject(json,Object.class);  
    //Object o = json.toJavaObject(Object.class);(二选一) 
    
    //得到List集合的两种方法。  
    //JSONArray jsonArr = json.getJSONArray("");(从JSONObject得到JSONArray对象)  
    //List<T> list = jsonArr.toJavaList(T.class);  
    
    //如果本身是数组形式的字符串可以使用下面的方法
    //List<T.class> list = JSON.parseArray(str,T.class);
    
    
  }
}