try-jq
====

**jq**

> download: https://stedolan.github.io/jq/download/

```bash
$ cd try-jq

$ mkdir jsonq && cd jsonq

$ cp -ip "$HOME/Downloads/jq-win64.exe" .

$ ls -1
## jq-win64.exe*

$ mv -v jq-win64.exe jq
## renamed 'jq-win64.exe' -> 'jq'

$ ls -1
## jq*
```

## example.json IF

[example.json](fixtures/example.json)

```
export interface Example {
  data?: (DataEntity)[] | null;
}
export interface DataEntity {
  ids?: (string)[] | null;
  str: string;
}
```

## Bookmarks

* [jq Manual (development version)](https://stedolan.github.io/jq/manual/)
* [jq play](https://jqplay.org/)
* [MakeTypes from JSON samples](https://jvilk.com/MakeTypes/)
* [json2ts - generate TypeScript interfaces from json](http://json2ts.com/)
