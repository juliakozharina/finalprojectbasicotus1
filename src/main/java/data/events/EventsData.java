package data.events;

import lombok.Getter;

@Getter
public enum EventsData {
    CalendarOfEvents("Календарь мероприятий");

    private String name;

    EventsData(String name) {
        this.name = name;
    }

}
