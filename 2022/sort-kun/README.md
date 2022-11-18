# sort-kun

ソートくん

## 概要

テキストファイルをソートします。

もし重複データがあった場合、警告しますが、出力ファイルは重複している行を取り除いて出力します。

## 動作確認

```bash
$ sh sort.sh resources/input.txt
## created 'result/output.txt'

$ diff resources/input.txt result/output.txt
## 0a1
## > _
## 2,5d2
## < continue
## < for
## < new
## < switch
## 7,10d3
## < default
## < if
## < package
## < synchronized
## 12,15d4
## < do
## < goto
## < private
## < this
## 17,20d5
## < double
## < implements
## < protected
## < throw
## 22,25d6
## < else
## < import
## < public
## < throws
## 27,30d7
## < enum
## < instanceof
## < return
## < transient
## 32,35d8
## < extends
## < int
## < short
## < try
## 37,40d9
## < final
## < interface
## < static
## < void
## 42,45d10
## < finally
## < long
## < strictfp
## < volatile
## 46a12,20
## > continue
## > default
## > do
## > double
## > else
## > enum
## > extends
## > final
## > finally
## 47a22,30
## > for
## > goto
## > if
## > implements
## > import
## > instanceof
## > int
## > interface
## > long
## 48a32,40
## > new
## > package
## > private
## > protected
## > public
## > return
## > short
## > static
## > strictfp
## 49a42,50
## > switch
## > synchronized
## > this
## > throw
## > throws
## > transient
## > try
## > void
## > volatile
## 51d51
## < _
```
