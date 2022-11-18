tac-rev
====

## 概要

`tac_and_rev.sh` は、第一引数にファイル名を指定することで任意のファイルの文字列を上下左右反転させる事ができるShellScriptである。

なお、Linuxにおける `tac` コマンドは、ファイルを末尾の行から逆順に出力するコマンドである。

また `rev` コマンドは、ファイルの各行の文字列を反転させて出力するコマンドである。

しかしながら、本 `tac_and_rev.sh` は、このうち `tac` コマンドを使用したが、`rev` コマンドはGit Bashで使用できなかった為に `rev` コマンドに相当する処理は、代替としてPythonを併用し行った。[^1]

序ながら、第二引数に `--rev` を指定した場合は、左右のみ反転し、上下は反転しない、というオプションを附け加えておいた。

## 使い方

```bash
$ cd tac-rev
$ ./tac_and_rev.sh ./resources/test/test_data.txt
```

## 動作確認

```bash
$ cd tac-rev
```

**事前確認**

```bash
$ cat resources/test/test_data.txt
## 1234
## asdf
```

**tac_and_rev.shの実行**

```bash
$ ./tac_and_rev.sh ./resources/test/test_data.txt
## result file: result/test_data.txt
```

**事後確認**

```bash
$ cat result/test_data.txt
## fdsa
## 4321
```

---

[^1]: [rev_str.py](tools/rev_str.py)
