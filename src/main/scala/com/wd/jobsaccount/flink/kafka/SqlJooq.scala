package com.wd.jobsaccount.flink.kafka

import java.lang.{Byte => JByte, Long => JLong}
import java.sql.Timestamp
import java.util.Date

//import com.wanda.dams.exchange.domain.OrderHistoryMsg
//import com.wanda.idc.exchange.common.Constants
//import com.wanda.idc.exchange.entity._
//import com.wanda.idc.exchange.generated.jooq.tables._
//import com.wanda.idc.exchange.generated.jooq.tables.records._
//import com.wanda.idc.exchange.messages.PBMessage
//import com.wanda.idc.exchange.postgre.Jooq
//import com.wanda.idc.exchange.status.IOrderStatus
import org.jooq.Condition
import org.jooq.impl.DSL
import org.slf4j.LoggerFactory

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer
//import com.wanda.idc.exchange.common.LoggerWrapperUtil._

/**
  * 这个class主要是将所有数据库的操作都集中于此
  *
  * 现有的其他jooq:
  *     com.wanda.dams.exchange.ledger.jooq.Sqljooq,
  *     com.wanda.dams.exchange.ledger.jooq.TransactionSqljooq
  *     com.wanda.idc.exchange.ledger.jooq.BasicInfoScalaJooq
  *     com.wanda.idc.exchange.ledger.jooq.lockChainSql
  *     com.wanda.idc.exchange.ledger.jooq.IJooq
  *     com.wanda.idc.exchange.ledger.jooq.ScalaJooq
  *     com.wanda.idc.exchange.ledger.jooq.Sqljooq
  * 应陆续统一到com.wanda.dams.exchange.common.SqlJooq这个class来
  *
  * Created by yang.haijun on 2017/5/24.
  */
object SqlJooq {

    private val logger = LoggerFactory.getLogger(this.getClass)

//    def updateReceiveCardStatus(id: Int, freezeID: String, status: Int, methodType: String, cardMessage: String): Unit = {
//        if(methodType == Constants.CARD_RECEIVE) {
//            Jooq.get.update(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN)
//              .set(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN.CARDRECEIVESTATUS, DSL.value(status))
//              .set(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN.CARDMESSAGE, DSL.value(cardMessage))
//              .where(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN.RECEIVEID.eq(id)).execute()
//        } else if(methodType == Constants.CARD_FREEZE) {
//            Jooq.get.update(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN)
//              .set(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN.CARDFREEZESTATUS, DSL.value(status))
//              .set(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN.FREEZEID, DSL.value(freezeID))
//              .set(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN.CARDMESSAGE, DSL.value(cardMessage))
//              .where(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN.RECEIVEID.eq(id)).execute()
//        } else if(methodType == Constants.CARD_UNFREEZE) {
//            Jooq.get.update(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN)
//              .set(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN.CARDUNFREEZESTATUS, DSL.value(status))
//              .set(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN.CARDMESSAGE, DSL.value(cardMessage))
//              .where(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN.FREEZEID.eq(freezeID)).execute()
//        } else if(methodType == Constants.CARD_TRANSFER) {
//            Jooq.get.update(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN)
//              .set(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN.CARDTRANSFERSTATUS, DSL.value(status))
//              .set(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN.CARDMESSAGE, DSL.value(cardMessage))
//              .where(SpReceiveCardTxn.SP_RECEIVE_CARD_TXN.FREEZEID.eq(freezeID)).execute()
//        }
//    }

    /**
      *
      * @param info
      */
//    def InsertIosMsgPush(info: IosMsgPushInfo): Unit = {
//        try {
//            Jooq.get
//              .insertInto(MessagePush.MESSAGE_PUSH)
//              .set(MessagePush.MESSAGE_PUSH.USERNAME, DSL.value(info.getUsername))
//              .set(MessagePush.MESSAGE_PUSH.MESSAGE, DSL.value(info.getMsg))
//              .set(MessagePush.MESSAGE_PUSH.CONTENTAVAILABLE, DSL.value(info.getContentAvailable))
//              .set(MessagePush.MESSAGE_PUSH.BADGE, DSL.value(info.getBadge))
//              .set(MessagePush.MESSAGE_PUSH.SOUND, DSL.value(info.getSound))
//              .set(MessagePush.MESSAGE_PUSH.TITLE, DSL.value(info.getTitle))
//              .set(MessagePush.MESSAGE_PUSH.BUILDERID, DSL.value(info.getBuilderId))
//              .set(MessagePush.MESSAGE_PUSH.CREATETIME, DSL.value(info.getCreateTime))
//              .set(MessagePush.MESSAGE_PUSH.MESSAGETYPE, DSL.value(info.getMsgType))
//              .set(MessagePush.MESSAGE_PUSH.ASSERTTYPE, DSL.value(info.getAssertType))
//              .set(MessagePush.MESSAGE_PUSH.ORDERID, DSL.value(info.getOrderId))
//              .set(MessagePush.MESSAGE_PUSH.LINKURL, DSL.value(info.getLinkUrl))
//              .set(MessagePush.MESSAGE_PUSH.READSTATE, DSL.value(info.getReadState))
//              .set(MessagePush.MESSAGE_PUSH.TRADEID, DSL.value(info.getTradeId))
//              .execute
//        } catch {
//            case e: Exception =>
//                logger.errorF("InsertIosMsgPush error", e)()()
//        }
//    }
//
//    /**
//      *
//      * @param username
//      * @param msgType
//      * @return
//      */
//    def getTotalCount(username: String, msgType: Int): String = {
//        try {
//            Jooq.get
//              .select(DSL.count().as("count"))
//              .from(MessagePush.MESSAGE_PUSH)
//              .where(MessagePush.MESSAGE_PUSH.USERNAME.eq(username) and MessagePush.MESSAGE_PUSH.MESSAGETYPE.eq(msgType))
//                .fetchOne(0,classOf[Int]).toString
//        } catch {
//            case e: Exception =>
//                logger.errorF("getTotalCount error", e)()()
//                "0"
//        }
//    }
//
//    /**
//      *
//      * @param username
//      * @param lastQueryTime
//      * @return
//      */
//    def getUnReadMsgCount(username: String, lastQueryTime: String): List[String] = {
//        try {
//            //消息类型交易消息为1，活动消息为2，系统消息为3
//            val selectObj = getMessagePushJooq(username).and(MessagePush.MESSAGE_PUSH.MESSAGETYPE.eq(1))
//            if (!(lastQueryTime == null || lastQueryTime.isEmpty())) {
//                selectObj.and(MessagePush.MESSAGE_PUSH.CREATETIME.greaterThan(java.lang.Long.parseLong(lastQueryTime)))
//            }
//            val transMsgCount = selectObj.fetchOne(0,classOf[Int])
//
//            val activityMsgCount = getMessagePushJooq(username).and(MessagePush.MESSAGE_PUSH.MESSAGETYPE.eq(2)).fetchOne(0,classOf[Int])
//
//            val systemMsgCount = getMessagePushJooq(username).and(MessagePush.MESSAGE_PUSH.MESSAGETYPE.eq(3)).fetchOne(0,classOf[Int])
//
//            val shareMsgCount = getMessagePushJooq(username).and(MessagePush.MESSAGE_PUSH.MESSAGETYPE.eq(4)).fetchOne(0, classOf[Int])
//
//            val list = new ListBuffer[String]
//            list += transMsgCount.toString
//            list += activityMsgCount.toString
//            list += systemMsgCount.toString
//            list += shareMsgCount.toString
//            list.toList
//        } catch {
//            case e: Exception =>
//                logger.errorF("getUnReadMsgCount error", e)()()
//                Nil
//        }
//    }
//
//    private def getMessagePushJooq(username:String) ={
//        Jooq.get
//          .select(DSL.count().as("count"))
//          .from(MessagePush.MESSAGE_PUSH)
//          .where(MessagePush.MESSAGE_PUSH.USERNAME.eq(username) and MessagePush.MESSAGE_PUSH.READSTATE.eq("1"))
//    }
//
//
//    /**
//      *
//      * @param username
//      * @param orderKind
//      * @param pageselect
//      * @param page
//      * @param pageSize
//      * @return
//      */
//    def getOrderListByUserAndKind(username: String, orderKind: Int, pageselect: Boolean, page: Int, pageSize: Int): List[OrderEntity] = {
//        try {
//            val selectObj = Jooq.get.selectFrom(CardOrderHistory.CARD_ORDER_HISTORY)
//
//            var query = selectObj.where(CardOrderHistory.CARD_ORDER_HISTORY.USERNAME.eq(username))
//
//            if (orderKind == 1) {
//                query = query.and(CardOrderHistory.CARD_ORDER_HISTORY.ORDERSTATUS.lessOrEqual(orderKind.toByte))
//
//            } else if (orderKind == 2) {
//                query = query
//                  .and(CardOrderHistory.CARD_ORDER_HISTORY.ORDERSTATUS.eq(orderKind.toByte)
//                    .or(CardOrderHistory.CARD_ORDER_HISTORY.ORDERSTATUS.eq(4.toByte)))
//
//            } else if (orderKind == 3) {
//                query = query
//                  .and(CardOrderHistory.CARD_ORDER_HISTORY.ORDERSTATUS.eq(orderKind.toByte))
//            }
//
//            val query2 = query.orderBy(CardOrderHistory.CARD_ORDER_HISTORY.ORDERTIME.desc())
//
//
//            if (pageselect) {
//                // 计算起始页和结束页：起始页从0开始,即偏移量
//                val firstPage = page * pageSize
//                //多取1条数据，看是否有下一页
//                val result = query2.limit(pageSize + 1).offset(firstPage).fetch()
//                val list = result.into(classOf[CardOrderHistoryRecord]).toList
//                getOrderEntityList(list)
//            } else {
//                val list = query2.fetch().into(classOf[CardOrderHistoryRecord]).toList
//                getOrderEntityList(list)
//            }
//        } catch {
//            case e: Exception =>
//                logger.errorF("getOrderListByUserAndKind error", e)()()
//                Nil
//        }
//    }
//
//    private def getOrderEntityList(cardOrderList: List[CardOrderHistoryRecord]): List[OrderEntity] = {
//        val resultList = new ListBuffer[OrderEntity]
//        cardOrderList.foreach(f => {
//            val orderEntity = new OrderEntity()
//            orderEntity.setContractCode(f.getContractcode)
//            orderEntity.setOrderId(f.getOrderid)
//            orderEntity.setOrderSide(String.valueOf(f.getOrderside))
//            orderEntity.setOrderStatus(String.valueOf(f.getOrderstatus))
//            orderEntity.setOrderTime(String.valueOf(f.getOrdertime))
//            orderEntity.setPrice(String.valueOf(f.getPrice))
//            orderEntity.setQuantity(String.valueOf(f.getQuantity))
//            orderEntity.setTradeSize(String.valueOf(f.getTradesize))
//            resultList += orderEntity
//        })
//        resultList.toList
//    }
//
//    /**
//      *
//      * @param username
//      * @param orderId
//      * @return
//      */
//    def getOrderDetailByUserAndId(username: String, orderId: String): OrderDetailEntity = {
//        try {
//            val cohr = Jooq.get.selectFrom(CardOrderHistory.CARD_ORDER_HISTORY)
//              .where(CardOrderHistory.CARD_ORDER_HISTORY.USERNAME.eq(username)
//                and CardOrderHistory.CARD_ORDER_HISTORY.ORDERID.eq(orderId))
//              .limit(1)
//              .fetchAny
//              .into(classOf[CardOrderHistoryRecord])
//            getOrderDetailEntity(cohr)
//        } catch {
//            case e: Exception =>
//                logger.errorF("getOrderDetailByUserAndId error", e)()()
//                new OrderDetailEntity
//        }
//    }
//
//    /**
//      *
//      * @param contractCode
//      * @param orderSide
//      * @param orderId
//      * @return
//      */
//    def getTradeDetailByCodeAndId(contractCode: String, orderSide : Int, orderId: String): List[TradeDetailEntity] = {
//        try {
//            var orderIdField = CardTradeHistory.CARD_TRADE_HISTORY.SELLORDERID
//            if (orderSide == 0) {
//                orderIdField = CardTradeHistory.CARD_TRADE_HISTORY.BUYORDERID
//            }
//
//            val res = Jooq.get.selectFrom(CardTradeHistory.CARD_TRADE_HISTORY)
//                .where(CardTradeHistory.CARD_TRADE_HISTORY.CONTRACTCODE.eq(contractCode) and orderIdField.eq(orderId))
//                .fetch
//                .into(classOf[CardTradeHistoryRecord])
//                .toList
//            getTradeDetailEntityList(res)
//        } catch {
//            case e: Exception =>
//                logger.errorF("getOrderDetailByUserAndId error", e)()()
//                ListBuffer[TradeDetailEntity]().toList
//        }
//    }
//
//    /**
//      *
//      * @param username
//      * @param orderTimeStart
//      * @param orderTimeEnd
//      * @param latestTradeTimeStart
//      * @param latestTradeTimeEnd
//      * @param orderSide
//      * @param orderStatus
//      * @param page
//      * @param pageSize
//      * @return
//      */
//    def getOrderListForMarket(username: String, orderTimeStart: String, orderTimeEnd: String,
//                              latestTradeTimeStart: String, latestTradeTimeEnd: String,
//                              orderSide: Int, orderStatus: Int, page: Int, pageSize: Int): List[OrderDetailEntity] = {
//        try {
//            val selectObj = Jooq.get.selectFrom(CardOrderHistory.CARD_ORDER_HISTORY)
//            val selectCountObj = Jooq.get.select(DSL.count().as("count")).from(CardOrderHistory.CARD_ORDER_HISTORY)
//
//            var cd1: Condition = CardOrderHistory.CARD_ORDER_HISTORY.USERNAME.eq(username)
//            if (orderTimeStart != null) {
//                val cd2 = CardOrderHistory.CARD_ORDER_HISTORY.ORDERTIME.greaterOrEqual(JLong.parseLong(orderTimeStart))
//                cd1 = cd1.and(cd2)
//            }
//            if (orderTimeEnd != null) {
//                val cd3 = CardOrderHistory.CARD_ORDER_HISTORY.ORDERTIME.lessOrEqual(JLong.parseLong(orderTimeEnd))
//                cd1 = cd1.and(cd3)
//            }
//            if (latestTradeTimeStart != null) {
//                val cd4 = CardOrderHistory.CARD_ORDER_HISTORY.LATESTTRADETIME.greaterOrEqual(JLong.parseLong(latestTradeTimeStart))
//                cd1 = cd1.and(cd4)
//            }
//            if (latestTradeTimeEnd != null) {
//                val cd5 = CardOrderHistory.CARD_ORDER_HISTORY.LATESTTRADETIME.lessOrEqual(JLong.parseLong(latestTradeTimeEnd))
//                cd1 = cd1.and(cd5)
//            }
//            if (orderSide != 2) {
//                val cd6 = CardOrderHistory.CARD_ORDER_HISTORY.ORDERSIDE.eq(orderSide.toByte)
//                cd1 = cd1.and(cd6)
//            }
//            if (orderStatus != 5) {
//                val cd7 = CardOrderHistory.CARD_ORDER_HISTORY.ORDERSTATUS.eq(orderStatus.toByte)
//                cd1 = cd1.and(cd7)
//            }
//
//            val countQuery = selectCountObj.where(cd1).fetchAny().get("count").toString
//
//            // 计算起始页和结束页：起始页从1开始,即偏移量
//            val firstPage = if ((page - 1) < 0) 0 else (page - 1) * pageSize
//
//            val queryList = selectObj
//              .where(cd1)
//              .limit(pageSize)
//              .offset(firstPage)
//              .fetch
//              .into(classOf[CardOrderHistoryRecord])
//              .toList
//
//            val resultList = new ListBuffer[OrderDetailEntity]
//            queryList.foreach(f => {
//                resultList += getOrderDetailEntity(f)
//            })
//
//            val orderDetail = new OrderDetailEntity()
//            orderDetail.setOrderId(countQuery)
//            resultList += orderDetail
//            resultList.toList
//        } catch {
//            case e: Exception =>
//                logger.errorF("getOrderListForMarket error", e)()()
//                Nil
//        }
//    }
//
//    /**
//      *
//      * @param cohr
//      * @return
//      */
//    private def getOrderDetailEntity(cohr: CardOrderHistoryRecord): OrderDetailEntity = {
//        val orderDetail = new OrderDetailEntity()
//        orderDetail.setContractCode(cohr.getContractcode)
//        orderDetail.setOrderTime(String.valueOf(cohr.getOrdertime))
//        orderDetail.setOrderSide(String.valueOf(cohr.getOrderside))
//        orderDetail.setOrderStatus(String.valueOf(cohr.getOrderstatus))
//        orderDetail.setPrice(String.valueOf(cohr.getPrice))
//        orderDetail.setFillSize(String.valueOf(cohr.getFillsize))
//        orderDetail.setQuantity(String.valueOf(cohr.getQuantity))
//        orderDetail.setTradeSize(String.valueOf(cohr.getTradesize))
//        orderDetail.setTradeValue(String.valueOf(cohr.getTradevalue))
//        orderDetail.setLatestTradeTime(String.valueOf(cohr.getLatesttradetime))
//        orderDetail.setLatestCancelTime(String.valueOf(cohr.getLatestcanceltime))
//        orderDetail.setLatestCancelReason(String.valueOf(cohr.getLatestupdatestatus))
//        orderDetail
//    }
//
//    /**
//      *
//      * @param res
//      * @return
//      */
//    private def getTradeDetailEntityList(res: List[CardTradeHistoryRecord]): List[TradeDetailEntity] = {
//
//        val tradeDetailList: ListBuffer[TradeDetailEntity] = ListBuffer[TradeDetailEntity]()
//        for (r <- res) {
//            val tradeDetail = new TradeDetailEntity()
//            tradeDetail.setTradeTime(r.getTradetime.toString)
//            tradeDetail.setTradeId(r.getTradeid.toString)
//            tradeDetail.setPrice(r.getPrice.toString)
//            tradeDetail.setQuantity(r.getQuantity.toString)
//            tradeDetail.setTradeStatus(r.getTradestatus.toString)
//            tradeDetailList.append(tradeDetail)
//        }
//        tradeDetailList.toList
//    }
//
//    /**
//      *
//      * @return
//      */
//    def get24HoursCancelOrders(): List[OrderEntity] = {
//        try {
//            logger.infoFBusiness("get24HoursCancelOrders for card")()()
//            val selectObj = Jooq.get.select(
//                CardOrderHistory.CARD_ORDER_HISTORY.ORDERID,
//                CardOrderHistory.CARD_ORDER_HISTORY.CONTRACTCODE)
//              .from(CardOrderHistory.CARD_ORDER_HISTORY)
//
//            val currentTime = (new Date()).getTime
//            val timeOutTime = currentTime - 24 * 60 * 60 * 1000
//
//            val queryList = selectObj
//              .where(CardOrderHistory.CARD_ORDER_HISTORY.ORDERSTATUS.in(IOrderStatus.ALL_OPEN.id.toByte, IOrderStatus.PARTIAL_OPEN.id.toByte))
//              .and(CardOrderHistory.CARD_ORDER_HISTORY.ORDERTIME.lessThan(timeOutTime))
//              .fetch
//              .into(classOf[CardOrderHistoryRecord])
//              .toList
//            val resultList = new ListBuffer[OrderEntity]
//            queryList.foreach(f => {
//                val order = new OrderEntity()
//                order.setOrderId(f.getOrderid)
//                order.setContractCode(f.getContractcode)
//                resultList += order
//            })
//            resultList.toList
//        } catch {
//            case e: Exception =>
//                logger.errorF("get24HoursCancelOrders error", e)()()
//                Nil
//        }
//    }
//
//    def writeRecord(pbOrder: PBMessage.PBOrder): Unit = {
//        try {
//            val insertSql = Jooq.get
//              .insertInto(CardOrderHistory.CARD_ORDER_HISTORY)
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.ORDERID, DSL.value(pbOrder.getOrderId))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.CONTRACTCODE, DSL.value(pbOrder.getContractCode))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.CONTRACTTYPE, DSL.value(JByte.valueOf(pbOrder.getContractType)))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.MASTEREX, DSL.value(pbOrder.getMasterEx))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.SLAVEEX, DSL.value(pbOrder.getSlaveEx))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.USERNAME, DSL.value(pbOrder.getBroker))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.QUANTITY, DSL.value(Integer.valueOf(pbOrder.getQuantity)))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.ORDERTIME, DSL.value(pbOrder.getOrderTime))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.PRICE, DSL.value(JLong.valueOf(pbOrder.getPrice)))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.TRADESIZE, DSL.value(0))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.TRADEVALUE, DSL.value(0L))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.FILLSIZE, DSL.value(0))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.ORDERTYPE, DSL.value(pbOrder.getOrderType.getNumber.toByte))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.ORDERSIDE, DSL.value(pbOrder.getOrderSide.getNumber.toByte))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.MERCHANTMASTERFEE, DSL.value(0L))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.MERCHANTSLAVEFEE, DSL.value(0L))
//
//            if (pbOrder.getAction == PBMessage.PBOrder.OrderAction.SUBMIT_FAILURE) {
//                insertSql
//                  .set(CardOrderHistory.CARD_ORDER_HISTORY.ORDERSTATUS, DSL.value(IOrderStatus.ALL_CANCELED.id.toByte))
//                  .set(CardOrderHistory.CARD_ORDER_HISTORY.LATESTCANCELTIME, DSL.value(new Date().getTime))
//                  .set(CardOrderHistory.CARD_ORDER_HISTORY.LATESTUPDATESTATUS, DSL.value(pbOrder.getActionValue.toByte))
//                  .execute
//
//            } else {
//                insertSql
//                  .set(CardOrderHistory.CARD_ORDER_HISTORY.ORDERSTATUS, DSL.value(IOrderStatus.ALL_OPEN.id.toByte))
//                  .execute
//            }
//        } catch {
//            case e: Exception =>
//                logger.errorF("writeRecord error", e)()()
//        }
//    }
//
//    def updateSuccess(updatedTradeSize: Long, updatedTradeValue: Long, updatedFillSize: Int,
//                      updatedLatestTradeTime: Long, orderId: String, user: String, masterFee: Long, slaveFee: Long): Unit = {
//        try {
//            Jooq.get
//              .update(CardOrderHistory.CARD_ORDER_HISTORY)
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.TRADESIZE, DSL.value(updatedTradeSize.toInt))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.TRADEVALUE, DSL.value(updatedTradeValue))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.FILLSIZE, DSL.value(updatedFillSize))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.LATESTTRADETIME, DSL.value(updatedLatestTradeTime))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.MERCHANTMASTERFEE, DSL.value(masterFee))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.MERCHANTSLAVEFEE, DSL.value(slaveFee))
//              .where(CardOrderHistory.CARD_ORDER_HISTORY.USERNAME.eq(user) and CardOrderHistory.CARD_ORDER_HISTORY.ORDERID.eq(orderId))
//              .execute
//        } catch {
//            case e: Exception =>
//                logger.errorF("updateSuccess error", e)()()
//        }
//    }
//
//    def updateSettleFail(updatedFillSize: Int, updatedLatestCancelTime: Long, updatedLatestStatus: Int,
//                         orderId: String, user: String): Unit = {
//        try {
//            Jooq.get
//              .update(CardOrderHistory.CARD_ORDER_HISTORY)
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.FILLSIZE, DSL.value(updatedFillSize))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.LATESTCANCELTIME, DSL.value(updatedLatestCancelTime))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.LATESTUPDATESTATUS, DSL.value(updatedLatestStatus.toByte))
//              .where(CardOrderHistory.CARD_ORDER_HISTORY.USERNAME.eq(user) and CardOrderHistory.CARD_ORDER_HISTORY.ORDERID.eq(orderId))
//              .execute
//        } catch {
//            case e: Exception =>
//                logger.errorF("updateSettleFail error", e)()()
//        }
//    }
//
//    def updateCancelTime(updatedLatestStatus: Int, updatedLatestCancelTime: Long, orderId: String, user: String): Unit = {
//        try {
//            Jooq.get
//              .update(CardOrderHistory.CARD_ORDER_HISTORY)
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.LATESTCANCELTIME, DSL.value(updatedLatestCancelTime))
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.LATESTUPDATESTATUS, DSL.value(updatedLatestStatus.toByte))
//              .where(CardOrderHistory.CARD_ORDER_HISTORY.USERNAME.eq(user) and CardOrderHistory.CARD_ORDER_HISTORY.ORDERID.eq(orderId))
//              .execute
//        } catch {
//            case e: Exception =>
//                logger.errorF("updateCancelTime error", e)()()
//        }
//    }
//
//    def updateStatus(updataStatus: Int, orderId: String, user: String): Unit = {
//        try {
//            Jooq.get
//              .update(CardOrderHistory.CARD_ORDER_HISTORY)
//              .set(CardOrderHistory.CARD_ORDER_HISTORY.ORDERSTATUS, DSL.value(updataStatus.toByte))
//              .where(CardOrderHistory.CARD_ORDER_HISTORY.USERNAME.eq(user) and CardOrderHistory.CARD_ORDER_HISTORY.ORDERID.eq(orderId))
//              .execute
//        } catch {
//            case e: Exception =>
//                logger.errorF("updateStatus error", e)()()
//        }
//    }
//
//    def getOneOrderMsg(userName: String, orderId: String): OrderHistoryMsg = {
//        try {
//            val query = Jooq.get
//              .selectFrom(CardOrderHistory.CARD_ORDER_HISTORY)
//              .where(CardOrderHistory.CARD_ORDER_HISTORY.USERNAME.eq(userName) and CardOrderHistory.CARD_ORDER_HISTORY.ORDERID.eq(orderId))
//              .fetchAny
//              .into(classOf[CardOrderHistoryRecord])
//
//            OrderHistoryMsg(
//                username = query.getUsername,
//                orderId = query.getOrderid,
//                contracCode = query.getContractcode,
//                contracType = converInt(query.getContracttype),
//                masterExcode = query.getMasterex,
//                slaveExcode = query.getSlaveex,
//                quantity = converInt(query.getQuantity),
//                orderTime = converLong(query.getOrdertime),
//                price = converLong(query.getPrice),
//                fillSize = converInt(query.getFillsize),
//                tradeSize = converInt(query.getTradesize),
//                tradeValue = converLong(query.getTradevalue),
//                orderType = converInt(query.getOrdertype),
//                orderSide = converInt(query.getOrderside),
//                orderStatus = query.getOrderstatus.intValue,
//                latestTradeTime = converLong(query.getLatesttradetime),
//                latestCancelTime = converLong(query.getLatestcanceltime),
//                latestUpdataStatus = converInt(query.getLatestupdatestatus),
//                latestTradeId = converLong(query.getLatesttradeid),
//                masterFee = converLong(query.getMerchantmasterfee),
//                slaveFee = converLong(query.getMerchantslavefee)
//            )
//        } catch {
//            case e: Exception =>
//                logger.errorF("getOneOrderMsg error", e)()()
//                OrderHistoryMsg("", "", "", 0, "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
//        }
//    }
//
//    private def converInt(value:Any):Int = {
//        if (value == null) {
//            0
//        } else {
//            value.toString.toInt
//        }
//    }
//
//    private def converInt(value:Integer):Int = {
//        if (value == null) {
//            0
//        } else {
//            value.intValue
//        }
//    }
//
//    private def converLong(value:JLong):Long ={
//        if(value == null){
//            0L
//        }else{
//            value.longValue
//        }
//    }
//
//    def InsertTokenCoin(info: TokenCoinInfo): Unit = {
//        try {
//            Jooq.get
//              .insertInto(TokenCoinStatementBook.TOKEN_COIN_STATEMENT_BOOK)
//              .set(TokenCoinStatementBook.TOKEN_COIN_STATEMENT_BOOK.USERNAME, DSL.value(info.getUsername))
//              .set(TokenCoinStatementBook.TOKEN_COIN_STATEMENT_BOOK.TRADEID, DSL.value(info.getTradeId))
//              .set(TokenCoinStatementBook.TOKEN_COIN_STATEMENT_BOOK.ORDERID, DSL.value(info.getOrderId))
//              .set(TokenCoinStatementBook.TOKEN_COIN_STATEMENT_BOOK.CHANGETIME, DSL.value(info.getChangeTime))
//              .set(TokenCoinStatementBook.TOKEN_COIN_STATEMENT_BOOK.CHANGETYPE, DSL.value(info.getChangeType))
//              .set(TokenCoinStatementBook.TOKEN_COIN_STATEMENT_BOOK.CHANGEAMOUNT, DSL.value(info.getChangeAmount))
//              .set(TokenCoinStatementBook.TOKEN_COIN_STATEMENT_BOOK.CHANGEREASON, DSL.value(info.getChangeReason))
//              .execute
//            logger.infoFBusiness("InsertTokenCoin Success")()()
//        } catch {
//            case e: Exception =>
//                logger.errorF("InsertTokenCoin error", e)()()
//        }
//    }
//    //----------------------------------------提交代码分割线----------------------------------------
//
//    def insertCardOrderHistory(rec: CardOrderHistoryRecord): Boolean = {
//        (Jooq.get.insertInto(CardOrderHistory.CARD_ORDER_HISTORY) set (rec) onDuplicateKeyIgnore() execute()) > 0
//    }
//
//    def insertCardTradeHistory(rec: CardTradeHistoryRecord): Boolean = {
//        (Jooq.get.insertInto(CardTradeHistory.CARD_TRADE_HISTORY) set (rec) onDuplicateKeyIgnore() execute()) > 0
//    }
//
//    def insertTop5Price(rec: Top5PriceRecord): Boolean = {
//        (Jooq.get.insertInto(Top5Price.TOP5_PRICE) set (rec) onDuplicateKeyIgnore() execute()) > 0
//    }
//
//    def insertTradeMinute(rec: TradeMinuteRecord): Boolean = {
//        (Jooq.get.insertInto(TradeMinute.TRADE_MINUTE) set (rec) onDuplicateKeyIgnore() execute()) > 0
//    }
//
//    def insertTokenCoinStatementBook(rec: TokenCoinStatementBookRecord): Boolean = {
//        (Jooq.get.insertInto(TokenCoinStatementBook.TOKEN_COIN_STATEMENT_BOOK) set (rec) onDuplicateKeyIgnore()
//            execute()) > 0
//    }
//
//    def insertMessagePush(rec: MessagePushRecord): Boolean = {
//        (Jooq.get.insertInto(MessagePush.MESSAGE_PUSH) set (rec) onDuplicateKeyIgnore() execute()) > 0
//    }
//
//    def isTradeMinuteExist(tradeMinuteId: String) : Boolean = {
//        val res = Jooq.get().select(TradeMinute.TRADE_MINUTE.TRADEMINUTEID).from(TradeMinute.TRADE_MINUTE)
//            .where(TradeMinute.TRADE_MINUTE.TRADEMINUTEID.eq(tradeMinuteId)).fetchAny()
//        if(res == null) false else true
//    }
    
    
    //Jooq.java ---> package com.wanda.idc.exchange.postgre; @common   
    def isTop5PriceExist(contractCode: String) : Boolean = {
//        val res = Jooq.get().select(Top5Price.TOP5_PRICE.CONTRACTCODE).from(Top5Price.TOP5_PRICE)
//            .where(Top5Price.TOP5_PRICE.CONTRACTCODE.eq(contractCode)).fetchAny()
//        if(res == null) false else true
      true
    }
    
    
    
//
//    def updateBuyTop5Price(contractCode: String, buyTop5Price: String): Unit = {
//        Jooq.get()
//            .update(Top5Price.TOP5_PRICE)
//            .set(Top5Price.TOP5_PRICE.BUYTOP5, DSL.value(buyTop5Price))
//            .where(Top5Price.TOP5_PRICE.CONTRACTCODE.eq(contractCode))
//            .execute()
//    }
//
//    def updateSellTop5Price(contractCode: String, sellTop5Price: String): Unit = {
//        Jooq.get()
//            .update(Top5Price.TOP5_PRICE)
//            .set(Top5Price.TOP5_PRICE.SELLTOP5, DSL.value(sellTop5Price))
//            .where(Top5Price.TOP5_PRICE.CONTRACTCODE.eq(contractCode))
//            .execute()
//    }
//
//    def updateLatestPrice(contractCode: String, latestPrice: Long): Unit = {
//        Jooq.get()
//            .update(Top5Price.TOP5_PRICE)
//            .set(Top5Price.TOP5_PRICE.LATESTTRADEPRICE, DSL.value(latestPrice))
//            .where(Top5Price.TOP5_PRICE.CONTRACTCODE.eq(contractCode))
//            .execute()
//    }
//
//    def updateLatestTradeId(contractCode: String, tradeId: Long): Unit = {
//        Jooq.get()
//            .update(Top5Price.TOP5_PRICE)
//            .set(Top5Price.TOP5_PRICE.LATESTTRADEID, DSL.value(tradeId))
//            .where(Top5Price.TOP5_PRICE.CONTRACTCODE.eq(contractCode))
//            .execute()
//    }
//
//    def getLatestTradeID(contractCode: String): Long = {
//        val rs = Jooq.get()
//            .select(Top5Price.TOP5_PRICE.LATESTTRADEID)
//            .from(Top5Price.TOP5_PRICE)
//            .where(Top5Price.TOP5_PRICE.CONTRACTCODE.equal(contractCode))
//            .fetchAny()
//
//        if (rs == null || rs.getValue(Top5Price.TOP5_PRICE.LATESTTRADEID) == null) {
//            0
//        } else {
//            rs.getValue(Top5Price.TOP5_PRICE.LATESTTRADEID)
//        }
//    }
//
//    /**
//      * 开发成功，插入开卡流水表
//      *
//      * @param username 用户名
//      * @param createTime 时间
//      * @param cardId 卡号
//      * @param status 状态
//      */
//    def insertCardOpenStatement(username: String, createTime: Long, cardId: String, status: Short) : Unit = {
//        val time = new Timestamp(createTime)
//        try {
//            Jooq.get().insertInto(CardOpenStatement.CARD_OPEN_STATEMENT,
//                CardOpenStatement.CARD_OPEN_STATEMENT.USERNAME,
//                CardOpenStatement.CARD_OPEN_STATEMENT.CREATETIME,
//                CardOpenStatement.CARD_OPEN_STATEMENT.CARDID,
//                CardOpenStatement.CARD_OPEN_STATEMENT.STATEMENTSTATUS).values(username, time, cardId, status)
//                .execute()
//            logger.infoFBusiness("insert into CARD_OPEN_STATEMENT success")("username", "cardId", "status")(username, cardId, status)
//        } catch {
//            case e: Exception =>
//                logger.errorF("insert into CARD_OPEN_STATEMENT error", e)()()
//        }
//    }
//
//    /**
//      * 开卡失败，插入开卡流水异常表
//      *
//      * @param username 用户名
//      * @param createTime 时间
//      * @param cardId 卡号
//      * @param status 状态
//      * @param message 消息
//      */
//    def insertCardOpenErrorStatement(username: String, createTime: Long, cardId: String, status: Short, message: String): Unit = {
//        try {
//            val time = new Timestamp(createTime)
//            Jooq.get().insertInto(CardOpenErrorStatement.CARD_OPEN_ERROR_STATEMENT,
//                CardOpenErrorStatement.CARD_OPEN_ERROR_STATEMENT.USERNAME,
//                CardOpenErrorStatement.CARD_OPEN_ERROR_STATEMENT.CREATETIME,
//                CardOpenErrorStatement.CARD_OPEN_ERROR_STATEMENT.CARDID,
//                CardOpenErrorStatement.CARD_OPEN_ERROR_STATEMENT.STATEMENTSTATUS,
//                CardOpenErrorStatement.CARD_OPEN_ERROR_STATEMENT.MESSAGE).values(username, time, cardId, status, message)
//                .execute()
//            logger.infoFBusiness("insert into CARD_OPEN_ERROR_STATEMENT success")("username", "cardId", "message")(username, cardId, message)
//        } catch {
//            case e: Exception =>
//                logger.errorF("insert into CARD_OPEN_ERROR_STATEMENT error", e)()()
//        }
//    }
}
