package com.wd.jobsaccount.jooq

import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.impl.DSL;

class Jooq {
  
  def get():DSLContext = {
    DSL.using(getConf())
  }
  
  
  def getConf():Configuration={
//    JooqConfigurationSupplier.getInstance().get();
    null
  }
  
}