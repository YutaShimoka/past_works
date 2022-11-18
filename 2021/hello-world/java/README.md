# Hello World

## Java

```java
public class Hello {

    public static void main(String[] args) {

        System.out.println("こんにちは世界");
    }

}
```

## How to use

```
C:\Users\5h1m0kayu02>prompt $n$s$g

C >cd Documents/GitHub/works/2021/hello-world/java

C >chdir
C:\Users\5h1m0kayu02\Documents\GitHub\works\2021\hello-world\java

C >dir /b | findstr Hello
Hello.java

C >javac -encoding utf-8 Hello.java

C >dir /b | findstr Hello
Hello.class
Hello.java

C >java Hello
こんにちは世界
```

## Rollback

<details><summary>Click to expand</summary><br>

```
C >dir /b | findstr Hello
Hello.class
Hello.java

C >del Hello.class

C >dir /b | findstr Hello
Hello.java

C >java Hello
エラー: メイン・クラスHelloを検出およびロードできませんでした
原因: java.lang.ClassNotFoundException: Hello
```

</details>

## References

* [DOSコマンドプロンプトのフルパス表示を消す方法 | 非IT企業に勤める中年サラリーマンのIT日記](http://pineplanter.moo.jp/non-it-salaryman/2017/09/18/cmd-hidden-path/)
