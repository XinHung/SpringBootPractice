package com.hung.ch10.reactive;

import java.util.concurrent.SubmissionPublisher;

public class ReactiveTest {
	/*
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<String>();

        SimpleProcessor<String> processor = new SimpleProcessor<>();
        SimpleSubscriber<String> subscriber = new SimpleSubscriber<>();

        // 發行者與處理器建立訂閱關係
        publisher.subscribe(processor);
        // 處理器與訂閱者建立訂閱關係
        processor.subscribe(subscriber);

        // submit() 透過非同步呼叫 onNex()，將資料發送給每一個訂閱者
        publisher.submit("Hello");
        publisher.submit("World");
        publisher.close();

        // 由於 SubmissionPublisher 內部採用非同步發布資料，為了避免主程式退出導致結束，
        // 因此 sleep 1 秒，讓訂閱者可以有時間處理資料
        Thread.sleep(1000);
    }
    */
}
