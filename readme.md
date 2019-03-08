# Using Elasticsearch 6.3.1 over HTTP with Spring Boot 2.1.M1 and Kotlin 1.2.60

With Spring Boot 2.1.0.M1 [RestHighLevelClient](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-getting-started-initialization.html)
is injectable. When using [Amazon Elasticsearch Service](https://aws.amazon.com/de/elasticsearch-service/) only http port
is available so Spring Data does not work as default. This demo shows how http connection could work with latest
Spring Boot milestone.

## Requirements

* Java 1.8
* Maven >= 3.2.1 (Kotlin comes as a maven dependency)
* Docker >= 3.0 (for integration tests)

## Local testing

##### Clone repository and build project

```ssh
git clone https://github.com/larmic/spring-boot-elasticsearch-over-http
mvn clean package
```

##### Start local Elasticsearch

```ssh
docker run -d -p 9200:9200 -p 9300:9300 --name spring-boot-elastic-over-http -e "discovery.type=single-node" -e "xpack.security.enabled=false" -e "cluster.name=elasticsearch" docker.elastic.co/elasticsearch/elasticsearch:6.3.2
```

##### Start application

```ssh
mvn spring-boot:run
```

##### HTTP request examples

###### Get all tweets

```ssh
curl -i -H "Accept: application/json" --request GET http://localhost:8080/
```

###### Post a new tweet

```ssh
curl -i -H "Content-Type: application/json" --request POST --data 'hello, this is a tweet!' http://localhost:8080/      
```

###### Read a specific tweet

```ssh
curl -i -H "Accept: application/json" --request GET http://localhost:8080/{tweet-id}      
```

###### Delete a specific tweet

```ssh
curl -i -H "Accept: application/json" --request DELETE http://localhost:8080/{tweet-id}      
```

###### Update a specific tweet

```ssh
curl -i -H "Content-Type: application/json" "Accept: application/json" --request PUT --data 'hello, this is a changed tweet!' http://localhost:8080/{tweet-id}      
```
