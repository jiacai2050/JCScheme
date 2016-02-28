- 0.0.1-SNAPSHAT, 2015/10/03
    1. datatype: `int`, `bool`, `function`
    2. keyword: `if`, `def`, `lambda`
    3. literal: `true`, `false`

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
  3. new builtin function: `car`、`cdr`、`null?`
  4. a new literal: `nil`, which stands for null list
```
>> (cons 1 2)
[1, 2]
>> (car (cons 1 2))
1
>> (cdr (cons 1 2))
2
>> (list) # identical to nil
()
>> (list 1 2)
(1, 2)
>> (car (list 1 2))
1
>> (cdr (list 1 2)) #  aware of the difference between this and (cdr (cons 1 2))
(2)
>> (null? (cdr (list 1 2)))
false
>> (null? (cdr (cdr (list 1 2))))
true
>> (null? nil)
true
>> (cons 1 nil)
(1)
>> (list 1)  # identical to (cons 1 nil)
(1)
```
- 0.0.4-SNAPSHAT, 2015/10/06
  1. support [closure](https://en.wikipedia.org/wiki/Closure_%28computer_programming%29)
  2. support [currying](https://en.wikipedia.org/wiki/Currying)
  3. support function scope
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
- 0.0.5-SNAPSHAT, 2015/10/11
  1. new datatype: `string`
  2. new builtin function `str=?`, used for test equality of two string
```shell
>> "abc"
"abc"
>> (def a "hello")
null
>> (def b "hello")
null
>> (str=? a b)
true
>> (str=? a "hello")
true
```
- 0.0.6-SNAPSHAT, 2016/02/28
    1. new builtin function `print`
    2. advance `def` to support function definition
    2. refactor all codes
```
>> (print (cons 1 2) (list 1 2) "java" (+ 1 2))
[1, 2]
(1, 2)
"java"
3
>> (def (inc x) (+ x 1))
>> (inc 1)
2
```
