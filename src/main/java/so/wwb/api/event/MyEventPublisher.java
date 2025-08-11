package so.wwb.api.event;

import java.util.ArrayList;
import java.util.List;

public class MyEventPublisher {

    private final List<MyEventListener> listeners = new ArrayList<>();

    // 註冊監聽器
    public void addListener(MyEventListener listener) {
        listeners.add(listener);
    }

    // 發送事件
    public void publishEvent(MyEvent event) {
        for (MyEventListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}
