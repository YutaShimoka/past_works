# Markdown table to TSV converter

This is a shell script to convert a Markdown table to a TSV file.

## How to use

```bash
$ cd markdown-table-to-tsv-converter

$ ./main.sh test/resources/test_data.md
## start process since 'test/resources/test_data.md' exists
```

## Result

```
$ zcat result/test_data.tsv.gz
id      name
1       test 1
2       test 2
3       test 3
```
