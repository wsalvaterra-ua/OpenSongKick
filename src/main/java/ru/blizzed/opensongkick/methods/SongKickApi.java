package ru.blizzed.opensongkick.methods;

import ru.blizzed.opensongkick.OpenSongKickContext;
import ru.blizzed.opensongkick.models.*;

import java.io.IOException;

public final class SongKickApi {

    private static ArtistSearchCaller artistSearch;
    private static EventSearchCaller eventSearchCaller;
    private static LocationSearchCaller locationSearchCaller;
    private static VenueSearchCaller venueSearch;

    private SongKickApi() {
    }

    public static ArtistSearch searchArtist() throws IOException {
        if (artistSearch == null) artistSearch = OpenSongKickContext.getInstance().createService(ArtistSearchCaller.class);
        return new ArtistSearch(artistSearch);
    }

    public static ResultsPage<Event> searchEvent(String artistName) throws IOException {
        if (eventSearchCaller == null) eventSearchCaller = OpenSongKickContext.getInstance().createService(EventSearchCaller.class);
        return eventSearchCaller.byArtist(artistName).execute().body();
    }

    public static ResultsPage<Location> searchLocation(String query) throws IOException {
        if (locationSearchCaller == null) locationSearchCaller = OpenSongKickContext.getInstance().createService(LocationSearchCaller.class);
        return locationSearchCaller.byQuery(query).execute().body();
    }

    public static ResultsPage<Venue> searchVenue(String query) throws IOException {
        if (venueSearch == null) venueSearch = OpenSongKickContext.getInstance().createService(VenueSearchCaller.class);
        return venueSearch.byQuery(query).execute().body();
    }

}
