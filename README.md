# My familys lap times in karting - server
Just playing around with Kotlin and REST... :)

This will act as a server for my upcoming application "fastestlaps-client", that will be a frontend implemented in [ReactJS](https://reactjs.org).

## Application data
The application is pre-loaded with some test data. 

## REST-API
The application exposes a traditional (non-discoverable) REST-API with GET and POST for the entities at hand.  
Use [curl](https://curl.haxx.se/) or, more preferred, [HTTPie](https://httpie.org/) to try out the services.  
All examples below use HTTPie.

### Drivers

### All
```http -b "http://localhost:8080/drivers"```
```json
[
    {
        "id": 1, 
        "name": "Peter"
    }, 
    {
        "id": 2, 
        "name": "Adam"
    }
]
```

#### By id
```http -b "http://localhost:8080/drivers/1"```
```json
{
    "id": 1, 
    "name": "Peter"
}
```

#### Create new
```http -b POST "http://localhost:8080/drivers" id=3 name=Felix```

### Laptimes

#### All
```http -b "http://localhost:8080/laptimes"```
```json
[
    {
        "date": "2017-12-05", 
        "description": null, 
        "driver": {
            "id": 2, 
            "name": "Adam"
        }, 
        "id": 4, 
        "kart": "JUNIOR125", 
        "time": 39.38, 
        "track": {
            "id": 2, 
            "name": "Amsberg"
        }
    }, 
    {
        "date": "2017-12-03", 
        "description": null, 
        "driver": {
            "id": 1, 
            "name": "Peter"
        }, 
        "id": 2, 
        "kart": "KZ2", 
        "time": 30.81, 
        "track": {
            "id": 1, 
            "name": "Hedemora"
        }
    }, 
    {
        "date": "2017-11-04", 
        "description": null, 
        "driver": {
            "id": 1, 
            "name": "Peter"
        }, 
        "id": 1, 
        "kart": "KZ2", 
        "time": 31.26, 
        "track": {
            "id": 1, 
            "name": "Hedemora"
        }
    }, 
    {
        "date": "2017-11-23", 
        "description": null, 
        "driver": {
            "id": 2, 
            "name": "Adam"
        }, 
        "id": 3, 
        "kart": "JUNIOR125", 
        "time": 31.29, 
        "track": {
            "id": 1, 
            "name": "Hedemora"
        }
    }
]
```

#### By driver(id)
```http -b "http://localhost:8080/laptimes?driverId=1"```

```json
[
    {
        "date": "2017-11-04", 
        "description": null, 
        "driver": {
            "id": 1, 
            "name": "Peter"
        }, 
        "id": 1, 
        "kart": "KZ2", 
        "time": 31.26, 
        "track": {
            "id": 1, 
            "name": "Hedemora"
        }
    }, 
    {
        "date": "2017-12-03", 
        "description": null, 
        "driver": {
            "id": 1, 
            "name": "Peter"
        }, 
        "id": 2, 
        "kart": "KZ2", 
        "time": 30.81, 
        "track": {
            "id": 1, 
            "name": "Hedemora"
        }
    }
]
```

#### By track(id)
```http -b "http://localhost:8080/laptimes?trackId=1"```

```json
[
    {
        "date": "2017-12-03", 
        "description": null, 
        "driver": {
            "id": 1, 
            "name": "Peter"
        }, 
        "id": 2, 
        "kart": "KZ2", 
        "time": 30.81, 
        "track": {
            "id": 1, 
            "name": "Hedemora"
        }
    }, 
    {
        "date": "2017-11-04", 
        "description": null, 
        "driver": {
            "id": 1, 
            "name": "Peter"
        }, 
        "id": 1, 
        "kart": "KZ2", 
        "time": 31.26, 
        "track": {
            "id": 1, 
            "name": "Hedemora"
        }
    }, 
    {
        "date": "2017-11-23", 
        "description": null, 
        "driver": {
            "id": 2, 
            "name": "Adam"
        }, 
        "id": 3, 
        "kart": "JUNIOR125", 
        "time": 31.29, 
        "track": {
            "id": 1, 
            "name": "Hedemora"
        }
    }
]
```

#### By driver(id) and track(id)
```http -b "http://localhost:8080/laptimes?trackId=1&driverId=1"
```json
[
    {
        "date": "2017-12-03", 
        "description": null, 
        "driver": {
            "id": 1, 
            "name": "Peter"
        }, 
        "id": 2, 
        "kart": "KZ2", 
        "time": 30.81, 
        "track": {
            "id": 1, 
            "name": "Hedemora"
        }
    }, 
    {
        "date": "2017-11-04", 
        "description": null, 
        "driver": {
            "id": 1, 
            "name": "Peter"
        }, 
        "id": 1, 
        "kart": "KZ2", 
        "time": 31.26, 
        "track": {
            "id": 1, 
            "name": "Hedemora"
        }
    }
]
```

#### By kart
```http -b "http://localhost:8080/laptimes?kart=JUNIOR125"```
```json
[
    {
        "date": "2017-12-05", 
        "description": null, 
        "driver": {
            "id": 2, 
            "name": "Adam"
        }, 
        "id": 4, 
        "kart": "JUNIOR125", 
        "time": 39.38, 
        "track": {
            "id": 2, 
            "name": "Amsberg"
        }
    }, 
    {
        "date": "2017-11-23", 
        "description": null, 
        "driver": {
            "id": 2, 
            "name": "Adam"
        }, 
        "id": 3, 
        "kart": "JUNIOR125", 
        "time": 31.29, 
        "track": {
            "id": 1, 
            "name": "Hedemora"
        }
    }
]
```



<details><summary>stuff with *mark* **down**</summary><p>

## _formatted_ **heading** with [a](link)

---
{{standard 3-backtick code block omitted from here due to escaping issues}}
asghasdhdf
asfghahf
---

Collapsible until here.
</p></details>