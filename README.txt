README.txt



https://blog.csdn.net/god_zhi_big/article/details/78231134




kafka + flink 【connectors 配置】
https://ci.apache.org/projects/flink/flink-docs-release-1.4/dev/connectors/kafka.html




flink实例开发-详细使用指南v1.4
https://blog.csdn.net/xu470438000/article/details/79508962



本地：C:\Users\thinkpad\.m2\repository\org\apache\flink


frontOffice

 <repositories>
    <!--<repository>
      <id>GridGain External Repository</id>
      <url>http://www.gridgainsystems.com/nexus/content/repositories/external</url>
    </repository>
    <repository>
      <id>offical</id>
      <name>Maven Official Repository</name>
      <url>http://repo1.maven.org/maven2</url>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
    </repository>-->
    <repository>
      <id>maxxipoint-repo</id>
      <name>Maxxipoint Maven Repository</name>
      <url>http://10.199.192.16:8081/nexus/content/groups/public/</url>
      <releases>
        <enabled>true</enabled>
      </releases>
      <snapshots>
        <enabled>true</enabled>
      </snapshots>
    </repository>
    <repository>
      <id>thirdparty-repo</id>
      <name>Thirdparty Maven Repository</name>
      <url>http://10.199.192.16:8081/nexus/content/repositories/thirdparty/</url>
      <releases>
        <enabled>true</enabled>
      </releases>
      <snapshots>
        <enabled>true</enabled>
      </snapshots>
    </repository>
  </repositories>
  
  
  
===> http://10.199.192.16:8081/nexus/content/groups/public/   org/apache/flink/

flink-connector-kafka-0.10_2.11/ 	Tue May 15 02:17:22 UTC 2018 	  	
flink-connector-kafka-0.8_2.10/ 	Mon Nov 06 10:10:21 UTC 2017 	  	
flink-connector-kafka-0.9_2.10/ 	Tue May 15 10:42:12 UTC 2018 	  	
flink-connector-kafka-0.9_2.11/ 	Tue May 15 02:17:25 UTC 2018 	  	
flink-connector-kafka-base_2.10/ 	Tue May 15 10:42:13 UTC 2018 	  	
flink-connector-kafka-base_2.11/ 	Tue May 15 02:17:26 UTC 2018 	  	
flink-connector-twitter_2.10/ 	Mon Nov 06 10:10:20 UTC 2017 	  	
flink-connectors/ 	Mon Nov 06 10:10:21 UTC 2017 	  	
flink-core/ 	Tue Apr 18 09:04:59 U

  
  
===> http://maven.intra.ffan.com/nexus/#view-repositories;thirdparty~browseindex
<dependency>
  <groupId>org.apache.flink</groupId>
  <artifactId>flink-connector-kafka_2.11</artifactId>
  <version>0.10.2</version>
</dependency>
  
  
  
  
  
===>  https://mvnrepository.com/search?q=flink-connector-kafka
flink-connector-kafka 的库

