# rps

Python
---

## code

[ここをクリック](./main.py)

## 使い方

```bash
$ cd rps/python
$ ./main.py
```

## 単体テスト（Unit test, UT）

### 正常系

<details><summary>Click to expand</summary><br>

|#|Player Hand|NPC\*1 Hand|Expected Result|Result|
|---|:---:|:---:|:---:|:---:|
|1|0: グー|0: グー|あいこ|<font color="Green">OK</font>|
|2|0: グー|1: パー|負け|<font color="Green">OK</font>|
|3|0: グー|2: チョキ|勝ち|<font color="Green">OK</font>|
|4|1: パー|0: グー|勝ち|<font color="Green">OK</font>|
|5|1: パー|1: パー|あいこ|<font color="Green">OK</font>|
|6|1: パー|2: チョキ|負け|<font color="Green">OK</font>|
|7|2: チョキ|0: グー|負け|<font color="Green">OK</font>|
|8|2: チョキ|1: パー|勝ち|<font color="Green">OK</font>|
|9|2: チョキ|2: チョキ|あいこ|<font color="Green">OK</font>|

\*1 ノンプレイヤーキャラクター（non player character, NPC）

</details>

### 異常系

TODO
