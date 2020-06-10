package com.lkx.demo.rxjava;


import com.alibaba.fastjson.JSONObject;
import com.lkx.demo.rxjava.observable.JsonObservable;
import com.lkx.demo.rxjava.subscribe.JsonObjectObserver;
import com.lkx.demo.rxjava.subscribe.PrintObserver;
import com.lkx.demo.rxjava.subscribe.SystemOutSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RxJavaDemoTest {
    SystemOutSubscribe systemOutSubscribe = new SystemOutSubscribe();
    PrintObserver printObserver = new PrintObserver();

    @Test
    public void onNext() {
        ObservableOnSubscribe observableOnSubscribe = new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter observableEmitter) throws Exception {
                System.out.println("---------------");
                observableEmitter.onNext(1);
//                observableEmitter.onNext(2);
//                observableEmitter.onNext(3);
            }
        };
        Observable observable = Observable.create(observableOnSubscribe);
        observable.subscribe(System.out::println);
    }


    /**
     * 每3秒一次执行
     *
     * @throws IOException
     */
    @Test
    public void interval() throws IOException {
        Observable.interval(3, TimeUnit.SECONDS).subscribe(printObserver);
        Observable.interval(3, TimeUnit.SECONDS).subscribe(systemOutSubscribe);
        System.in.read();
    }

    @Test
    public void customerObservable() {
        JsonObjectObserver jsonObjectObserver = new JsonObjectObserver();
        JsonObservable<JSONObject> jsonObjectJsonObservable = new JsonObservable<JSONObject>();
        Observable<com.alibaba.fastjson.JSONObject> jsonObjectObservable = Observable.create(jsonObjectJsonObservable);
        jsonObjectObservable.subscribe(jsonObjectObserver);
    }




}