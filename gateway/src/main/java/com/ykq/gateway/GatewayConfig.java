package com.ykq.gateway;

import io.vertx.core.Vertx;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import thirdpart.checksum.ICheckSum;
import thirdpart.codec.IBodyCodec;

@Log4j2
@Getter
public class GatewayConfig {
    //网关ID
    private short id;

    //端口
    private int recvPort;


    //TODO 柜台列表 数据库连接

    @Setter
    private IBodyCodec bodyCodec;

    @Setter
    private ICheckSum cs;

    private Vertx vertx = Vertx.vertx();
}
