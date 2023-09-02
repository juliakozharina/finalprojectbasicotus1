package data.events;

import lombok.Getter;

@Getter
public enum EventsTypeData {
    OpenWebinars("Открытый вебинар");

    private String name;

    EventsTypeData(String name) {
        this.name = name;
    }
}
