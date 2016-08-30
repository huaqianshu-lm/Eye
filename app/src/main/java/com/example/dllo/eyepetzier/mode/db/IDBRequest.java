package com.example.dllo.eyepetzier.mode.db;

import java.util.List;

/**
 * Created by dllo on 16/8/27.
 * 处理DB各种事务的接口,比如:增删改查
 */
public interface IDBRequest {
    /**
     * 增
     */
    <T> void insert(T t);               // 增加一条 bean
    <T> void insert(List<T> ts);        // 增加多条 beans
    /**
     * 删
     */
    <T> void deleteAll(Class<T> clazz); // 全部删除
    <T> void deleteBy(T t);             // 条件删除
    <T, K> void deleteByColumn(Class<T> clazz, String column, K value); // 通过column删除
    /**
     * 改
     */
    <T> void update(T t);               // 更新一条
    <T> void update(List<T> ts);        // 更新多条
    <T, K> void updateByColumn(T t, String column, K value); // 通过column更新
    /**
     * 查
     */
    <T> List<T> queryAll(Class<T> clazz);  // 全部查询
    <T, K> List<T> queryByColumn(Class<T> clazz, String column, K value); //通过column查询

}
