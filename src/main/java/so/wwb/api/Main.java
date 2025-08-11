package so.wwb.api;

import so.wwb.api.event.MyEvent;
import so.wwb.api.event.MyEventPublisher;

public class Main {

    public static void main(String[] args) {
        MyEventPublisher publisher = new MyEventPublisher();

        publisher.addListener(event -> System.out.println("監聽器1 收到事件: " + event.getMessage()));
        publisher.addListener(event -> System.out.println("監聽器2 收到事件: " + event.getMessage()));

        publisher.publishEvent(new MyEvent("Hello Event!"));
        publisher.publishEvent(new MyEvent("Another Event"));
    }
}
