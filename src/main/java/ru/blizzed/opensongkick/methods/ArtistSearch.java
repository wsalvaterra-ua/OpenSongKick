package ru.blizzed.opensongkick.methods;

import ru.blizzed.opensongkick.models.Artist;
import ru.blizzed.opensongkick.models.ResultsPage;

import java.util.Map;

public class ArtistSearch {

    private ArtistSearchCaller artistSearchCaller;

    public ArtistSearch(ArtistSearchCaller artistSearchCaller) {
        this.artistSearchCaller = artistSearchCaller;
    }

    public ApiCaller<ResultsPage<Artist>> byName(String name) {
        return new ApiCaller<>(artistSearchCaller.byName(name));
    }

    public ApiCaller<ResultsPage<Artist>> byName(String name, Map<String, String> params) {
        return new ApiCaller<>(artistSearchCaller.byName(name, params));
    }

}
