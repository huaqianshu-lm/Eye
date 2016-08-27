package com.example.dllo.eyepetzier.mode.db;

import java.util.List;

/**
 * Created by dllo on 16/8/27.
 * 数据库的单例类
 */
public class DatabaseSingleton implements IDBRequest {

    /**
     * 静态内部类的单例
     */
    private static final class SingletonHolder{
        private static final DatabaseSingleton INSTANCE = new DatabaseSingleton();
    }
    public static DatabaseSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // constructor
    private IDBRequest idbRequest;
    private DatabaseSingleton() {
        idbRequest = new LiteOrmImplemnt();
    }

    /*********增 删 改 查***********/
    /**
     * insert
     */
    @Override
    public <T> void insert(T t) {
        idbRequest.insert(t);
    }

    @Override
    public <T> void insert(List<T> ts) {
        idbRequest.insert(ts);
    }

    /**
     * delete
     */
    @Override
    public <T> void deleteAll(Class<T> clazz) {
        idbRequest.deleteAll(clazz);
    }

    @Override
    public <T> void deleteBy(T t) {
        idbRequest.deleteBy(t);
    }

    @Override
    public <T, K> void deleteByColumn(Class<T> clazz, String column, K value) {
        idbRequest.deleteByColumn(clazz, column, value);
    }

    /**
     * update
     */
    @Override
    public <T> void update(T t) {
        idbRequest.update(t);
    }

    @Override
    public <T> void update(List<T> ts) {
        idbRequest.update(ts);
    }

    @Override
    public <T, K> void updateByColumn(T t, String column, K value) {
        idbRequest.updateByColumn(t, column, value);
    }

    /**
     * query
     */
    @Override
    public <T>  List<T> queryAll(Class<T> clazz) {
        return idbRequest.queryAll(clazz);
    }

    @Override
    public <T, K> List<T> queryByColumn(Class<T> clazz, String column, K value) {
        return idbRequest.queryByColumn(clazz, column, value);
    }


}
