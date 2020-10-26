# NestedScrollWebView
基于NestedScrollView改写的WebView 处理CoordinatorLayout关联滑动问题，增加了都没有fling效果

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
## Usage
In your layout.xml include the following:
```java
<com.example.nestedscrollwebview.NestedScrollWebView
        android:id="@+id/web"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"/>
```

