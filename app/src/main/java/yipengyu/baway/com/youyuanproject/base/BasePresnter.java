package yipengyu.baway.com.youyuanproject.base;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public  abstract class BasePresnter<T> {

    public T view;
    public void attach(T view){
      this.view=  view;

    }
     public void detach(){
         this.view=null;
     }
}
