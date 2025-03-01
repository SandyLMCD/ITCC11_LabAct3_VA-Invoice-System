import java.util.ArrayList;
import java.util.List;

public class EventBus {
    private List<EventListener> listeners = new ArrayList<>();

    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void publish(Event_Service event) {
        for (EventListener listener : listeners) {
            listener.onEvent(event);
        }
    }

    interface EventListener {
        void onEvent(Event event);
    }

    public void publish(Event_Client event_Client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'publish'");
    }
}
