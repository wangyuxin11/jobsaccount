package com.wd.jobsaccount.jooq


import org.jooq.util.GenerationTool;
//import org.jooq.util.jaxb.*;

import java.io.File;


class JooqCodeGenerator {
  def main(args: Array[String]) {
    /**
     * java代碼
    Configuration configuration = new Configuration().withJdbc(
        new Jdbc().withDriver(MariaDBConf.getInstance().getDriver())
            .withUrl(MariaDBConf.getInstance().getUrl())
            .withUser(MariaDBConf.getInstance().getUsername())
            .withPassword(MariaDBConf.getInstance().getPassword()))
        .withGenerator(
            new Generator().withGenerate(new Generate().withDaos(true))
                .withDatabase(new Database()
                    .withName("org.jooq.util.mariadb.MariaDBDatabase")
                    .withIncludes(".*").withExcludes("")
                    .withInputSchema("public")
                )
                .withTarget(new Target().withPackageName(
                    "com.wanda.idc.exchange.generated.jooq")
                    .withDirectory(
                        //需要配置成自己工程对应的目录。
                        "common"+ File.separator+"src"+File.separator+"main"+File.separator+"java")
                ));

    GenerationTool.generate(configuration);
     */
    
  }
}