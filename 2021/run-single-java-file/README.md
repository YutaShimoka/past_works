run single java file
====

# What is this ?

`run.sh` は、Javaファイルのコンパイルと実行を行う、シェルスクリプト。

# prerequisite

* jdk

## run.shの引数

|#|Item|Value|required|
|---|---|---|:---:|
|1|第一引数|Javaファイルのファイルパス [^1]|◎|

## 簡単な動作確認

```bash
$ sh run.sh ./resources/Hello.java
## [debug] #####start ./resources/Hello.java#####
## [debug] #####compile
## [debug] #####execute
## こんにちは世界
```

test files are in [resources/](resources/)

## P.S.

Java 11から、javaコマンドで、コンパイルせずに拡張子.javaファイルの直接実行が可能になった。[^2]

**Java 8**

```bash
$ java -version
## java version "1.8.0_202"
## Java(TM) SE Runtime Environment (build 1.8.0_202-b08)
## Java HotSpot(TM) 64-Bit Server VM (build 25.202-b08, mixed mode)

$ cd run-single-java-file/resources

$ find -name "Hello*"
## ./Hello.java

$ java Hello.java
## エラー: メイン・クラスHello.javaが見つからなかったかロードできませんでした
```

**Java 11**

```bash
$ java -version
## openjdk version "11.0.13" 2021-10-19
## OpenJDK Runtime Environment Temurin-11.0.13+8 (build 11.0.13+8)
## OpenJDK 64-Bit Server VM Temurin-11.0.13+8 (build 11.0.13+8, mixed mode)

$ cd run-single-java-file/resources

$ find -name "Hello*"
## ./Hello.java

$ chcp.com 65001

$ java Hello.java
## こんにちは世界
```

---

[^1]: 絶対パス、相対パスのどちらも可。
[^2]: [「Java有償化」って、どういうこと？～Javaアップデートに関する疑問にこたえます！～ : 富士通ラーニングメディア](https://www.knowledgewing.com/kw/blog/2019/03/java-update.html)
