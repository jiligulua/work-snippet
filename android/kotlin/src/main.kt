package com.funtv.toolkits.android.test


fun main() {

    println(">..")
    for(i in 1..4) print(i)
    for(i in 1..4 step 2) print(i)
    for(i in 1 until 10) print(i) // 排除10

    // 函数变长参数
    vars("Hello", "World", "I", "love", "you")

    // 普通函数，带有默认参数
    var ret = sum(3)

    // 输出
    println(ret)
    println("Hello, World!")

    // lambda
    val sumLambda: (Int, Int) -> Int = {x,y -> x+y}
    println(sumLambda(1,2))

    // 定义常量和变量：val和var，在使用之前必须由初始化
    testChar()

    // NULL检查机制
    testNull()

    testApply()

    testArray()

    testConditionControl()
    testLoopControl()

    testClass()

    testNestedClass()

    testOuterClass()

    testAnonymousClass()

    // test_data()



    test_generic()

    test_enum()
    test_enum2()

    test_takeif()
}

fun testChar() : Unit {
    var a = 1
    val s1 = "a is $a" // $varName: 表示变量值

    a = 2
    val s2 = "${s1.replace("is", "was")}, but now is $a"    // ${varName.fun()}表示变量的方法返回值

    println(s2)
}

fun testNull() : Unit {
    // ?表示可为空，?:做空判断处理
    var age: String? = "23"
    // age = null
    println(age)

    // 字段后面加!!抛出空异常
    // val ages = age!!.toInt()
    val ages = age?.toInt() ?: -1
    println(">?:")
    println(ages)
   
    // age为空返回-1
    // val ages2 = ages?.toInt() ?: -1;

    // 安全的变量判断:?.，不做处理返回null，直接在变量后面添加?，表示若是null，则直接返回null，否则继续往下执行
    // Safe calls are useful in chains.
    val ages1 = age?.toInt()
    println(">toInt")
    println(ages1)


    var listWithNulls : List<String?> = listOf("Kotlin", null)
    for (item in listWithNulls) {
        item?.let{
            println(item)
        }
    }
}

fun sum(a: Int, b: Int = 4): Int {
    return a + b;
}

fun vars(vararg v: String) {
    for (vt in v) {
        print(vt + "\t")
    }
    println()
}

fun testApply() : Unit {
    println("> testApply")
    ArrayList<String>().apply {
        add("testApply1")
        add("testApply2")
    }.let{
        println(it)
    }
}

fun testArray() : Unit {
    val a = arrayOf(1, 2, 3)
    val b = Array(3, {i -> (i * 2)}) // 表示有3个数据，从0开始，进行lambda运算处理。
    
    println("> testArray")
    println(a)
    for (item in a) {
        print(item)
    }
    println()
    for (item in b) {
        print(item)
    }
    

    val text = """
    背起小书包
    我爱去小学
    """

    println(text)

    
    val s = "Hello"
    val str = "$s.length is ${s.length}"
    println(str)
}

fun testConditionControl() : Unit {
    println("> test condition control")

    val x :Int = 1

    when (x) {
        1 -> println("x == 1")
        2 -> println("x == 2")
        else -> {  // 同switch的default
            println("x不是1，也不是2")
        }
    }

    when (x) {
        0, 1 -> println("x == 0 or x == 1")
        else -> println("otherwise")
    }

    // 也可检测一个值（in）或者不在（!in）一个区间或者集合中
    val validNumbers = intArrayOf(11, 15, 18)
    when (x) {
        in 1..10 -> println("x is in the range")
        in validNumbers -> println("x is valid")
        !in 10..20 -> println("x is outside the range")
        else -> println("none of the above")
    }
}

fun testLoopControl() : Unit {
    println("\n> test loop control")

    val items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        println(item)
    }

    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}

fun testClass() : Unit {
    println("\n> test class")
    var person: Person = Person("Chris")

    person.lastName = "Wu"

    println("lastName: ${person.lastName}")

    person.no = 9
    println("no: ${person.no}")

    person.no = 20
    println("no: ${person.no}")

}


class Person constructor(firstName: String) {

    init {
        println("FirsName is $firstName")
    }

    var name: String = firstName
    var country: String = "CN"

    var lastName: String = "Gardner"
        get() = field.toUpperCase()  // field: 后端变量，表示编译之后的真实变量，统一了属性变量，赞
        set // 表示对赋值不做任何附加操作

    var no: Int = 100
        get() = field  // 后端变量，默认的get表达式
        set(value) {
            if (value < 10) {
                field = value
            } else {
                field = -1
            }
        }

    var heiht: Float = 145.4f
        private set// 说明不能够发送赋值操作，是私有变量。这么说来，默认是共有变量
}


open class Base {
    open fun f() {}
}

abstract class Derived : Base() {
    override abstract fun f() 
}


open class RichButton : Clickable {
    
    // 这个函数是final的，不能在子类中重写它
    fun disable() {}

    // 这个函数是open的，可以在子类当中重写
    open fun animate() {}

    // 这个函数重写了一个open函数并且它本身同样是open的
    override fun click() {}

    // 若不想让子类重写你的实现，可以再显示的将重写的函数标注为final
    // final override fun click() {}
}

// 定义一个接口
interface Clickable {
    fun click()
}

// 嵌套类
class Outer {       // 外部类
    private val bar: Int = 1
    class Nested {  // 嵌套类
        fun foo() = 2
    }
}

fun testNestedClass() {
    println("> test nested class")

    val demo = Outer.Nested().foo() // 调用格式：外部类.嵌套类.嵌套类方法/属性
    println("demo is $demo")
}

fun testOuterClass() {
    println("> test outer class 2")

    val demo = Outer2().Inner().foo()
    println(demo)

    val demo2 = Outer2().Inner().innerTest()
    println(demo2)
}

class Outer2 {
    private val bar: Int = 1
    var v = "成员属性"
    inner class Inner {
        fun foo() = bar // 访问外部类成员
        fun innerTest() {
            var o = this@Outer2 // 获取外部类的成员变量
            println("内部类可以引用外部类的成员，例如: " + o.v)
            println("内部类可以引用外部类的成员，例如: " + v)
        }
    }
}

fun testAnonymousClass() {
    var testAnony = OuterAnonymous()

    // 采用对象表达式来创建接口对象，即匿名内部类的实例
    testAnony.setInterface(object : TestInterface {
        override fun test() {
            println("对象表达式创建匿名内部类的实例")
        }
    })
}

class OuterAnonymous {
    var v = "成员属性"

    fun setInterface(test: TestInterface) {
        test.test()
    }
}

interface TestInterface {
    fun test()
}

open class Person1(name: String) {
    constructor(name: String, age: Int) : this(name) {
        // 初始化
        println("---基类次级构造函数-- name: $name, age: $age")
    }
}

class Student1 : Person1 {
    constructor(name: String, age: Int, no: String, score: Int) : super(name, age) {
        println("name: $name")
        println("age: $age")
        println("no: $no")
        println("score: $score")
    }
}

open class A1 {
    open fun f() { println("A1-f") }
    fun a() { println("A1-a") }
}

interface B1 {
    val count: Int
    fun f() { println("B1-f") }
    fun b() { println("B1-b") }
}

class C1() : A1() , B1 {

    override var count: Int = 0

    override fun f() {
        super<A1>.f()
        super<B1>.f()
    }
}


// data calss User2(val name: String, val age: Int)

// fun test_data() {
//     val jack = User2(name = "jack", age = 38)
//     val oldJack = jack.copy(age = 68)

//     print("> test data class")
//     print("${oldJack.toString()}")
// }


fun test_generic(): Unit {
    println("> test generic")
    val box: Box<Int> = Box<Int>(1)
    box.let {
        println("Box value is: ${it.value}")
    }
    println("${box}")
}

class Box<T>(t: T) {
    var value:T = t // 也可以不写:T，若编译器可以自动推导
}

fun <T> doPrintln(content: T) { // 泛型函数，在fun之后，函数名之前，需要有<T>
    when (content) {
        is Int -> println("整型数字为$content")
        is String -> println("字符串转化为大写：${content.toUpperCase()}")
        else -> println("T 不是整型，也不是字符串")
    }
}


fun test_enum() {
    println("> test enum")

    var color: Color = Color.BLUE

    println(Color.values())
    println(Color.valueOf("RED"))
    println(color.name)
    println(color.ordinal)
}

enum class Color {
    RED, BLACK, BLUE, GREEN, WHITE
}

enum class Color1(val rgb: Int) {
    RED(0xFF000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

// 默认名称为枚举字符名，值从0开始，若需要指定值，则可以使用其构造函数
enum class Shaep(value: Int) {
    ovel(100),
    rectangle(200)
}

fun foo2() {
    val adHoc = object {
        var y: Int = 0
        var x: Int = 0
    }

    print(adHoc.x + adHoc.y)
}



fun testCompanion() {
    val instance = CompanionClass.create()
}

class CompanionClass {
    companion object Factory {
        fun create(): CompanionClass = CompanionClass()
    }
}

fun test_takeif() {
    println("> test take if")
    val name = "yanzhikai"
    name.indexOf("yan")
        .takeIf { value ->  // 将indexOf所获得返回值，作为takeIf的输入值，或者用it替代。若是成立走下面的逻辑
            println("it >= 0 ")
            value >= 0
        }  // 而takeIf的返回值，只能是对象本身或者null
        ?.let {
            println("testTakeIf: has yan，the value is this")
        }
    println("testTakeIf: $name")
}

// 从这里可以看到takIf的返回值是最初的对象本身或null，而takeUnless,则表示对象判断为false时，才是对象本身，否则为null。
enum class AdOpenType(s: String) {
    kOuterWeb("outer_web"),
    kApk("apk"),
    kDeepLink("deeplink")

    // value: String
    // get() = s
}

fun test_enum2() {
    println("> test enum string")

    // var type = AdOpenType.kOuterWeb



}