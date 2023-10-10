package com.hung.ch10.reactive;

import java.util.concurrent.Flow;

public class SimpleSubscriber<T> implements Flow.Subscriber<T> {
    private Flow.Subscription subscription;

    public void onSubscribe(Flow.Subscription subscription) {
        // 儲存訂閱，並向發布者請求一個資料
        (this.subscription = subscription).request(1);
    }
    public void onNext(T item) {
        System.out.println("訂閱者收到的資料：" + item);
        // 請求一個資料
        subscription.request(1);
    }
    public void onError(Throwable ex) { ex.printStackTrace(); }
    public void onComplete() {}
}
