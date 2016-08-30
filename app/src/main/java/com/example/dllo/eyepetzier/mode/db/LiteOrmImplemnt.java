package com.example.dllo.eyepetzier.mode.db;

import com.example.dllo.eyepetzier.ui.application.EyeApp;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.litesuits.orm.db.model.ColumnsValue;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.util.List;


/**
 * Created by dllo on 16/8/27.
 * liteorm的实现类
 */
public class LiteOrmImplemnt implements IDBRequest {

    private LiteOrm liteOrm;
    static final String DB_NAME = "eye.db";

    LiteOrmImplemnt() {

        if (liteOrm == null) {
            // 配置config
            DataBaseConfig config = new DataBaseConfig(EyeApp.getContext(), DB_NAME);
            config.debugged = true; // open the log
            config.dbVersion = 1; // set database version
            config.onUpdateListener = null; // set database update listener

            liteOrm = LiteOrm.newSingleInstance(config);
        }
    }

    /**
     * 增加一条
     * 同一个对象,不会重复添加
     * @param t
     * @param <T>
     */
    @Override
    public <T> void insert(T t) {
        _insert(t);
    }
    private <T> void _insert(T t) {
        liteOrm.insert(t, ConflictAlgorithm.Replace);
    }

    /**
     * 增加多条
     * @param ts
     * @param <T>
     */
    @Override
    public <T> void insert(List<T> ts) {
        _insert(ts);
    }
    private <T> void _insert(List<T> ts) {
        liteOrm.insert(ts, ConflictAlgorithm.Replace);
    }

    /**
     * 删除全部
     * @param clazz : 值为T.class
     */
    @Override
    public <T> void deleteAll(Class<T> clazz) {
        _deleteAll(clazz);
    }
    private <T> void _deleteAll(Class<T> clazz) {
        liteOrm.deleteAll(clazz);
    }

    /**
     * 删除一条
     * @param t
     * @param <T>
     */
    @Override
    public <T> void deleteBy(T t) {
        _deleteBy(t);
    }
    private <T> void _deleteBy(T t) {
        liteOrm.delete(t);
    }

    /**
     * 通过列名和值删除
     * @param clazz : 值为T.class
     * @param column : 列名
     * @param value : 列值
     * @param <T>
     * @param <K>
     */
    @Override
    public <T, K> void deleteByColumn(Class<T> clazz, String column, K value){
        _deleteByColumn(clazz, column, value);
    }
    private <T, K> void _deleteByColumn(Class<T> clazz, String column, K value){
        liteOrm.delete(new WhereBuilder(clazz).where(column + " = ?", new Object[]{value}));
    }

    /**
     * 更新一条
     * @param t
     * @param <T>
     */
    @Override
    public <T> void update(T t) {
        _update(t);
    }
    private <T> void _update(T t){
//        liteOrm.update(t, ConflictAlgorithm.Replace); // 可能重复添加??
        liteOrm.update(t, ConflictAlgorithm.Ignore); // 不重复添加
    }

    /**
     * 更新多条
     * @param ts
     * @param <T>
     */
    @Override
    public <T> void update(List<T> ts) {
        _update(ts);
    }
    private <T> void _update(List<T> ts){
        liteOrm.update(ts, ConflictAlgorithm.None);
    }

    /**
     * 通过列名更新
     * @param t
     * @param column 列名
     * @param value 更新的值
     */
    @Override
    public <T, K> void updateByColumn(T t, String column, K value){
        _updateByColumn(t, column, value);
    }
    private <T, K> void _updateByColumn(T t, String column, K value){
        ColumnsValue cv = new ColumnsValue(new String[]{column}, new Object[]{value});
        liteOrm.update(t, cv, ConflictAlgorithm.None);
    }

    /**
     * 查询全部
     * @param clazz : 值为T.class
     */
    @Override
    public <T> List<T> queryAll(Class<T> clazz) {
        return _queryAll(clazz);
    }
    private <T> List<T> _queryAll(Class<T> clazz) {
        return liteOrm.query(clazz);
    }

    /**
     * 通过column查询
     * @param clazz
     * @param column 列名
     * @param value  值
     * @param <T>
     * @param <K>
     * @return
     */
    @Override
    public <T, K> List<T> queryByColumn(Class<T> clazz, String column, K value) {
        return _queryByColumn(clazz, column, value);
    }
    private <T, K> List<T> _queryByColumn(Class<T> clazz, String column, K value){
        return liteOrm.query(new QueryBuilder<T>(clazz).where(column + " = ?", new Object[]{value}));
    }
}
