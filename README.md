# restmock
[![](https://travis-ci.org/lucasfarre/restmock.svg?branch=develop)](https://travis-ci.org/lucasfarre/restmock)
[![](https://codecov.io/gh/lucasfarre/restmock/branch/develop/graph/badge.svg)](https://codecov.io/gh/lucasfarre/restmock)
[![](https://img.shields.io/badge/license-MIT-blue.svg)](./LICENSE)

A REST API for mocking other REST API responses.

### Getting started
#### Start the service
1. Run `./gradlew clean installDist` to build the `app.jar` required to build the docker image
2. Run `docker-compose build` to build the docker image containing the app
3. Run `docker-compose up` to start the service

#### Create a response mock
Post a new mock with your expected response:

`curl -X POST -d '{"http_method":"get","http_status":200,"headers":{"Content-Type":"application/json"},"body":"{\"message\":\"Hello, World!\"}"}' http://localhost:8080/mock`

> You might escape your body as the example above.

You'll get a response like this:
```
{  
   "id":"b349607f1786450ea8804d2f2a6946dd",
   "http_method":"get",
   "http_status":200,
   "headers":{  
      "Content-Type":"application/json"
   },
   "body":"{\"message\":\"Hello, World!\"}"
}
```
#### Use your mock
Now you have your mock id, you can try it:

`curl -X GET -v http://localhost:8080/mock/b349607f1786450ea8804d2f2a6946dd`

You'll get the response mock you requested earlier:
```
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /mock/b349607f1786450ea8804d2f2a6946dd HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*
> 
< HTTP/1.1 200 OK
< Date: Mon, 12 Feb 2018 20:27:35 GMT
< Content-Type: application/json
< Transfer-Encoding: chunked
< Server: Jetty(9.4.6.v20170531)
< 
* Connection #0 to host localhost left intact
{"message":"Hello, World!"}
```
