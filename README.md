## JCScheme

学习编程这么多年来，重要有了我自己的 Scheme 方言

```
JCScheme = Jiacai Scheme 😊
```


## 运行

clone 本项目后直接`mvn clean package`即可，生成jar包。

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

然后就可以运行 JCScheme 了
```
rlwrap java -jar target/JCScheme-*.jar
```
## 说明

JCScheme 的解释可以参考我的博文[我的第一个玩具语言 JCScheme 问世了](http://liujiacai.net/blog/2015/10/03/first-toy-scheme/)。

如果你有什么好的想法或建议，欢迎 PR。

## 版本 

- 0.0.1-SNAPSHAT, 2015/10/03

