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
### Lambda 表达式类型推断

### Lambda 表达式类型限制

### 方法引用
重复使用现有的方法定义，并像Lambda一样传递他们
```
目标引用::方法名
Object::toString
```
- 静态方法引用
- 任意类型实例方法引用
- 现有对象的实例方法引用

### 构造函数引用
```
ClassName::new
```

## Stream 流
以声明的方式处理数据集合
### 流是什么
从支持数据处理操作的源生成的元素序列
- 元素序列
    以计算为目的
- 源
    使用一个提供数据的源，如集合，数组或输入输出资源
- 数据处理操作    
    如filter,map,reduce,find,match,sort等
- 流水线
    很多流操作返回一个流，多个操作可链接起来，形成一个大的流水线
- 内部迭代
    不需要显示迭代  
- 只能遍历一次
    流只能遍历一次，遍历完后，不能再次操作
### 流式代码特点
- 声明性-更简洁，更易读
- 可复合-更灵活
- 可并行-性能更好

### 流操作
####  中间操作
可以连接起来的流操作,除非流水线上触发一个终端操作，否则中间操作不会执行任何处理。

- filter 
    过滤
- map
    映射
- flatMap
    饼平化 
- sorted 
    排序
- distinct
    去重
- limit 
    截取   
- skip
    跳过元素
####  终端操作
关闭流的操作。从流水线生成结果。

- forEach
    消费
- count
    计算总数
- collect 
    收集
### 流的使用
- 数据源执行查询
- 中间操作形成流水线
- 终端操作生成结果

### 构建器模式

##  流操作

### 筛选 filter
使用 filter 筛选 
```java
@FunctionalInterface
public interface Predicate<T> {
   boolean test(T t);
} 
 ```
### 去重 distinct
Stream.distinct() ,更具equal()判断对象是否相等。

### 截取 limit 
Stream.limit()

### 跳过元素 skip
Stream.skip()
### 映射 map
Stream.map(),接受一个函数作参数，函数被应用到每个元素上，将其映射成一个新的元素 
### 流饼平化  flatMap
Stream.flatMap(),一个流中的每个值都换成另一个流

### 匹配
- 至少匹配一个元素 anyMatch

Stream.anyMatch()

- 匹配所有元素 allMatch

Stream.allMatch()

- 没有任何元素匹配  noneMatch

Stream.noneMatch()

### 查找 

- 查找任意元素 findAny

Stream.findAny()

- 查找第一个元素  findFirst

Stream.findFirst()

### 归约 reduce
```java
T reduce(T identity, BinaryOperator<T> accumulator);

Optional<T> reduce(BinaryOperator<T> accumulator);

<U> U reduce(U identity,<U, ? super T, U> accumulator,
                 BinaryOperator<U> combiner);

```
#### 元素求和
- Integer::sum
```java
IntStream.of(1,2,3,4,5,6).reduce(0,(x,y) -> x+y);

IntStream.of(1,2,3,4,5,6).reduce(0,Integer::sum);
```
#### 最大值最小值  

- Integer::max
- Integer::min
```java

IntStream.of(1, 2, 3, 4, 5, 6).reduce(Integer::max).getAsInt();

IntStream.of(1, 2, 3, 4, 5, 6).reduce( Integer::min).getAsInt();
```

### 数值流
避免拆装箱成本
#### 原始类型流
- IntStream
- DoubleStream
- LongStream

#### 映射到数值流 
- mapToInt
- mapToDouble
- mapToLong

#### 转回到对象流
boxed()
#### 数值范围 
- range()
- rangeClosed()

### 创建流
- Stream.of()
```java
public static<T> Stream<T> of(T... values) {
        return Arrays.stream(values);
}
``` 
- 空流  Stream.empty()

- 由数组创建流 Arrays.stream()
```java
public static <T> Stream<T> stream(T[] array) {
        return stream(array, 0, array.length);
}
```
- 由文件生成流 Files.lines()

```java
Stream<String> lines = Files.lines( Paths.get("README.md"));
lines.forEach(System.out::println);
```

- 由函数生成流 生成无限流
    - Stream.iterate()
        依次生成一系列值 
        ```java
        public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f) {}
        ``` 
    - Stream.generate()
        接受 Supplier 并返回Stream
        ```java
            public static<T> Stream<T> generate(Supplier<? extends T> s) {
            }
        ```

## 流收集数据
### 预定义收集器
- 流元素规约汇总为一个数

    选择合适的操作
    - 最大值和最小值
        - maxBy() 最大值
        - max()  最大值 
        - minBy() 最小值
        - min() 最小值
    - 总和
        - summingInt() 计算int总和
        - summingLong() 计算long总和
        - summingDouble() 计算double总和
    - 平均值
        - averageInt() 计算int平均值  
        - averageLong() 计算long平均值
        - averageDouble() 计算double平均值
    - 统计分析 
    
        计算总数量，总和，最小值，平均值，最大值
        - summarizingInt()
        - summarizingLong()
        - summarizingDouble()
    - joining() 连接字符串 
    - counting() 计算总数
    - reducing() 广义规约
    
- 元素分组
   - groupingBy() 
   - 多级分组 groupingBy()作为groupingBy()的参数 
   - 按子组收集数据 其他收集器作为groupingBy()的参数
   - 将收集器的结果转换为另一种类型 
    collectingAndThen()
- 元素分区 
    分组的特殊情况，key为布尔值
    partitioningBy()
    
### Collectors方法
    - toList()
    - toSet()
    - toCollection()
    - counting()
    - summingInt()
    - averagingInt()
    - summarizingInt()
    - joining()
    - maxBy()
    - minBy()
    - reducing()
    - collectingAndThen()
    - groupingBy()
    - partitioningBy() 
### 自定义收集器
- Collector 收集器接口
```java
public interface Collector<T, A, R> {
    Supplier<A> supplier();
    BiConsumer<A, T> accumulator();
    BinaryOperator<A> combiner();
    Function<A, R> finisher();
    Set<Characteristics> characteristics();
}
```
- Stream.collect()
```java
public interface Stream<T> extends BaseStream<T, Stream<T>> {
 <R> R collect(Supplier<R> supplier,
                  BiConsumer<R, ? super T> accumulator,
                  BiConsumer<R, R> combiner);  
}
```
## 并行流
在并行流中避免共享状态
- 注意测试并行和顺序的性能差别
- 留意装箱
- 操作是否适用于并行流
- 小数据流考虑使用顺序流
- 考虑流水线总计算成本
- 数据结构是否利于拆分
- 合并操作代价