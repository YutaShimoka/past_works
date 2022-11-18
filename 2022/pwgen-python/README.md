# pwgen-python

パスワード生成くん

## Description

### 書式

```bash
$ ./pwgen.py -y [パスワードの長さ] [生成数]
```

* オプション `-y`
    - パスワードに記号文字を含めます。

### Sample

```bash
$ ./pwgen.py
## 0Itugh8O

$ ./pwgen.py -y
## DD87QYU%

$ ./pwgen.py 20
## yDvkFeZrl9A5cT6i2duL

$ ./pwgen.py -y 20
## Op:^^_LU<isa2c5#(i7m

$ ./pwgen.py 20 4
## CDZKvFO5SzSfOMb2jRHv
## PyJYAdIU6XeSOH59Uj6k
## WYsmRrKeecWUWpq7EUqH
## 2DnNHqYw1qnnFAmgDFH0

$ ./pwgen.py -y 20 4
## 'quYb:w?e=[q9Jnkdb<m
## *aNhF`L$=s/a]qA@S6Y7
## s]e%l@4Fh_3p!/a}z7N<
## K8`~IENEb6;AdS^k$rF)
```
