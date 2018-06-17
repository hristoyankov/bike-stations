# bike-stations

Bike stations is a simple project consuming data provided by TFL (https://tfl.gov.uk/) and shows the 5 closest stations to Marble Arch, London.

## Usage

In order to run bike-stations locally you need [Leiningen](https://leiningen.org/). Before running there a couple of things that need configuring - you need to provide TFP API credentials in `env.sh` and configure the port at which the server will run. Once done run:

   $ source ./env.sh

The service provides basic HTTP authentication. Adding new users is done by appending the data to `users_db.edn`. By default `guest:guest` user is added.

If everything is configured simple run should do the trick:

   $ lein run

## Future improvements

If I have time I'd focus on adding the following functionalities:

* Add dynamic location entry (integration with Google Maps API). The app is designed so that it can easily adopt dynamic location and number of stations displayed (by query parameters).
* Move from basic authentication to proper authentication mechanism, ideally providing SSO with Google, FB, Twitter, Github, etc.
* Deploy and setup CI/CD.
* Add styling and map (Google Maps/OSM) where the stations can be visualized and inspected.



