package com.wd.jobsaccount.util

import org.apache.flink.api.java.utils.ParameterTool;

import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
//import scala.util.Properties
import java.util.Properties
//import scala.util.Try

//import java.util._;

/**
 * 配置文件加载工具，支持的配置方式包括：
 * 1. 通过程序参数直接配置任意参数，格式为：--key value。
 * 2. 通过程序 --conf参数指定properties文件路径。
 * 3. 通过JVM -Dconfig.file=指定的properties文件。
 * 4. 通过环境变量指定“EXCHANGE_CONF_DIR”，加载在“EXCHANGE_CONF_DIR”路径下任意properties文件。
 * 5. 默认配置文件：Classpath下的exchange.properties
 * 优先级依次递减。
 */
class ConfigurationTool {

//  private static final String JVM_CONFIG_ARG = "config.file";
  
  

  def get():Properties = {
    this.synchronized {
        val JVM_CONFIG_ARG = "config.file";
        val file = System.getProperty(JVM_CONFIG_ARG);
        if (file != null) {
          val sysProps = ParameterTool.fromSystemProperties().getProperties();
          return sysProps
        }
        null
    }
  }

//  def fromJVMConfigFile(): Properties = {
//    val JVM_CONFIG_ARG = "config.file";
//    val file = System.getProperty(JVM_CONFIG_ARG);
//    if (file != null) {
//      val props1: Properties = getSystemProperties();
//      //      val p: Properties = fromPropertiesFile(file);
//      //      if (p != null)
//      //        props.putAll(p);
//      props1
//    } else {
//      null
//    }
//  }
//    
//    def getSystemProperties():Properties= {
//      ParameterTool.fromSystemProperties().getProperties();
//    }

    
//    def fromPropertiesFile( file:String):Properties= {
//      val props:Properties = null;
//      try{
//        props = ParameterTool.fromPropertiesFile(file).getProperties();
//        
//      }catch {
//        case e:Exception => e.printStackTrace()
//      }
//      props 
//    }

}