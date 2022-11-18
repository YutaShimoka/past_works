# shell-error_handling

## 概要

任意のShell Scriptで、以下のように[sample_handle_err.sh](./sample_handle_err.sh)を実行することで、エラー発生時、処理を即時停止し、エラーが発生した行を通知（本サンプルでは `echo メッセージ` で表示）することが可能となる。

```bash
#!/usr/bin/env bash
source ./sample_handle_err.sh

...
```

**実行例**

```bash
$ sh test.sh test_01
## [INFO] *****test.sh START*****
## [DEBUG] main function : start
## [DEBUG] sub function : start
## [DEBUG] sub function : end
## [DEBUG] main function : end
## [INFO] 32行目でエラーが発生し、即時停止します。
## [ERROR] Oops, an error occurred. [L32]
```

なお、[sample_handle_err.sh](./sample_handle_err.sh)は、関数で発生したエラーにも対応している。

* Main関数でエラーが発生した場合

```bash
$ sh test.sh test_02
## [INFO] *****test.sh START*****
## [DEBUG] main function : start
## [INFO] 22行目でエラーが発生し、即時停止します。
## [ERROR] Oops, an error occurred. [L22]
```

* Main関数が呼び出したサブ関数でエラーが発生した場合

```bash
$ sh test.sh test_03
## [INFO] *****test.sh START*****
## [DEBUG] main function : start
## [DEBUG] sub function : start
## [INFO] 13行目でエラーが発生し、即時停止します。
## [ERROR] Oops, an error occurred. [L13]
```
