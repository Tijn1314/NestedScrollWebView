# NestedScrollWebView(日活千万验证)
基于NestedScrollView改写的WebView 处理CoordinatorLayout关联滑动问题
网络上的都没有fling效果 所以改写了一下NestedScrollView

<img src="https://github.com/Tijn1314/NestedScrollWebView/blob/master/pic/device.png" alt="show" />

```java
allprojects {
    repositories {
        ...
        maven { url 'https://www.jitpack.io' }
    }
}
dependencies {
    implementation 'com.github.Tijn1314:NestedScrollWebView:1.0'
}
```

