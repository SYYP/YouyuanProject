package yipengyu.baway.com.youyuanproject.bean;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class FriendBean {


    /**
     * result_message : 添加好友成功
     * addUser : {"area":"北京市 北京市 崇文区","picWidth":720,"createtime":1500030226875,"picHeight":1369,"gender":"男","lng":116.390998333333,"introduce":"赛若天仙","imagePath":"http://qhb.2dyt.com/MyInterface/images/a3557334-b94c-441a-943e-558bbb2286e8.jpg","userId":13,"yxpassword":"JaQ7Blw6","relation":0,"password":"e10adc3949ba59abbe56e057f20f883e","lasttime":1500030226875,"phone":"18863894005","nickname":"美的不忍直视","age":"20","lat":39.9054983333333}
     * result_code : 200
     */

    private String result_message;
    private AddUserBean addUser;
    private int result_code;

    public String getResult_message() {
        return result_message;
    }

    public void setResult_message(String result_message) {
        this.result_message = result_message;
    }

    public AddUserBean getAddUser() {
        return addUser;
    }

    public void setAddUser(AddUserBean addUser) {
        this.addUser = addUser;
    }

    public int getResult_code() {
        return result_code;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public static class AddUserBean {
        /**
         * area : 北京市 北京市 崇文区
         * picWidth : 720
         * createtime : 1500030226875
         * picHeight : 1369
         * gender : 男
         * lng : 116.390998333333
         * introduce : 赛若天仙
         * imagePath : http://qhb.2dyt.com/MyInterface/images/a3557334-b94c-441a-943e-558bbb2286e8.jpg
         * userId : 13
         * yxpassword : JaQ7Blw6
         * relation : 0
         * password : e10adc3949ba59abbe56e057f20f883e
         * lasttime : 1500030226875
         * phone : 18863894005
         * nickname : 美的不忍直视
         * age : 20
         * lat : 39.9054983333333
         */

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
        private int relation;
        private String password;
        private long lasttime;
        private String phone;
        private String nickname;
        private String age;
        private double lat;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getPicWidth() {
            return picWidth;
        }

        public void setPicWidth(int picWidth) {
            this.picWidth = picWidth;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public int getPicHeight() {
            return picHeight;
        }

        public void setPicHeight(int picHeight) {
            this.picHeight = picHeight;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getYxpassword() {
            return yxpassword;
        }

        public void setYxpassword(String yxpassword) {
            this.yxpassword = yxpassword;
        }

        public int getRelation() {
            return relation;
        }

        public void setRelation(int relation) {
            this.relation = relation;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public long getLasttime() {
            return lasttime;
        }

        public void setLasttime(long lasttime) {
            this.lasttime = lasttime;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }
}
