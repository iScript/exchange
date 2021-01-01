package com.ykq.counter.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.ykq.counter.bean.res.Account;
import com.ykq.counter.bean.res.OrderInfo;
import com.ykq.counter.bean.res.PosiInfo;
import com.ykq.counter.bean.res.TradeInfo;
import com.ykq.counter.cache.CacheType;
import com.ykq.counter.cache.RedisStringCache;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;




import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
//第二步
@Component
public class DbUtil {
    private static DbUtil dbUtil = null;

    private DbUtil() {
    }

    //第三部
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    //第四部
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    //第二步
    @PostConstruct
    private void init() {

        dbUtil = new DbUtil();
        //第四部
        dbUtil.setSqlSessionTemplate(this.sqlSessionTemplate);
    }

    //////////////////////////////身份认证/////////////////////////////////////
    public static Account queryAccount(long uid, String password) {
        System.out.println(dbUtil);
        return dbUtil.getSqlSessionTemplate().selectOne(
                "userMapper.queryAccount",
                ImmutableMap.of("uid", uid, "password", password)
        );
    }

    public static void updateLoginTime(long uid, String nowDate,
                                       String nowTime) {
        System.out.println(uid+"-"+nowDate+"-"+nowTime);
        dbUtil.getSqlSessionTemplate().update(
                "userMapper.updateAccountLoginTime",
                ImmutableMap.of(
                        "uid", uid,
                        "modifyDate", nowDate,
                        "modifyTime", nowTime
                )
        );
    }

    public static int updatePwd(long uid, String oldPwd,
                                String newPwd) {
        return dbUtil.getSqlSessionTemplate().update(
                "userMapper.updatePwd",
                ImmutableMap.of("uid", uid,
                        "newPwd", newPwd,
                        "oldPwd", oldPwd));
    }

    //////////////////////////////资金类////////////////////////////////////////
    public static long getBalance(long uid) {
        Long res = dbUtil.getSqlSessionTemplate()
                .selectOne("orderMapper.queryBalance",
                        ImmutableMap.of("uid", uid));
        if (res == null) {
            return -1;
        } else {
            return res;
        }
    }

    public static void addBalance(long uid, long balance) {
        dbUtil.getSqlSessionTemplate().update("orderMapper.updateBalance",
                ImmutableMap.of("uid", uid, "balance", balance));
    }

    public static void minusBalance(long uid, long balance) {
        addBalance(uid, -balance);
    }

    //////////////////////////////持仓类////////////////////////////////////////
    public static List<PosiInfo> getPosiList(long uid) {
        //查缓存
        String suid = Long.toString(uid);
        String posiS = RedisStringCache.get(suid, CacheType.POSI);
        //if (StringUtils.isEmpty(posiS)) {
        if (true) {
            System.out.println(uid);
            //未查到 查库
            List<PosiInfo> tmp = dbUtil.getSqlSessionTemplate().selectList(
                    "orderMapper.queryPosi",
                    ImmutableMap.of("uid", uid)
            );
            List<PosiInfo> result =
                    CollectionUtils.isEmpty(tmp) ? Lists.newArrayList()
                            : tmp;
            //更新缓存
            RedisStringCache.cache(suid, JsonUtil.toJson(result), CacheType.POSI);
            return result;
        } else {
            //查到 命中缓存
            return JsonUtil.fromJsonArr(posiS, PosiInfo.class);
        }
    }

    public static PosiInfo getPosi(long uid, int code) {
        return dbUtil.getSqlSessionTemplate().selectOne("orderMapper.queryPosi",
                ImmutableMap.of("uid", uid, "code", code));
    }

    //////////////////////////////委托类////////////////////////////////////////
    public static List<OrderInfo> getOrderList(long uid) {
        //查缓存
        String suid = Long.toString(uid);
        String orderS = RedisStringCache.get(suid, CacheType.ORDER);
        if (StringUtils.isEmpty(orderS)) {
            //未查到 查库
            List<OrderInfo> tmp = dbUtil.getSqlSessionTemplate().selectList(
                    "orderMapper.queryOrder",
                    ImmutableMap.of("uid", uid)
            );
            List<OrderInfo> result =
                    CollectionUtils.isEmpty(tmp) ? Lists.newArrayList()
                            : tmp;
            //更新缓存
            RedisStringCache.cache(suid, JsonUtil.toJson(result), CacheType.ORDER);
            return result;
        } else {
            //查到 命中缓存
            return JsonUtil.fromJsonArr(orderS, OrderInfo.class);
        }
    }

    //////////////////////////////成交类////////////////////////////////////////
    public static List<TradeInfo> getTradeList(long uid) {
        //查缓存
        String suid = Long.toString(uid);
        String tradeS = RedisStringCache.get(suid, CacheType.TRADE);
        if (StringUtils.isEmpty(tradeS)) {
            //未查到 查库
            List<TradeInfo> tmp = dbUtil.getSqlSessionTemplate().selectList(
                    "orderMapper.queryTrade",
                    ImmutableMap.of("uid", uid)
            );
            List<TradeInfo> result =
                    CollectionUtils.isEmpty(tmp) ? Lists.newArrayList()
                            : tmp;
            //更新缓存
            RedisStringCache.cache(suid, JsonUtil.toJson(result), CacheType.TRADE);
            return result;
        } else {
            //查到 命中缓存
            return JsonUtil.fromJsonArr(tradeS, TradeInfo.class);
        }
    }

}
