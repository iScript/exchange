package com.ykq.seq;

import com.ykq.seq.bean.SeqConfig;
import thirdpart.codec.BodyCodec;

public class SeqStartup2 {

    public static void main(String[] args) throws Exception {
        String configName = "seq2.properties";
        new SeqConfig(configName,new BodyCodec()).startup();
    }

}
