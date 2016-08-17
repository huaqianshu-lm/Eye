package com.example.dllo.eyepetzier.mode.net;

/**
 * Created by dllo on 16/8/12.
 * 所有网络请求封装好的单例类 - 二次封装
 * ( 可以包括 : volley, OkHttp等网络请求框架 )
 */
public class NetRequestSingleton implements INetwork{

    /**
     * 静态内部类的单例类写法
     */
    private static final class SingletonHolder{
        private static final NetRequestSingleton INSTANCE = new NetRequestSingleton();
    }
    public static NetRequestSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * constructor
     */
    private INetwork iNetwork; //重新声明接口类型的对象
    private NetRequestSingleton() {
        // 父类声明,具体执行时子类实现方法,这是多态
        // iNetwork代表父类对象, OkHttpImplemnt和VolleyImplemnt代表子类对象
        iNetwork = new OkHttpImplemnt(); // 如果想要实现OkHttp网络请求,父类就初始化这个子类
//        iNetwork = new VolleyImplemnt(); // 如果想要实现Volley网络请求,父类就初始化这个子类
    }

    /**
     * method 1  - 只请求,不解析
     * 返回String response
     * @param url
     * @param onHttpCallback
     */
    @Override
    public void startRequest(String url, IOnHttpCallback<String> onHttpCallback) {
        iNetwork.startRequest(url, onHttpCallback);
    }

    /**
     * method 2 - 既请求,也解析
     * 返回Entity实体类 response 到主线程
     * @param url
     * @param clazz 实体类的type
     * @param onHttpCallback
     * @param <T>
     */
    @Override
    public <T> void startRequest(String url, Class<T> clazz, IOnHttpCallback<T> onHttpCallback) {
        iNetwork.startRequest(url, clazz, onHttpCallback);
    }





}
