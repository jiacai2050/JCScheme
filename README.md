## JCScheme

The very first toy language I wrote for studying the essence of programing language.

```
JCScheme = Jiacai's Scheme 😊
```
## Concepts

JCScheme use [S-expression](https://en.wikipedia.org/wiki/S-expression) internally to represent [AST](https://en.wikipedia.org/wiki/Abstract_syntax_tree) like any other Scheme. And [SExpression.java](src/main/java/net/liujiacai/jcscheme/SExpression.java) is the implementation of `S-expression`, which is the core of JCScheme.

FYI, diagram below is the `S-expression` of `(+ 1 2 (* 3 4))` behind `SExpression.java`.
![AST_demo](https://img.alicdn.com/imgextra/i1/581166664/TB2GS3CkFXXXXayXpXXXXXXXXXX_!!581166664.png)

More explanations can be found in my Chinese blog [《我的第一个玩具语言 JCScheme 问世了》](http://liujiacai.net/blog/2015/10/03/first-toy-scheme/)。


## Install
```shell
git clone git@github.com:jiacai2050/JCScheme.git
cd JCScheme; mvn clean package
java -jar target/JCScheme-*.jar
```

You can install `rlwrap` to support line editing, persistent history and completion.
```shell
# ubuntu
sudo apt-get install rlwrap
# centos
sudo yum install rlwrap
# Mac
brew install rlwrap  # for Homebrew
port install rlwrap  # for MacPorts
```
Then, run JCScheme like this
```shell
rlwrap java -jar target/JCScheme-*.jar
```

## Syntax

1. datatype
    - `number`
    - `bool`
    - `string`
    - `pair`
    - `list`
    - `function`
2. builtin keywords
    - `if`
    - `def`
    - `lambda`
3. builtin functions
    - `bool`: `and`、 `or`、 `not`
    - `number`: `+`、 `-`、 `*`、 `/`、 `>`、 `<`、 `=`
    - `pair/list`: `cons`、 `car`、 `cdr`、 `list`、 `null?`
    - `string`: `str=?`、
    - `other`: `print`

Code snippets can be found in [ChangeLog](ChangeLog.md)

## License
[MIT License](http://liujiacai.net/license/MIT.html?year=2015) © Jiacai Liu
