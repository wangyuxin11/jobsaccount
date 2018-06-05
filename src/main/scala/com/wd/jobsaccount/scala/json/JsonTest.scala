package com.wd.jobsaccount.scala.json

import com.alibaba.fastjson.JSON


// Json字符串变成list对象，进行过滤之后，再次转为Json字符串

object JsonTest {

  case class JsonClass(key: String, type2: String, name: String, grid: String, condition: String) {
    override def toString: String = {
      if (condition != null) {
        "{\"key\" : \"" + key + "\" , \"type\" : \"" + type2 + "\" , \"name\" : \"" + name + "\" , \"grid\" : " + grid + " , \"condition\" : " + condition + "}"
      } else {
        "{\"key\" : \"" + key + "\" , \"type\" : \"" + type2 + "\" , \"name\" : \"" + name + "\" , \"grid\" : " + grid + "}"
      }
    }
  }

  def main(args: Array[String]): Unit = {

    val json = JSON.parseArray(jsonStr, classOf[JsonClass]).toArray

    val nameSet = Set("企业征信图谱监控") 

    val j = json.map(row => row.asInstanceOf[JsonClass]).filterNot(r => nameSet.contains(r.name)).toList

    println(j.map(row => row.toString).mkString("[", ",", "]"))
  }

  val jsonStr =
    """
      |[{
      | "key": "36069d72-f457-f5f8-62c6-0915ed3ba82f",
      | "type2": "ProjectCreator",
      | "name": "项目创建/监控",
      | "grid": {
      |     "x": 0,
      |     "y": 0,
      |     "w": 6,
      |     "h": 2,
      |     "static": true
      | }
      |}, {
      | "key": "bd4b17c7-6710-014b-bc2d-72a3c1e0f49e",
      | "type2": "ProjectFlow",
      | "name": "企业征信图谱监控",
      | "grid": {
      |     "x": 6,
      |     "y": 2,
      |     "w": 6,
      |     "h": 2
      | },
      | "condition": {
      |     "projectId": 1
      | }
      |}, {
      | "key": "1b787098-3d2c-4589-9c99-1d16a3cf20af",
      | "type2": "ClusterResource",
      | "grid": {
      |     "x": 0,
      |     "y": 6,
      |     "w": 12,
      |     "h": 3
      | }
      |}, {
      | "key": "91491e4c-caec-a29f-6fd6-d5deef0fb50b",
      | "type2": "TaskList",
      | "name": "待办事项",
      | "grid": {
      |     "x": 0,
      |     "y": 2,
      |     "w": 6,
      |     "h": 2
      | }
      |}, {
      | "key": "8dd04eeb-476f-8c87-f6df-99e267e06d02",
      | "type2": "ProjectFlow",
      | "name": "失联修复监控",
      | "grid": {
      |     "x": 6,
      |     "y": 0,
      |     "w": 6,
      |     "h": 2
      | },
      | "condition": {
      |     "projectId": 2
      | }
      |}, {
      | "key": "cca2bdd4-36f3-de8b-041e-1cc9920c951c",
      | "type2": "ProjectFlow",
      | "name": "关系网络监控",
      | "grid": {
      |     "x": 0,
      |     "y": 4,
      |     "w": 6,
      |     "h": 2
      | },
      | "condition": {
      |     "projectId": 3
      | }
      |}, {
      | "key": "f3a449d2-5fbe-2a61-ed34-ce87c5c52266",
      | "type2": "ProjectFlow",
      | "name": "案件调查监控",
      | "grid": {
      |     "x": 6,
      |     "y": 4,
      |     "w": 6,
      |     "h": 2
      | },
      | "condition": {
      |     "projectId": 4
      | }
      |}, {
      | "key": "86276e05-1bec-26bc-981c-f6bc6e49d4de",
      | "type2": "ProjectFlow",
      | "name": "userpin统计自然人监控",
      | "grid": {
      |     "x": 0,
      |     "y": 9,
      |     "w": 6,
      |     "h": 2
      | },
      | "condition": {
      |     "projectId": 5
      | }
      |}]
      |
    """.stripMargin
    
//    System.exit(0);

}