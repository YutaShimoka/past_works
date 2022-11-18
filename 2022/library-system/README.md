# library-system
 
## Requirements

* .NET Core 3.1 / SDK 3.1.417
* SQL Server (Expressエディション)

## Settings

**SQL Server**

| #   | Item    | Value      |
| --- | ------- | ---------- |
| 1   | インスタンス名 | SQLEXPRESS |
| 2   | データベース名 | db_example |

## Preparation

1. `appsettings.json` を作成する。

```bash
$ cd library-system

$ sed "s/\*\*\*\*\*\*/$(hostname)/g" <appsettings_template.json >appsettings.json
```

2. Visual StudioでNuGetパッケージ マネージャー コンソールを開き、次のコマンドを叩く。

> ※ `ツール` > `NuGetパッケージ マネージャー` > `パッケージ マネージャー コンソール`

```
PM> Add-Migration InitialCreate
PM> Update-Database
```

## How to use

**Server Start**

```bash
$ cd library-system

$ dotnet run
```

ブラウザから `http://localhost:5000` にアクセス。
