package com.example.healthapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.example.healthapp.bean.UserBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DBManage {


    private final DatabaseHelper databaseHelper;
    private static DBManage dbManage;

    private DBManage(Context context) {
        databaseHelper = new DatabaseHelper(context);

    }

    public static DBManage getInstance(Context context) {
        if (dbManage == null) {
            synchronized (DBManage.class) {
                if (dbManage == null) {
                    dbManage = new DBManage(context);
                }
            }
        }
        return dbManage;
    }


    //添加用户
    public long addUser(UserBean user){
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",user.getName());
        cv.put("psw",user.getPsw());
        cv.put("sex",user.getSex());
        cv.put("des",user.getDes());
        cv.put("nickname",user.getNickname());
        return database.insert("user", null, cv);
    }

    public ArrayList<UserBean> selectUser(String userName){
        ArrayList<UserBean> booksBeans = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cursor=database.rawQuery("SELECT * FROM user WHERE name = ?",new String[]{userName});

        while (cursor.moveToNext()){

            String  id =cursor.getString(0);
            String  name =cursor.getString(1);
            String  psw =cursor.getString(2);
            String  sex =cursor.getString(3);
            String  des =cursor.getString(4);
            String  nick =cursor.getString(5);
            booksBeans.add(new UserBean(id,name,psw,sex,des,nick));
        }

        return booksBeans;
    }


    public long updateUser(UserBean user){
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        if (user.getName()!=null){
            cv.put("name",user.getName());
        }
        if (user.getPsw()!=null){
            cv.put("psw",user.getPsw());
        }
        if (user.getSex()!=null){
            cv.put("sex",user.getSex());
        }
        if (user.getDes()!=null){
            cv.put("des",user.getDes());
        }
        if (user.getNickname()!=null){
            cv.put("nickname",user.getNickname());
        }

        //修改条件
        String whereClause = "id=?";

        return database.update("user",cv,whereClause,new String[]{user.getId()});
    }
    //通过id查询用户
    public UserBean queryUserById(String id){
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM user WHERE id =?", new String[]{id});
        UserBean userBean = null;
        if (cursor.moveToNext()){
            //更具UserBean中的字段获取数据
            String id1 = cursor.getString(0);
            String name = cursor.getString(1);
            String psw = cursor.getString(2);
            String sex = cursor.getString(3);
            String des = cursor.getString(4);
            String nickname = cursor.getString(5);
            userBean = new UserBean(id1,name,psw,sex,des,nickname);

        }
        return userBean;
    }

    public long delUser(int id){
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        return database.delete("user","id=?", new String[]{String.valueOf(id)});
    }






    public <T> long insert(T entity) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Map<String, String> map = getValues(entity);
        //把数据转移到ContentValues中
        ContentValues values = getContentValues(map);

        return database.insert(entity.getClass().getSimpleName().toLowerCase(), null, values);
    }

    public <T> long update(T entity, T where) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
//        sqLiteDatabase.update(tableName,contentValues,"name=",new String[]{"jett"});
        int result = -1;
        Map values = getValues(entity);
        ContentValues contentValues = getContentValues(values);
        Map whereCause = getValues(where);//key==_id   value=1
        Condition condition = new Condition(whereCause);
        result = database.update(entity.getClass().getSimpleName().toLowerCase(), contentValues, condition.whereCasue, condition.whereArgs);
        return result;
    }

    public <T> int delete(T where) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
//        sqLiteDatabase.delete(tableName,"name=?",new String[]{});
        Map map = getValues(where);
        Condition condition = new Condition(map);
        int result = database.delete(where.getClass().getSimpleName().toLowerCase(), condition.whereCasue, condition.whereArgs);
        return result;
    }


    public <T> List<T> query(T where) {

        return query(where, null, null, null);
    }

    public <T> List<T> query(T where, String orderBy, Integer startIndex, Integer limit) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
//        sqLiteDatabase.query(tableName,null,"id=?",new String[],null,null,orderBy,"1,5");
        Map map = getValues(where);
        String limitString = null;
        if (startIndex != null && limit != null) {
            limitString = startIndex + " , " + limit;
        }
        Condition condition = new Condition(map);

        Cursor cursor = database.query(where.getClass().getSimpleName(), null, condition.whereCasue, condition.whereArgs, null, null, orderBy, limitString);
        //定义一个用来解析游标的方法
        List<T> result = getResult(cursor, where);
        return result;
    }

    private <T> List<T> getResult(Cursor cursor, T obj) {
        ArrayList list = new ArrayList();
        Object item = null;
        while (cursor.moveToNext()) {
            try {
                item = obj.getClass().newInstance();//new User();

                Field[] declaredFields = obj.getClass().getDeclaredFields();
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    String columnName = field.getName();
                    Integer columnIndex = cursor.getColumnIndex(columnName);
                    if (columnIndex != -1) {
                        if (type == String.class) {
                            field.set(item, cursor.getString(columnIndex));
                        } else if (type == Double.class) {
                            field.set(item, cursor.getDouble(columnIndex));
                        } else if (type == Integer.class) {
                            field.set(item, cursor.getInt(columnIndex));
                        } else if (type == Long.class) {
                            field.set(item, cursor.getLong(columnIndex));
                        } else if (type == byte[].class) {
                            field.set(item, cursor.getBlob(columnIndex));
                        } else {
                            continue;
                        }
                    }
                }
                list.add(item);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return list;
    }


    private class Condition {
        private String whereCasue;//"name=? and password=?"
        private String[] whereArgs;//new String[]{"jett"}

        public Condition(Map<String, String> whereCasue) {
            ArrayList list = new ArrayList();//whereArgs里面的内容存入list
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("1=1");
            //取所有的字段名
            Set keys = whereCasue.keySet();
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = whereCasue.get(key);
                if (value != null) {
                    stringBuilder.append(" and " + key + "=?");
                    list.add(value);
                }
            }
            this.whereCasue = stringBuilder.toString();
            this.whereArgs = (String[]) list.toArray(new String[list.size()]);

        }
    }


    private ContentValues getContentValues(Map<String, String> map) {
        ContentValues contentValues = new ContentValues();
        Set keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
            if (value != null) {
                contentValues.put(key, value);
            }
        }
        return contentValues;
    }

    private <T> Map<String, String> getValues(T entity) {
        HashMap<String, String> map = new HashMap<>();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object o = field.get(entity);
                if (o == null) {
                    continue;
                }
                String value = o.toString();
                String key = field.getName();
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    map.put(key, value);
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            System.out.println(field.getName());
        }
        return map;
    }


}
