package ru.blizzed.opensongkick.methods;

import ru.blizzed.opensongkick.models.Artist;
import ru.blizzed.opensongkick.models.ResultsPage;
import ru.blizzed.opensongkick.params.Param;
import ru.blizzed.opensongkick.params.ParamsConverter;

public class ArtistSearch {

    private ArtistSearchCaller artistSearchCaller;

    ArtistSearch(ArtistSearchCaller artistSearchCaller) {
        this.artistSearchCaller = artistSearchCaller;
    }

    public ApiCaller<ResultsPage<Artist>> byName(String name) {
        return new ApiCaller<>(artistSearchCaller.byName(name));
    }

    public ApiCaller<ResultsPage<Artist>> byName(String name, Param... params) {
        return new ApiCaller<>(artistSearchCaller.byName(name, ParamsConverter.asMap(params)));
    }

}
