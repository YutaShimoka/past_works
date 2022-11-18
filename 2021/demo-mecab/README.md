demo-mecab
====

## 前提条件

### Step 1 : MaCabをダウンロード/インストール

http://taku910.github.io/mecab/

### Step 2 : pipでのMeCabのインストール

```bash
$ pip install mecab
```

## 準備

### 青空文庫から任意の作品をダウンロード

```bash
$ cd demo-mecab

$ mkdir -p test/resources && cd test/resources

$ target_url="https://www.aozora.gr.jp/cards/000148/files/789_ruby_5639.zip"

$ curl -Ss ${target_url} > tmpfile && unzip -q tmpfile -d . && rm tmpfile && ls -1
## wagahaiwa_nekodearu.txt
```

### データクリーニング

冒頭、末尾の部分は、データとして取り込むには相応しくない為、削除しておく。

```bash
$ ls -1
## wagahaiwa_nekodearu.txt

$ vi wagahaiwa_nekodearu.txt
```

* 冒頭

<details><summary>Click to expand</summary><br>

```
-------------------------------------------------------
【テキスト中に現れる記号について】

《》：ルビ
（例）吾輩《わがはい》

｜：ルビの付く文字列の始まりを特定する記号
（例）一番｜獰悪《どうあく》

［＃］：入力者注　主に外字の説明や、傍点の位置の指定
　　　（数字は、JIS X 0213の面区点番号またはUnicode、底本のページと行数）
（例）※［＃「言＋墟のつくり」、第4水準2-88-74］

〔〕：アクセント分解された欧文をかこむ
（例）〔Quid aliud est mulier nisi amicitiae& inimica〕
アクセント分解についての詳細は下記URLを参照してください
http://www.aozora.gr.jp/accent_separation.html
-------------------------------------------------------
```

</details>

* 末尾

<details><summary>Click to expand</summary><br>

```
底本：「夏目漱石全集1」ちくま文庫、筑摩書房
　　　1987（昭和62）年9月29日第1刷発行
底本の親本：「筑摩全集類聚版　夏目漱石全集　1」筑摩書房
　　　1971（昭和46）年4月5日初版
初出：「ホトトギス」
　　　1905（明治38）年1月、2月、4月、6月、7月、10月
　　　1906（明治39）年1月、3月、4月、8月
※誤植を疑った箇所を、底本の親本の表記にそって、あらためました。
入力：柴田卓治
校正：渡部峰子（一）、おのしげひこ（二、五）、田尻幹二（三）、高橋真也（四、七、八、十、十一）、しず（六）、瀬戸さえ子（九）
1999年9月17日公開
2018年2月5日修正
青空文庫作成ファイル：
このファイルは、インターネットの図書館、青空文庫（http://www.aozora.gr.jp/）で作られました。入力、校正、制作にあたったのは、ボランティアの皆さんです。
```

</details>

## demo_mecab.pyの実行

```bash
$ cd demo-mecab

$ python demo_mecab.py wagahaiwa_nekodearu.txt
## result file: result/wagahaiwa_nekodearu.csv
```

## 出力ファイル確認

```bash
$ wc -l result/wagahaiwa_nekodearu.csv
## 206402 result/wagahaiwa_nekodearu.csv

$ head result/wagahaiwa_nekodearu.csv
## 吾輩,名詞,代名詞,一般,*,*,*,吾輩,ワガハイ,ワガハイ
## は,助詞,係助詞,*,*,*,*,は,ハ,ワ
## 猫,名詞,一般,*,*,*,*,猫,ネコ,ネコ
## で,助動詞,*,*,*,特殊・ダ,連用形,だ,デ,デ
## ある,助動詞,*,*,*,五段・ラ行アル,基本形,ある,アル,アル
## 夏目,名詞,固有名詞,人名,姓,*,*,夏目,ナツメ,ナツメ
## 漱石,名詞,固有名詞,人名,名,*,*,漱石,ソウセキ,ソーセキ
## 一,名詞,数,*,*,*,*,一,イチ,イチ
## 　,記号,空白,*,*,*,*,　,　,　
## 吾輩,名詞,代名詞,一般,*,*,*,吾輩,ワガハイ,ワガハイ

$ tail result/wagahaiwa_nekodearu.csv
## 得,動詞,自立,*,*,一段,未然形,得る,エ,エ
## られ,動詞,接尾,*,*,一段,未然形,られる,ラレ,ラレ
## ぬ,助動詞,*,*,*,特殊・ヌ,基本形,ぬ,ヌ,ヌ
## 。,記号,句点,*,*,*,*,。,。,。
## 南無阿弥陀仏,名詞,一般,*,*,*,*,南無阿弥陀仏,ナムアミダブツ,ナムアミダブツ
## 南無阿弥陀仏,名詞,一般,*,*,*,*,南無阿弥陀仏,ナムアミダブツ,ナムアミダブツ
## 。,記号,句点,*,*,*,*,。,。,。
## ありがたい,形容詞,自立,*,*,形容詞・アウオ段,基本形,ありがたい,アリガタイ,アリガタイ
## ありがたい,形容詞,自立,*,*,形容詞・アウオ段,基本形,ありがたい,アリガタイ,アリガタイ
## 。,記号,句点,*,*,*,*,。,。,。
```

## Bookmarks

* [青空文庫　Aozora Bunko](https://www.aozora.gr.jp/index.html)
