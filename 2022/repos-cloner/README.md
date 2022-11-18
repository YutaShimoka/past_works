# repos-cloner

GitHubのリポジトリをクローンする為のツール。

## Prerequisite:

* jq

> ※ `/c/jsonq/` 配下

## Features:

| #   | ファイル名                                                | 概要                         |
| --- | ---------------------------------------------------- | -------------------------- |
| 1   | [clone.sh](clone_template.sh) [^1]                   | クローンします。既にクローン済みの場合はプルします。 |
| 2   | [clone_if_not_present.sh](./clone_if_not_present.sh) | クローンされていないリポジトリのみクローンします。  |

## How to Use:

### clone.sh

```bash
$ cd repos-cloner

$ cp -ip clone_template.sh clone.sh

$ vi clone.sh

$ sh clone.sh
```

> **Note**<br>
> viコマンドでは、次の箇所を編集します。

<details><summary>Click to expand</summary><br>

**default**

デフォルトだとコメントアウトされているのでクローン出来ません。

```bash
## execution

user=""
repos=("")
# clone_func_v1 ${user} "${repos[@]}" # commented out by default
# clone_func_v2 ${user}               # commented out by default
```

**sample 1**

次のサンプルでは、任意のユーザ (ここでは `******` さん) の `hoge`、`fuga`、`piyo` というリポジトリをクローンします。

```bash
## execution

user="******"
repos=("hoge" "fuga" "piyo")
clone_func_v1 ${user} "${repos[@]}"
# clone_func_v2 ${user}               # commented out by default
```

**sample 2**

次のサンプルでは、任意のユーザ (ここでは `******` さん) がGitHub上で公開している全てのリポジトリを一括クローンします。

```bash
## execution

user="******"
repos=("")
# clone_func_v1 ${user} "${repos[@]}" # commented out by default
clone_func_v2 ${user}
```

</details>

### clone_if_not_present.sh

```bash
$ cd repos-cloner

$ sh clone_if_not_present.sh "${user}"
```

---

[^1]: [関数一覧](./docs/functions.md)
