package com.lkx.demo.rxjava.subscribe;

import com.alibaba.fastjson.JSONObject;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Json类型的订阅器
 *
 * @author ： liukx
 * @time ： 2019/5/30 - 16:05
 */
public class JsonObjectObserver implements Observer<JSONObject> {

    private Logger logger = LoggerFactory.getLogger(JsonObjectObserver.class);

    @Override
    public void onSubscribe(Disposable disposable) {
        logger.info(" onSubscribe =========>> " + disposable.toString());
    }

    @Override
    public void onNext(JSONObject jsonObject) {
        logger.info(" onNext =========>> " + jsonObject.toJSONString());
    }

    @Override
    public void onError(Throwable throwable) {
        logger.info(" onError =========>> " + throwable.toString());
    }

    @Override
    public void onComplete() {
        logger.info(" onComplete =========>> ");
    }
}
