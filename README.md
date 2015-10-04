## JCScheme

学习编程这么多年来，终于有了我自己的 Scheme 方言

```
JCScheme = Jiacai's Scheme 😊
```


## 运行

clone 本项目后直接`mvn clean package`即可生成jar包。

由于本项目的重点是实现解释器部分，所以 REPL 做的有些搓，方向键不能使用，Linux/Mac 用户可以安装`rlwrap`解决。下面命令参考：
```
# ubuntu
sudo apt-get install rlwrap
# centos 
sudo yum install rlwrap
# Mac
brew install rlwrap  # for Homebrew
port install rlwrap  # for MacPorts
```

然后就可以运行 JCScheme 了。
目前支持整型、布尔型、函数三种类型，支持`if`、`def`与`lambda`关键字，更多功能参考 [ChangeLog](#ChangeLog)。
```
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
## 说明

JCScheme 的解释可以参考我的博文[我的第一个玩具语言 JCScheme 问世了](http://liujiacai.net/blog/2015/10/03/first-toy-scheme/)。

如果你有什么好的想法或建议，欢迎 PR。

## <a name="ChangeLog"/>ChangeLog

- 0.0.1-SNAPSHAT，2015/10/03
- 0.0.2-SNAPSHAT，2015/10/04，增加匿名函数调用
```
>> ((lambda (a b) (if (> a b) a b)) 3 4)
4
>> (lambda (a b) (if (> a b) a b))
Function :
	Args : [a, b]
	Body : ( if ( > a b )  a b )
```
