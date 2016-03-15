#　Android状态栏一体化（沉浸式状态栏）

>  在安卓5.0以上应用开发的时候，经常会发现别的应用状态栏和应用的颜色是一样的，看起来非常的和谐，因此被码农们称为沉浸式状态栏，也叫状态栏一体化，其实实现起赖也不是很难，只需几行代码就可以搞定。而且还可以在不同的页面为状态栏设置不同的颜色，使整个应用看起来都非常的和谐。

#### 布局设置

```xml
    <RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/background_grey"
    android:fitsSystemWindows="true"
    android:clipToPadding="true"
    >
```

>  其中`android:background="@color/background_grey"`为主布局背景的颜色，状态栏的颜色也会和这保持一致，`android:fitsSystemWindows="true"`这句话表示让布局适应系统窗口，`android:clipToPadding="true"`表示状态栏和应用之间有间距，不然应用会和状态栏重合。

#### 代码设置

```java 
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
```

