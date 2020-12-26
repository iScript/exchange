package com.ykq.counter.controller;

import com.ykq.counter.bean.res.CaptchaRes;
import com.ykq.counter.bean.res.CounterRes;
import com.ykq.counter.cache.CacheType;
import com.ykq.counter.cache.RedisStringCache;
import com.ykq.counter.util.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thirdpart.uuid.GudyUuid;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @RequestMapping("/captcha")
    public CounterRes captcha() throws Exception{
        //1.生成验证码 120 40 4个字符 噪点+线条
        Captcha captcha = new Captcha(120,40,
                4,10);

        //2.将验证码<ID,验证码数值>放入缓存
        String uuid = String.valueOf(GudyUuid.getInstance().getUUID());
        RedisStringCache.cache(uuid,captcha.getCode(),
                CacheType.CAPTCHA);

        //3.使用base64编码图片，并返回给前台
        //uuid,base64
        CaptchaRes res = new CaptchaRes(uuid,captcha.getBase64ByteStr());
        return new CounterRes(res);
    }

}
