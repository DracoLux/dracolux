package timeline;

import java.util.Queue;

public class Timeline {
    Queue<Event> events;

    public Timeline(Queue<Event> events) {
        this.events = events;
    }

    public Event poll(){
        return events.poll();
    }

    public boolean isEmpty(){
        return events.isEmpty();
    }

}
