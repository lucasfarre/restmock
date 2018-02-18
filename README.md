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

`curl -X POST -d '{"method":"get","status":200,"headers":{"Content-Type":"application/json"},"body":"{\"message\":\"Hello, World!\"}"}' http://localhost/mock`

> You might escape your body as the example above.

You'll get a response like this:
```
{  
   "id":"73f01ced-48be-46ff-ad68-5f4103517d3b",
   "method":"get",
   "status":200,
   "headers":{  
      "Content-Type":"application/json"
   },
   "body":"{\"message\":\"Hello, World!\"}"
}
```
#### Use your mock
Now you have your mock id, you can try it:

`curl -v http://localhost/mock/73f01ced-48be-46ff-ad68-5f4103517d3b`

You'll get the response mock you requested earlier:
```
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 80 (#0)
> GET /mock/73f01ced-48be-46ff-ad68-5f4103517d3b HTTP/1.1
> Host: localhost
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
