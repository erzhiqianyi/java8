# java8
java8 note and code example

## java8新特性
- Stream Api
- Lambda 表达式 
- 行为参数化
- 接口中的默认方法
- Date Time Api
- Optional 类

## Stream 和 Collection的区别
- Stream 
    描述对数据的计算 
- Collection 
    存储和访问数据

## 行为参数化
 让方法接受多种行为作为参数，并在内部使用，来完成不同的行为。
 
## Lambda 表达式
   简洁地可传递的匿名函数
 - 匿名
    没有明确名称
 - 函数
    有参数列表，函数主体，返回类型，可抛出异常
 - 传递
    可作为参数传递给方法或存储在变量中
 - 简洁
### 组成
```
() -> {}
``` 
- 参数列表
    可以没有参数 
- 箭头
- 主体
   如果函数主体只有一行，可不用{}
   
### 在哪里使用
  在函数式接口中使用。函数式接口是只定义一个抽象方法的接口

### 常用函数式接口
- Predicate 

    表示一个设计类型T的布尔表达式。 判断 
```java
@FunctionalInterface
public interface Predicate<T> {
   boolean test(T t);
} 
 ```
- Consumer 

    接受泛型T的对象并处理该对象，没有返回。消费
```java
@FunctionalInterface
public interface Consumer<T> {
   void accept(T t);
} 
 ```  
 
- Function

   接受一个泛型T的对象，并返回一个泛型R的对象。转换 
```java 

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
```
- Supplier

   不接受参数，返回一个类型T的参数。生产
   
```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
   
```
- UnaryOperator

   返回输入类型 

```java
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {
    static <T> UnaryOperator<T> identity() {
        return t -> t;
    }
}
```

- BiFunction 

  接收两个参数并返回一个结果。转换
        
```java

@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
    default <V> BiFunction<T, U, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u) -> after.apply(apply(t, u));
    }
}
```

- BiPredicate

  接收两个参数并返回一个布尔值。判断 

```java

@FunctionalInterface
public interface BiPredicate<T, U> {
    boolean test(T t, U u);
}
```

- BiConsumer 
  接收两个参数，不返回结果。消费

```java

@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T t, U u);
}
```
- BinaryOperator
   接收两个相同的T对象，并返回其中一个。转换  
   
```java
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T,T,T> {
    static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }
    
    static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }

 
}
```

### 基本类型函数式接口
基本类型为避免拆装箱，提供了基本类型的函数式接口
### Lambda 表达式类型检查
从Lambda的上下文推断类型，上下文中的Lambda表达式需要的类型为目标类型 
```java
List<String> appleColor = map(apples,(Apple apple)  -> apple.getColor() )
``` 
### Lambda 表达式类型推断
### Lambda 表达式类型限制

