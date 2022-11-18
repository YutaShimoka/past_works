# try-csvq

**csvq**

> download: https://github.com/mithrandie/csvq/releases

## Deploy

```bash
$ cd try-csvq

$ ls -1 ~/downloads | grep csvq
## csvq-v1.15.2-windows-amd64.tar.gz

$ cp -ip ~/downloads/csvq-v1.15.2-windows-amd64.tar.gz . && ls -1
## csvq-v1.15.2-windows-amd64.tar.gz

$ tar zxvf csvq-v1.15.2-windows-amd64.tar.gz
## csvq-v1.15.2-windows-amd64/
## csvq-v1.15.2-windows-amd64/README.md
## csvq-v1.15.2-windows-amd64/csvq.exe
## csvq-v1.15.2-windows-amd64/LICENSE
## csvq-v1.15.2-windows-amd64/CHANGELOG.md
## csvq-v1.15.2-windows-amd64/BINARY_CODE_LICENSE

$ mv -v csvq-v1.15.2-windows-amd64 csvq
## renamed 'csvq-v1.15.2-windows-amd64' -> 'csvq'

$ ls -1
## csvq/
## csvq-v1.15.2-windows-amd64.tar.gz

$ rm csvq-v1.15.2-windows-amd64.tar.gz && ls -1
## csvq/
```
