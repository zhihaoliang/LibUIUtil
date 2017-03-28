1.这是一个对一些UI操作的的整合包括的方法有：<br>
<1>com.zhihaoliang.util.ui.UIUtil
<pre><code>
/**
 \* 测量View的高度
 */
public static int measureViewHeight(View paramView)
/**
 \* 测量View的宽度
 */
public static int measureViewWidth(View paramView)
/**
 \* 显示Toast
 */
public static void showToast(Context context, String text)
/**
 \* 显示Toast
 */
public static void showToast(Context context, int textId) 
/**
 \* 设置文本的格式
 */
public static CharSequence getStyledString(Context context ,String[] text ,int[] styles ) 

</pre></code>
<2>  com.zhihaoliang.util.ui.Log
<pre><code>
/**
 \* 打印对象
 */
public static void log(Object... args)
/**
 \* 打印对象
 */
public static void logObject(Object object) 
/**
 \* 打印对象到同一行
 */
public static void logObjectLine(Object object)
/**
 \* 打印对象到sdcard
 */
public static void LogFile(String scanResult)

</pre></code>

2.工程的导入在工程中的build.gradle
<pre><code>
allprojects {

repositories {
    jcenter()
    maven {
        url "https://raw.githubusercontent.com/zhihaoliang/LibOkHttpUtil/master/libokhttp/repository"
    }
}

}
</pre></code>
在app中的build.gradle 加入<br>
 compile 'com.zhihaoliang.util.ui:libui:1.2'