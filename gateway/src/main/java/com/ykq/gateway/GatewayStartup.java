package com.ykq.gateway;


import lombok.extern.log4j.Log4j2;

@Log4j2
public class GatewayStartup {
    public static void main(String[] args) throws Exception {
        String configFileName = "gateway.xml";

        GatewayConfig config = new GatewayConfig();
        System.out.println(config);
//        config.initConfig(GatewayStartup.class.getResource("/").getPath()
//                + configFileName);
//        config.setCs(new ByteCheckSum());
//        config.setBodyCodec(new BodyCodec());
//        config.startup();
    }
}
