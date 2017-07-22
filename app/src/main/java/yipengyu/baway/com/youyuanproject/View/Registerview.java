package yipengyu.baway.com.youyuanproject.View;

import cn.smssdk.SMSSDK;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public interface Registerview {

    //手机号为空
    void  phontEmpy( int type);
    //倒计时获取短信验证码
     void showTimer();

}
