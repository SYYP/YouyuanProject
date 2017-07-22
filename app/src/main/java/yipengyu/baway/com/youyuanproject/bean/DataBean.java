package yipengyu.baway.com.youyuanproject.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DataBean {

    /**
     * area : 安徽省-安庆市-枞阳县
     * picWidth : 510
     * createtime : 1499485141560
     * picHeight : 507
     * gender : 男
     * lng : 0
     * introduce : hhaha
     * imagePath : http://dyt-pict.oss-cn-beijing.aliyuncs.com/dliao/default_man.jpg
     * userId : 23
     * yxpassword : U383lo04
     * password :
     * lasttime : 1499485141560
     * phone : 18256127721
     * nickname : 安徽
     * age : 18
     * lat : 0
     */
    @Id(autoincrement = true)
    private Long id ;
    private String area;
    private int picWidth;
    private long createtime;
    private int picHeight;
    private String gender;
    private double lng;
    private String introduce;
    private String imagePath;
    private int userId;
    private String yxpassword;
    private String password;
    private long lasttime;
    private String phone;
    private String nickname;
    private String age;
    private double lat;


    @Generated(hash = 825355436)
    public DataBean(Long id, String area, int picWidth, long createtime,
            int picHeight, String gender, double lng, String introduce,
            String imagePath, int userId, String yxpassword, String password,
            long lasttime, String phone, String nickname, String age, double lat) {
        this.id = id;
        this.area = area;
        this.picWidth = picWidth;
        this.createtime = createtime;
        this.picHeight = picHeight;
        this.gender = gender;
        this.lng = lng;
        this.introduce = introduce;
        this.imagePath = imagePath;
        this.userId = userId;
        this.yxpassword = yxpassword;
        this.password = password;
        this.lasttime = lasttime;
        this.phone = phone;
        this.nickname = nickname;
        this.age = age;
        this.lat = lat;
    }

    @Generated(hash = 908697775)
    public DataBean() {
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setPicWidth(int picWidth) {
        this.picWidth = picWidth;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public void setPicHeight(int picHeight) {
        this.picHeight = picHeight;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setYxpassword(String yxpassword) {
        this.yxpassword = yxpassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLasttime(long lasttime) {
        this.lasttime = lasttime;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public String getArea() {
        return area;
    }

    public int getPicWidth() {
        return picWidth;
    }

    public long getCreatetime() {
        return createtime;
    }

    public int getPicHeight() {
        return picHeight;
    }

    public String getGender() {
        return gender;
    }

    public double getLng() {
        return lng;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getUserId() {
        return userId;
    }

    public String getYxpassword() {
        return yxpassword;
    }

    public String getPassword() {
        return password;
    }

    public long getLasttime() {
        return lasttime;
    }

    public String getPhone() {
        return phone;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAge() {
        return age;
    }
}