# DEPRECATED Integration testing with Spring Boot, Elasticsearch, Testcontainers and JUnit5

This demo is deprecated. You can found an actual version [here](https://github.com/larmic/spring-boot-demos/tree/main/spring-boot-elasticsearch)

![Build Status](https://github.com/larmic/testcontainers-junit5/workflows/Java%20CI%20with%20Maven/badge.svg)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Simple example demonstrating how testcontainers, Elasticsearch and JUnit 5 can play together.

## Used technologies

* Spring Boot 2.5.1
* Kotlin 1.5.10
* Elasticsearch 7.10.1 with [RestHighLevelClient](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-getting-started-initialization.html)
* Testcontainers 1.15.3
* JUnit 5.7.2

## Requirements

* Java 11
* Maven >= 3.2.1 (Kotlin comes as maven dependency)
* Docker >= 3.0 (for integration tests)

##### Clone repository and build project

Integration test ```TweetControllerIT``` will be started in maven phase ```verify```.

```ssh
$ git clone https://github.com/larmic/testcontainers-junit5
$ mvn clean verify
```

##### Local testing

```ssh
# start local elasticsearch
$ docker run -d -p 9200:9200 -p 9300:9300 --name testcontainers-junit5-demo -e "discovery.type=single-node" -e "xpack.security.enabled=false" -e "cluster.name=elasticsearch" docker.elastic.co/elasticsearch/elasticsearch-oss:7.10.1

# start application
$ mvn spring-boot:run

# HTTP request examples
# Get all tweets
$ curl -i -H "Accept: application/json" --request GET http://localhost:8080/

# Post a new tweet
$ curl -i -H "Content-Type: application/json" --request POST --data 'hello, this is a tweet!' http://localhost:8080/

# Read a specific tweet     
$ curl -i -H "Accept: application/json" --request GET http://localhost:8080/{tweet-id}      
 
# Delete a specific tweet
$ curl -i -H "Accept: application/json" --request DELETE http://localhost:8080/{tweet-id}

# Update a specific tweet    
$ curl -i -H "Content-Type: application/json" "Accept: application/json" --request PUT --data 'hello, this is a changed tweet!' http://localhost:8080/{tweet-id}        
```
