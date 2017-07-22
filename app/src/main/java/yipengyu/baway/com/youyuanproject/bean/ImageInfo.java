package yipengyu.baway.com.youyuanproject.bean;


/**
 * 圈子：九宫格图片展示，Image封装
 * Created by xiaoke on 2016/5/9.
 */
public class ImageInfo {

    private String url;
    private int width;
    private int height;

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
