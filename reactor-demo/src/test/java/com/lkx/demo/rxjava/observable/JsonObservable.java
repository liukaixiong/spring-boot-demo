package com.lkx.demo.rxjava.observable;

import com.alibaba.fastjson.JSONObject;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

/**
 * Json类型的数据流
 *
 * @author ： liukx
 * @time ： 2019/5/30 - 15:48
 */
public class JsonObservable<J> extends Observable<JSONObject> implements ObservableOnSubscribe<JSONObject> {

    private Integer index = 0;

    @Override
    protected void subscribeActual(Observer<? super JSONObject> observer) {
        JSONObject jsonObject = getJsonObject();
        System.out.println("subscribeActual : " + index);
        observer.onNext(jsonObject);
        observer.onNext(jsonObject);
        observer.onNext(jsonObject);

    }

    private JSONObject getJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("index", index++);
        return jsonObject;
    }

    @Override
    public void subscribe(ObservableEmitter<JSONObject> observableEmitter) throws Exception {
        JSONObject jsonObject = getJsonObject();
        observableEmitter.onNext(jsonObject);
        System.out.println("订阅 : "+jsonObject.toJSONString());
    }
}