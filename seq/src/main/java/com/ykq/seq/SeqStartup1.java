package com.ykq.seq;

import com.ykq.seq.bean.SeqConfig;
import thirdpart.codec.BodyCodec;

public class SeqStartup1 {

    public static void main(String[] args) throws Exception {
        String configName = "seq1.properties";
        new SeqConfig(configName,new BodyCodec()).startup();
    }

}
