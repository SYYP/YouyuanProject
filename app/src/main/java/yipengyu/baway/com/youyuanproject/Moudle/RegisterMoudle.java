package yipengyu.baway.com.youyuanproject.Moudle;

import cn.smssdk.SMSSDK;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class RegisterMoudle implements Iregister {
    @Override
    public void getVerificationCode(String phone) {
        SMSSDK.getVerificationCode("86", phone);
    }
}
