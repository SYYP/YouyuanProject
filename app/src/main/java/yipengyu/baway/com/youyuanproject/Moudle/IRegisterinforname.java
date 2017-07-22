package yipengyu.baway.com.youyuanproject.Moudle;

import yipengyu.baway.com.youyuanproject.bean.RegisterBean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public interface IRegisterinforname {

    void  getData(String phone,String nickname,String sex,String age,String adres,String introduce,String password,String lat,String lng,RegisterInforFragmentDataListener listener);

    public interface RegisterInforFragmentDataListener {


        public void onSuccess(RegisterBean registerBean);

        public void onFailed(int code);

    }

}
