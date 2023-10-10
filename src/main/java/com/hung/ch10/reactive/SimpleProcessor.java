package com.hung.ch10.reactive;

import java.util.concurrent.Flow;

public class SimpleProcessor<T> implements Flow.Processor<T, T>{
    private Flow.Subscriber<? super T> subscriber;
    @Override
    public void subscribe(Flow.Subscriber<? super T> subscriber) {
        // 儲存訂閱者
        this.subscriber = subscriber;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        // 向訂閱者傳遞 Subscription 物件，訂閱者可透過 Subscription 物件向發行者請求資料
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(T item) {
        System.out.println("處理器收到的資料：" + item);
        // 在處理器中可以對資料進行轉換然後發布出去
        subscriber.onNext(item);
    }

    @Override
    public void onError(Throwable ex) {
        ex.printStackTrace();
    }

    @Override
    public void onComplete() {
    }
}
