package yipengyu.baway.com.youyuanproject.Moudle;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public interface ILoginMoudle {
    void getuserinfor(String name,String pwd,String lat, String lng,LoginUserListener listener);


      public interface LoginUserListener{
          void onSuccess(String reuslt);
          void onFailed(int code);
      }
}
