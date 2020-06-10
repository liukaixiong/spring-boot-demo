package com.lkx.demo.rxjava;


import io.reactivex.Observable;

/**
 * RxJava
 *
 * @author ： liukx
 * @time ： 2019/5/30 - 15:03
 */
public class RxJavaDemo {


    public void t1() {
        Observable<Integer> observable = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
        });
        observable.subscribe(System.out::println);
    }

}
