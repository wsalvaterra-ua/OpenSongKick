# OpenSongKick
Simple Java library for [Songkick](http://songkick.com) API

**OpenSongKick** provides easy access to all available methods of [SongKick API][doc]. 
Can be useful for grabbing data of events, venues, metro areas, etc.

* Contains Java object wrappers for any API response
* Supports paginated result response (search or lists with many objects)
* Convenient request builders

Full list of methods available in [OpenSongKick API Documentation][doc]

## Installing

#### Maven

In your pom.xml inside the *\<dependencies>* tag
```xml
<dependencies>
    ...
    <dependency>
        <groupId>ru.blizzed</groupId>
        <artifactId>opensongkick</artifactId>
        <version>1.0.2</version>
    </dependency>
</dependencies>
```

#### Gradle

In your build.gradle file inside the *dependencies* section

* Gradle 3.0 and above
``` 
dependencies {
   ...
   implementation 'ru.blizzed:opensongkick:1.0.2'
}
```
  
* Below 3.0
``` 
dependencies {
    ...
    compile 'ru.blizzed:opensongkick:1.0.2'
}
```
  
## Usage

#### Initialization

```java 
OpenSongKickContext.initialize("your-api-key"));
```

#### Building and executing requests

All methods of OpenSongKick API are available in `SongKickApi` class after initialization.
You can pass any params to the call – just take a look at static class `SongKickParams` that 
contains completed instances of all parameters.

Imagine that you need to find an event by user location

```java 
long lat = 48.1550547;
long lon = 11.4017526;
```

```java 
SongKickApi.eventSearch()
        .byLocation(SongKickParams.LOCATION_GEO.of(lat, lon))
        .execute()
        .getResults()
        .forEach(System.out::println);
```

or the same but with a set of params

```java 
SongKickApi.eventSearch()
        .byLocation(
                SongKickParams.LOCATION_GEO.of(lat, lon), 
                SongKickParams.ARTIST_NAME.of("Cradle Of Filth"),
                SongKickParams.PAGE.of(2)
        )
        .execute()
        .getResults()
        .forEach(System.out::println);
```

#### Callbacks 
You can receive callbacks in two ways:

* Catching exceptions
```java 
try {
    SongKickApi.locationSearch().byQuery("Russia").execute().getResults().forEach(System.out::println);
} catch (ApiCallException | ApiErrorException e) {
    // Handle error
} 
```
* With listener
```java 
SongKickApi.locationSearch().byQuery("Russia").execute(new ApiCaller.Listener<ResultsPage<Location>>() {
    @Override
    public void onComplete(ResultsPage<Location> result, ApiCaller<ResultsPage<Location>> apiCaller) {
        result.getNameVariations().forEach(System.out::println);
    }
    @Override
    public void onError(Error error, ApiCaller<ResultsPage<Location>> apiCaller) {
        /* This method triggers you when API has been called but response contains an error */
        // Handle Api Error
    }
    @Override
    public void onFailure(ApiCallException e, ApiCaller<ResultsPage<Location>> apiCaller) {
        /* This method triggers you when call to API cannot be established. E.g. no internet connection */
        // Handle Failure
    }
});
```
**Tip:** you can override not all callback methods

#### Requests cancelling
It goes without saying that you can also cancel request immediately if needs
```java 
caller.cancel();
```

## License

```
Copyright (c) 2017 BlizzedRu (Ivan Vlasov)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[doc]: http://www.songkick.com/developer