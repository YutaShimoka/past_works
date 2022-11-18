# http code checker

Whether the http code is 200.
---

## How to use

```bash
$ cd http-code-checker

$ ./is_200.sh "https://www.google.com"
## 200

$ ./is_200.sh "https://www.google.com/teapot"
## url=https://www.google.com/teapot, http_code=418

$ echo $?
## 1
```
