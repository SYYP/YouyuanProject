package yipengyu.baway.com.youyuanproject.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import yipengyu.baway.com.youyuanproject.bean.FriendListDataBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FRIEND_LIST_DATA_BEAN".
*/
public class FriendListDataBeanDao extends AbstractDao<FriendListDataBean, Long> {

    public static final String TABLENAME = "FRIEND_LIST_DATA_BEAN";

    /**
     * Properties of entity FriendListDataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Area = new Property(1, String.class, "area", false, "AREA");
        public final static Property PicWidth = new Property(2, int.class, "picWidth", false, "PIC_WIDTH");
        public final static Property Createtime = new Property(3, long.class, "createtime", false, "CREATETIME");
        public final static Property PicHeight = new Property(4, int.class, "picHeight", false, "PIC_HEIGHT");
        public final static Property Gender = new Property(5, String.class, "gender", false, "GENDER");
        public final static Property Lng = new Property(6, double.class, "lng", false, "LNG");
        public final static Property Introduce = new Property(7, String.class, "introduce", false, "INTRODUCE");
        public final static Property ImagePath = new Property(8, String.class, "imagePath", false, "IMAGE_PATH");
        public final static Property UserId = new Property(9, int.class, "userId", false, "USER_ID");
        public final static Property Yxpassword = new Property(10, String.class, "yxpassword", false, "YXPASSWORD");
        public final static Property Relation = new Property(11, int.class, "relation", false, "RELATION");
        public final static Property Password = new Property(12, String.class, "password", false, "PASSWORD");
        public final static Property Lasttime = new Property(13, long.class, "lasttime", false, "LASTTIME");
        public final static Property Phone = new Property(14, String.class, "phone", false, "PHONE");
        public final static Property Nickname = new Property(15, String.class, "nickname", false, "NICKNAME");
        public final static Property Age = new Property(16, String.class, "age", false, "AGE");
        public final static Property Lat = new Property(17, double.class, "lat", false, "LAT");
        public final static Property Relationtime = new Property(18, long.class, "relationtime", false, "RELATIONTIME");
    }


    public FriendListDataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public FriendListDataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FRIEND_LIST_DATA_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"AREA\" TEXT," + // 1: area
                "\"PIC_WIDTH\" INTEGER NOT NULL ," + // 2: picWidth
                "\"CREATETIME\" INTEGER NOT NULL ," + // 3: createtime
                "\"PIC_HEIGHT\" INTEGER NOT NULL ," + // 4: picHeight
                "\"GENDER\" TEXT," + // 5: gender
                "\"LNG\" REAL NOT NULL ," + // 6: lng
                "\"INTRODUCE\" TEXT," + // 7: introduce
                "\"IMAGE_PATH\" TEXT," + // 8: imagePath
                "\"USER_ID\" INTEGER NOT NULL ," + // 9: userId
                "\"YXPASSWORD\" TEXT," + // 10: yxpassword
                "\"RELATION\" INTEGER NOT NULL ," + // 11: relation
                "\"PASSWORD\" TEXT," + // 12: password
                "\"LASTTIME\" INTEGER NOT NULL ," + // 13: lasttime
                "\"PHONE\" TEXT," + // 14: phone
                "\"NICKNAME\" TEXT," + // 15: nickname
                "\"AGE\" TEXT," + // 16: age
                "\"LAT\" REAL NOT NULL ," + // 17: lat
                "\"RELATIONTIME\" INTEGER NOT NULL );"); // 18: relationtime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FRIEND_LIST_DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FriendListDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String area = entity.getArea();
        if (area != null) {
            stmt.bindString(2, area);
        }
        stmt.bindLong(3, entity.getPicWidth());
        stmt.bindLong(4, entity.getCreatetime());
        stmt.bindLong(5, entity.getPicHeight());
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(6, gender);
        }
        stmt.bindDouble(7, entity.getLng());
 
        String introduce = entity.getIntroduce();
        if (introduce != null) {
            stmt.bindString(8, introduce);
        }
 
        String imagePath = entity.getImagePath();
        if (imagePath != null) {
            stmt.bindString(9, imagePath);
        }
        stmt.bindLong(10, entity.getUserId());
 
        String yxpassword = entity.getYxpassword();
        if (yxpassword != null) {
            stmt.bindString(11, yxpassword);
        }
        stmt.bindLong(12, entity.getRelation());
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(13, password);
        }
        stmt.bindLong(14, entity.getLasttime());
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(15, phone);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(16, nickname);
        }
 
        String age = entity.getAge();
        if (age != null) {
            stmt.bindString(17, age);
        }
        stmt.bindDouble(18, entity.getLat());
        stmt.bindLong(19, entity.getRelationtime());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FriendListDataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String area = entity.getArea();
        if (area != null) {
            stmt.bindString(2, area);
        }
        stmt.bindLong(3, entity.getPicWidth());
        stmt.bindLong(4, entity.getCreatetime());
        stmt.bindLong(5, entity.getPicHeight());
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(6, gender);
        }
        stmt.bindDouble(7, entity.getLng());
 
        String introduce = entity.getIntroduce();
        if (introduce != null) {
            stmt.bindString(8, introduce);
        }
 
        String imagePath = entity.getImagePath();
        if (imagePath != null) {
            stmt.bindString(9, imagePath);
        }
        stmt.bindLong(10, entity.getUserId());
 
        String yxpassword = entity.getYxpassword();
        if (yxpassword != null) {
            stmt.bindString(11, yxpassword);
        }
        stmt.bindLong(12, entity.getRelation());
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(13, password);
        }
        stmt.bindLong(14, entity.getLasttime());
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(15, phone);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(16, nickname);
        }
 
        String age = entity.getAge();
        if (age != null) {
            stmt.bindString(17, age);
        }
        stmt.bindDouble(18, entity.getLat());
        stmt.bindLong(19, entity.getRelationtime());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public FriendListDataBean readEntity(Cursor cursor, int offset) {
        FriendListDataBean entity = new FriendListDataBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // area
            cursor.getInt(offset + 2), // picWidth
            cursor.getLong(offset + 3), // createtime
            cursor.getInt(offset + 4), // picHeight
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // gender
            cursor.getDouble(offset + 6), // lng
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // introduce
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // imagePath
            cursor.getInt(offset + 9), // userId
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // yxpassword
            cursor.getInt(offset + 11), // relation
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // password
            cursor.getLong(offset + 13), // lasttime
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // phone
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // nickname
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // age
            cursor.getDouble(offset + 17), // lat
            cursor.getLong(offset + 18) // relationtime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FriendListDataBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setArea(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPicWidth(cursor.getInt(offset + 2));
        entity.setCreatetime(cursor.getLong(offset + 3));
        entity.setPicHeight(cursor.getInt(offset + 4));
        entity.setGender(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setLng(cursor.getDouble(offset + 6));
        entity.setIntroduce(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setImagePath(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setUserId(cursor.getInt(offset + 9));
        entity.setYxpassword(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setRelation(cursor.getInt(offset + 11));
        entity.setPassword(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setLasttime(cursor.getLong(offset + 13));
        entity.setPhone(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setNickname(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setAge(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setLat(cursor.getDouble(offset + 17));
        entity.setRelationtime(cursor.getLong(offset + 18));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FriendListDataBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FriendListDataBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FriendListDataBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}