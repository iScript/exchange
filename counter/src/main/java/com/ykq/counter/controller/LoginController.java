package com.ykq.counter.controller;

import com.ykq.counter.bean.res.Account;
import com.ykq.counter.bean.res.CaptchaRes;
import com.ykq.counter.bean.res.CounterRes;
import com.ykq.counter.cache.CacheType;
import com.ykq.counter.cache.RedisStringCache;
import com.ykq.counter.service.AccountService;
import com.ykq.counter.util.Captcha;
import com.ykq.counter.util.DbUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import thirdpart.uuid.GudyUuid;

import static com.ykq.counter.bean.res.CounterRes.*;

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

    @Autowired
    private AccountService accountService;

    @RequestMapping("/userlogin")
    public CounterRes login(@RequestParam long uid,
                            @RequestParam String password,
                            @RequestParam String captcha,
                            @RequestParam String captchaId) throws Exception{

        Account account = accountService.login(uid, password,
                captcha, captchaId);

        if(account == null){
            return new CounterRes(FAIL,
                    "用户名密码/验证码错误，登录失败",null);
        }else {
            return new CounterRes(account);
        }
    }

    @RequestMapping("/loginfail")
    public CounterRes loginFail(){
        return new CounterRes(RELOGIN,"请重新登陆",null);
    }

    //退出登录
    @RequestMapping("/logout")
    public CounterRes logout(@RequestParam String token){
        accountService.logout(token);
        return new CounterRes(SUCCESS,"退出成功",null);
    }

    @RequestMapping("pwdupdate")
    public CounterRes pwdUpdate(@RequestParam int uid,
                                @RequestParam String oldpwd,
                                @RequestParam String newpwd){
        boolean res = accountService.updatePwd(uid, oldpwd, newpwd);
        if(res){
            return new CounterRes(SUCCESS,"密码更新成功",null);
        }else {
            return new CounterRes(FAIL,"密码更新失败",null);
        }

    }

    @RequestMapping("/test")
    public String test(){
        Account account = DbUtil.queryAccount(3, "002");
        System.out.println(account);

        return "";

    }



}
