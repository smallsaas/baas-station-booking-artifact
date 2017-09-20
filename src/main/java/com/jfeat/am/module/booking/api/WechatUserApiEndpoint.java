package com.jfeat.am.module.booking.api;

import com.jfinal.aop.Duang;
import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.cache.IAccessTokenCache;
import com.jfinal.wxaapp.api.WxaUserApi;
import com.jfinal.wxaapp.jfinal.WxaController;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2017/9/19.
 */
@Controller
@DependsOn("springContextHolder")
public class WechatUserApiEndpoint extends WxaController{

    protected WxaUserApi wxaUserApi = Duang.duang(WxaUserApi.class);
    /*
    *   Login
    * */
    public void login(){
        String code = getPara("code");
        if(StrKit.isBlank(code)){
            Kv data = Kv.by("errcode",1000).set("errmsg","code is blank");
            renderJson(data);
            return;
        }

        // get SessionKey
        ApiResult apiResult =wxaUserApi.getSessionKey(code);

        //return SessionKey
        if(!apiResult.isSucceed()){
            renderJson(apiResult.getJson());
            return;
        }

        // create connecting by used appId and accessToken
        IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();
        String sessionId = StrKit.getRandomUUID();
        accessTokenCache.set("wechat accessToken:session:" + sessionId,apiResult.getJson());
        renderJson("sessionId",sessionId);
    }

    // release  user info

    public void userInfo(){
        String signature = getPara("signature");
        String originData = getPara("originData");
        String encryptedData = getPara("encryptedData");

        String iv = getPara("iv");

        // 参数空校验 不做演示
        // 利用 appId 与 accessToken 建立关联，支持多账户
        IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();
        String sessionId = getHeader("wxa-sessionid");
        if (StrKit.isBlank(sessionId)) {
            Kv data = Kv.by("errcode", 500).set("errmsg", "wxa_session Header is blank");
            renderJson(data);
            return;
        }
        String sessionJson = accessTokenCache.get("wxa:session:" + sessionId);
        if (StrKit.isBlank(sessionJson)) {
            Kv data = Kv.by("errcode", 500).set("errmsg", "wxa_session sessionJson is blank");
            renderJson(data);
            return;
        }
        ApiResult sessionResult = ApiResult.create(sessionJson);
        // 获取sessionKey
        String sessionKey = sessionResult.get("session_key");
        if (StrKit.isBlank(sessionKey)) {
            Kv data = Kv.by("errcode", 500).set("errmsg", "sessionKey is blank");
            renderJson(data);
            return;
        }
        // 用户信息校验
        boolean check = wxaUserApi.checkUserInfo(sessionKey, originData, signature);
        if (!check) {
            Kv data = Kv.by("errcode", 500).set("errmsg", "UserInfo check fail");
            renderJson(data);
            return;
        }
        // 服务端解密用户信息
        ApiResult apiResult = wxaUserApi.getUserInfo(sessionKey, encryptedData, iv);
        if (!apiResult.isSucceed()) {
            renderJson(apiResult.getJson());
            return;
        }

        String unionId = apiResult.get("unionId");
        renderJson("{}");
        //TODO  render 一个页面 ？
        // 如果开发者拥有多个移动应用、网站应用、和公众帐号（包括小程序），可通过unionid来区分用户的唯一性
        // 同一用户，对同一个微信开放平台下的不同应用，unionid是相同的。
    }
}
