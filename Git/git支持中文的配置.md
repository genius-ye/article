# git支持中文配置

### 安装完git后，默认对中文支持是灰常有限的，为了支持中文需要做一系列配置：

1、Git Bash 中输入 ls 命令，可以正常显示中文文件名：

修改%Gi%t\etc\git-completion.bash，如果没有则在末尾添加下面一行：

	    alias ls='ls --show-control-chars --color=auto'


2、在Git Bash 中可以正常输入中文：
修改%Git%\etc\inputrc，修改配置项如下：

	    set output-meta on

	    set convert-meta off

3、Git Bash中查看日志是能正常显示中文：
修改%Git%\etc\profile，在末尾添加如下一行：

	     export LESSCHARSET=utf-8
4、在git gui 中正常显示代码中的中文：
修改%Git%\etc\gitconfig，添加如下配置项（如果代码文件是gb2312编码，就填gb2312）

        [gui]
        encoding = utf-8

5、正常显示推、拉中文修订说明

修改%Git%\etc\gitconfig，添加如下配置项

	    [i18n]
	    commitencoding = GB2312

> 说明：若没有此项存在两个问题，一、我们推到服务器的中文修订说明会变成乱码；二、我们从服务器拉下来的别人推上去的中文修订说明也会是乱码。