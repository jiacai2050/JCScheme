## Normal_order JCScheme

This branch implements normal order JCScheme. Forked from [master branch](https://github.com/jiacai2050/JCScheme/tree/ebf9fdf4ea0c69ba3175307e1fedaf8b868b758b)

You can use following code snippets to test:

```
>> (def (p) (p))
>> (def (test x y) (if (= x 0) 0 y))
>> (test 0 (p))
0
```

The result of `(test 0 (p))` behaves different regarding to evaluation strategy

- `normal order` will return `0`
- `applicative order` will cause dead loop


## Main difference from master branch

Instead of directly evaluated arguments when apply a function or define a variable, JCScheme wrap arguments in [JCTrunk](src/main/java/net/liujiacai/jcscheme/JCTrunk.java) and only evaluate arguments as needed.

## License
[MIT License](http://liujiacai.net/license/MIT.html?year=2015) © Jiacai Liu
