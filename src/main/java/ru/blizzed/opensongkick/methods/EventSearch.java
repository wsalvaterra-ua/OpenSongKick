package ru.blizzed.opensongkick.methods;

import ru.blizzed.opensongkick.models.Event;
import ru.blizzed.opensongkick.models.ResultsPage;
import ru.blizzed.opensongkick.params.LocationParam;
import ru.blizzed.opensongkick.params.Param;
import ru.blizzed.opensongkick.params.ParamsConverter;

public class EventSearch {

    private EventSearchCaller eventSearchCaller;

    public EventSearch(EventSearchCaller eventSearchCaller) {
        this.eventSearchCaller = eventSearchCaller;
    }

    public ApiCaller<ResultsPage<Event>> byArtist(String artistName) {
        return new ApiCaller<>(eventSearchCaller.byArtist(artistName));
    }

    public ApiCaller<ResultsPage<Event>> byArtist(String artistName, Param... params) {
        return new ApiCaller<>(eventSearchCaller.byArtist(artistName, ParamsConverter.asMap(params)));
    }

    public ApiCaller<ResultsPage<Event>> byArtist(LocationParam location) {
        return new ApiCaller<>(eventSearchCaller.byLocation(location.toString()));
    }

    public ApiCaller<ResultsPage<Event>> byArtist(LocationParam location, Param... params) {
        return new ApiCaller<>(eventSearchCaller.byLocation(location.toString(), ParamsConverter.asMap(params)));
    }

}
