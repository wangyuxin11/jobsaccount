package com.wd.jobsaccount.flink.kafka

object CommonBean {

}

case class CardFreezeRes(data: CardFreezeData, msg: String, code: String) {
  override def toString: String = s"{code=${code}, msg=${msg}, data=${data}}"
}

case class CardFreezeData(quantity: String, transId: String, uid: String) {
  override def toString: String = s"{transId=${transId}, quantity=${quantity}}"
}

case class CardBalanceRes(data: CardBalanceData, msg: String, code: String) {
  override def toString: String = s"{code=${code}, msg=${msg}, data=${data}}"
}

case class CardBalanceData(balance: String, uid: String) {
  override def toString: String = s"{uid=${uid}, balance=${balance}}"
}

case class OrderHistoryMsg(username: String,
                           orderId: String,
                           contracCode: String,
                           contracType: Int,
                           masterExcode: String,
                           slaveExcode: String,
                           quantity: Int,
                           orderTime: Long,
                           price: Long,
                           fillSize: Int,
                           tradeSize: Int,
                           tradeValue: Long,
                           orderType: Int,
                           orderSide: Int,
                           orderStatus: Int,
                           latestTradeTime: Long,
                           latestCancelTime: Long,
                           latestUpdataStatus: Int,
                           latestTradeId: Long,
                           masterFee: Long,
                           slaveFee: Long
                          )