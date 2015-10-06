## JCScheme

After programming so many years, I finally write my own language !

```
JCScheme = Jiacai's Scheme 😊
```
## Concepts

JCScheme use [S-expression](https://en.wikipedia.org/wiki/S-expression) internally to represent [AST](https://en.wikipedia.org/wiki/Abstract_syntax_tree) like any other Scheme. 
[SExpression.java](src/main/java/net/liujiacai/jcscheme/SExpression.java) is the implementation of `S-expression`, which is the core part of JCScheme.
   
FYI, diagram below is the `S-expression` of `(+ 1 2 (* 3 4))` expressed inside `SExpression.java`.
![AST_demo](https://img.alicdn.com/imgextra/i3/581166664/TB2OftZfVXXXXbTXpXXXXXXXXXX_!!581166664.png)

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

The very first version of JCScheme support:
1. datatype: Number, Bool, Function
2. keyword: `if`, `def`, `lambda`
3. literal: `true`, `false`

More new features can be found at [Change Log](#ChangeLog)。
```shell
rlwrap java -jar target/JCScheme-*.jar
>> (* 2 3 4 5)
120
>> (def a 4)
null
>> (def b 5)
null
>> (if (> a b) a b)
5
>> (def max (lambda (a b) (if (> a b) a b)))
null
>> (def c (max a b))
null
>> c
5
```

## ChangeLog

- 0.0.1-SNAPSHAT, 2015/10/03
- 0.0.2-SNAPSHAT, 2015/10/04
  1. support [immediately invoked function](https://en.wikipedia.org/wiki/Immediately-invoked_function_expression)
```
>> ((lambda (a b) (if (> a b) a b)) 3 4)
4
>> (lambda (a b) (if (> a b) a b))
Function :
	Args : [a, b]
	Body : ( if ( > a b )  a b )
```
- 0.0.3-SNAPSHAT, 2015/10/05 
  1. new datatype: `pair` and `list`
  2. new keyword: `cons`、`list`
  3. new builtin function: `car`、`cdr`、`empty?`
  4. a new literal: `nil`, which stands for empty list
```
>> (cons 1 2)
[1, 2]
>> (car (cons 1 2))
1
>> (cdr (cons 1 2))
2
>> (list) # identical to nil
nil   
>> (list 1 2)
(1, 2)
>> (car (list 1 2))
1
>> (cdr (list 1 2)) #  aware of the difference between this and (cdr (cons 1 2))
(2)
>> (empty? (cdr (list 1 2)))
false
>> (empty? (cdr (cdr (list 1 2))))
true
>> (empty? nil)
true
>> (cons 1 nil)
(1)
>> (list 1)  # identical to (cons 1 nil)
(1)
```
- 0.0.4-SNAPSHAT, 2015/10/06
  1. support [closure](https://en.wikipedia.org/wiki/Closure_%28computer_programming%29) 
  1. support [currying](https://en.wikipedia.org/wiki/Currying)
  2. support function scope
```shell
# closure demo
>> (def adder (lambda (x) (lambda (y) (+ x y))))
null
>> (def add2 (adder 2))
null
>> (add2 3)
5
# currying demo
>> (def myadd (lambda (x y) (+ x y)))
null
>> (myadd 3)
Function :
        Args : [y]
        Body : [( + x y ) ]
>> ( (myadd 3) 4)
7
# function scope demo
>> (lambda () (def a 1) a)
Function :
        Args : []
        Body : [( def a 1 ) , a]
>> ((lambda () (def a 1) a))
1
>> a    # variable a isn't in global scope
Error token: a
```  
  
## License
[MIT License](LICENSE) © Jiacai Liu