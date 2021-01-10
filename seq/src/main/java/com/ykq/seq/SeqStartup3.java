package com.ykq.seq;

import com.ykq.seq.bean.SeqConfig;
import thirdpart.codec.BodyCodec;

public class SeqStartup3 {

    public static void main(String[] args) throws Exception {
        String configName = "seq3.properties";
        new SeqConfig(configName,new BodyCodec()).startup();
    }

}
