# Markdown raw contents of Qiita

See https://qiita.com

## 概要

任意のQiitaユーザが投稿している全ての記事をマークダウン形式で取得します。

## 前提条件

* jq

> download: https://stedolan.github.io/jq/download/

```bash
$ pwd
## /c

$ mkdir jsonq && cd jsonq

$ cp -i "$HOME/Downloads/jq-win64.exe" .

$ mv -v jq-win64.exe jq
## renamed 'jq-win64.exe' -> 'jq'
```

※デフォルトでは、jqのディレクトリを `/c/jsonq/jq` としています。

## 使い方

`import.sh` の第一引数に任意のQiitaユーザ名を指定してShellを実行します。

```bash
$ cd import-qiita

$ sh import.sh "${user}"
```

※出力先は `$HOME/Documents/Qiita/${user}/` 配下となります。
