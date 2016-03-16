# 不使用SDK实现微信分享（支持多图分享）

> 最近在项目中要用到微信分享，还要支持多图分享，官方给的sdk却只支持单图，所以就想通过其他途径实现，然后就在某论坛看到了这个，经测试确实好用，妈妈再也不用担心我使用微信分享了。

### 主要代码：

```java
    /**
     * 分享图片给好友
     *
     * @param file
     */
    private void shareToFriend(File file) {

        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
        intent.setComponent(comp);
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        startActivity(intent);
    }




    /**
     * 分享多图到朋友圈,多张图片加文字
     *
     * @param uris
     */
    private void shareToTimeLine(String title, ArrayList<Uri> uris) {
        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(comp);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");

        intent.putExtra("Kdescription", title);

        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        startActivity(intent);
    }
```

***

> 效果和官方的发送朋友圈一样……