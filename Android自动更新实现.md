# Android 自动更新实现

> 在没有服务器的情况下实现Android的自动更新，需要用到的准备有，github免费的空间，网盘或者是免费的app托管服务器，如:fir.im或者是蒲公英等……

### 1.配置文件：

> 先准备一个json格式的配置文件，至于为什么是json格式的，是因为后期换成真实的服务器的时候不用过多的去改代码。内容如下：

```json
    {
        "appName": "VIM",
        "versionName": "0.1",
        "versionCode":"1",
        "url": "http://pkg3.fir.im/af43fca273c96f8c629e97510e44dc5250beb5fe.apk?attname=app-release.apk_0.2.apk"
    }
```

> 将这个配置文件放到github服务器上，然后获取该文件的url，如：https://raw.githubusercontent.com/genius-ye/genius-ye.github.io/master/update.json

### Android端代码

* model:

```java
/**
 * Created by genius-ye on 2016/4/22.
 */
public class UpdateInfo {
    /** 应用名 **/
    private String appName ;
    /** 版本号 **/
    private String versionName;
    private String versionCode;
    /** 下载地址 **/
    private String url;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
```

* 下载文件的工具类（用到了okhttp库，支持进度显示）

```java
     /**
     * 文件下载
     *
     * @param url      ：文件的url
     * @param filePath ：文件保存的路径，加文件后缀
     */
    public void downloadFile(String url, final String filePath , final RequsetCallBack requsetCallBack) {

        final ProgressDialog pBar = new ProgressDialog(context);    //进度条，在下载的时候实时更新进度，提高用户友好度
        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pBar.setTitle("正在下载");
        pBar.setMessage("请稍候...");
        pBar.setProgress(0);
        pBar.show();

        OkHttpClient okHttpClient = new OkHttpClient();
        Request downloadRequest = new Request.Builder().url(url).build();
        okHttpClient.newCall(downloadRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                requsetCallBack.onFailure();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                File file = new File(filePath);
                int length = (int) response.body().contentLength();
                pBar.setMax(length);
                InputStream inputStream = response.body().byteStream();
                byte[] buf = new byte[1024];
                int process = 0;
                int ch = -1;
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                while ((ch = inputStream.read(buf)) != -1) {
                    fileOutputStream.write(buf, 0, ch);
                    process += ch;
                    pBar.setProgress(process);       //这里就是关键的实时更新进度了！
                }
                fileOutputStream.close();
                Logger.d("文件下载完成……");
                pBar.cancel();
                requsetCallBack.onSuccess(null);
            }
        });
    }
```

* 请求配置文件：

```java
    final OkHttpClient client = new OkHttpClient();
                final Request request = new Request.Builder().url(Infos.URL_UPDATE).build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        if (!response.isSuccessful())
                            throw new IOException("Unexpected code " + response);

                        UpdateInfo updateInfo = JSONObject.parseObject(response.body().string(), UpdateInfo.class);
                        Message message = new Message();
                        message.what = REQUEST_CODE_DOWNLOADFILE;
                        message.obj = updateInfo;
                        handler.sendMessage(message);
                    }
                });
```

* 下载更新：

```java
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REQUEST_CODE_DOWNLOADFILE:
                    updateInfo = (UpdateInfo) msg.obj;
                    String versionName = AndroidTools.getVersionName(SetActivity.this);
                    final String fileName = App.PATH + File.separator + updateInfo.getAppName() + ".apk";
                    if (!versionName.equals(updateInfo.getVersionName())) {

                        new AlertDialog.Builder(SetActivity.this).setTitle("更新").setMessage("发现新版本："+updateInfo.getVersionName()).setNegativeButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                httpTools.downloadFile(updateInfo.getUrl(), fileName, new HttpTools.RequsetCallBack() {
                                    @Override
                                    public void onFailure() {

                                    }

                                    @Override
                                    public void onSuccess(JSONObject jsonObject) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
                                        startActivity(intent);
                                    }
                                });
                            }
                        }).show();

                    }
                    break;
            }
        }
    };
```

