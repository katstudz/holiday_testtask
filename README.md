the service accepts the POST - query on "/holiday/forCountryPair" with body,
 with contains two country codes and date in YYYY-MM-DD format. It should be like:
 ```
 {
 	"firstCountryCode":"PL",
 	"secondCountryCode":"DE",
 	"date": "2016-01-01"
 }
 ```
  
As a response, user can receive response with name's of holiday for chosen country:
```
{
    "name1": "Nowy Rok",
    "name2": "Neujahrstag",
    "date": "2016-01-01"
}
```

Service return just first holiday's name for every country, so if somewhere in world are
more celebrations, it will be ignored. When there's no holiday, we get "_" as response:
```
{
    "name1": "_",
    "name2": "_",
    "date": "2016-12-31"
}
```

To run project: use runholidaypair.sh or open it in some IDE.
