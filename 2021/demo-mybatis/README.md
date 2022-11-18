demo-mybatis
====

## REST Interface

|#|Name|Method|Endpoint|
|---|---|---|---|
|1|Get Hoge|GET|`http://localhost:8080/api/hoge/{id}`|
|2|Get Hoges|GET|`http://localhost:8080/api/hoge`|
|3|Regist Hoge|POST|`http://localhost:8080/api/hoge`|
|4|Edit Hoge|PUT|`http://localhost:8080/api/hoge`|
|5|Delete Hoge|DELETE|`http://localhost:8080/api/hoge/{id}`|

### Sample

```bash
# Get a hoge data.
$ curl -i -X GET http://localhost:8080/api/hoge/xxx

# Get a list of hoges.
$ curl -i -X GET http://localhost:8080/api/hoge

# Regist a hoge data.
$ curl -i -X POST -H "Content-Type: application/json" http://localhost:8080/api/hoge -d \
    '{
        "id": "yyy",
        "str": "asdf"
    }'

# Edit a hoge data.
$ curl -i -X PUT -H "Content-Type: application/json" http://localhost:8080/api/hoge -d \
    '{
        "id": "yyy",
        "str": "ghjk"
    }'

# Delete a hoge data.
$ curl -i -X DELETE http://localhost:8080/api/hoge/yyy
```
