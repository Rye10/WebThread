# WebThread
新建webthread类进行网络请求，数据通过handler返回主线程中然后更新UI
===

主要在在UI线程中定义静态的Handler      public static Handler handler;<br>
在onCreate初始化Hanlder并重写handleMessage方法<br>
在WebThread中获取的数据，sendMessage传递回UI  在MainActivity中  MainActivity.handler.sendMessage(msg);<br>
在webthread中无法更新UI，listview无法显示数据，只能在主线程更新<br>
<br>


static 相当于全局可调用该变量
调用方法  类名.静态变量名.方法

参考：http://blog.csdn.net/a591193965/article/details/49950915<br>
