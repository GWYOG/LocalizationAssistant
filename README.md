# LocalizationAssitant

It doesn't support i18n at this moment, so the following README is written in Chinese.

### What's this?

Localization Assitant，简称LA，是个用java编写的小型软件，为了使本地化Minecraft Mod更加便利而诞生.

### Why it's better than scripts?
* 由于LA是用java写的，所以基本上来说只要有装JRE，就能使用这个软件，而这对于MC玩家来说应该不成问题.  
* 对于.lang文件的选择和参数的配置，用户都可以在LA的GUI中便捷地设定.
* LA将会集成各种方便的功能于一身，以减少用户使用多个脚本文件时的不便.

### What can it do?
就目前来说，它实现了六个功能：

* **条件替换：**智能地替换.lang文件中的内容.它只会在本地化内容中完成替换(即"="后面的内容)，而且可以自定义替换的行数，也可以设定为对于一些字符中间的内容不进行替换(比如可以设置成不替换双引号中的内容)
* **等距添加字符串：**对于某些mod，比如Agricraft或是Psi，在本地化它们的手册时，需要每隔几个字符(比如10个)就手动打入一个空格来进行换行.而这个功能可以自动实现这个添加字符(串)的过程.同样地，用户可以设置操作的行数.
* **可用文本更新：**在mod依赖的MC版本大升级后，你是不是为了巨量的待更新的.lang条目而发愁呢？有了这个功能你就不用再担心了. 它可以将旧版zh_CN.lang中可用的条目自动替换掉新版的en_US.lang中的内容.其中，“可用的”指的是它们(旧版文本和新版文本)有相同的"="前的内容，或是有相同的"="后的英文名字，你可以自定义选择使用这两个更新方法中的某一个，或是一次性交替使用它俩，或是同时匹配这两个内容. 值得一提的是，无论是使用哪种更新方式，LA在对比时都将忽略大小写.  
这个功能还可以选择额外模式，目前一共可以有两个模式进行选择。若开启校验模式，如果选择的更新方式是对比=后的英文文本，那么当出现旧版zh_CN.lang中同一个英文文本对应多个不同的中文翻译时，在最后生成的更新后的文本对应条目前将会出现"##Warning:"来提示用户此条目译名可能不准确，以便用户检验. 若开启显示更新信息模式，那么此功能将实现[crafteverywhere](https://github.com/crafteverywhere)用python写的[文本更新脚本](https://github.com/crafteverywhere/Craft_Minecraft_Mod_Localization/blob/master/lang_checker.py)的功能，不过此时注记的符号稍有改变：新增条目为"##N:",旧版文本为"##-:"，新版文本为"##+:",旧版参考译文为"##R:"
* **逐行条件删除：**此功能可以逐行检索是否存在某个用户指定的字符串，如果发现某行存在该字符串，LA将把此行该字符串及其之后的所有内容删除. 用户也可以选择在删除时保留该字符串.   
特别地，自v1.4版本用户还可以使用此功能直接快速删除"可用文本更新"功能中额外模式显示的信息  
* **已译词条替换：**此功能要在两个输入模式中选择一个。第一个模式可以帮助你将你之前翻译好的部分文本替换你还未翻译的部分，比如你之前翻译好了xxx.xx.orecopper.name=铜矿，那之后所有文本中的Copper Ore（或开头小写）将被替换为铜矿。另一个模式就更加自定义了，你通过编写一个每行形如"英文=中文"的utf-8格式文件来告诉LA你想替换的内容，LA则会快速帮你替换掉它们。比如你可以编写一个快速替换16种颜色的文件。   
* **高频词汇统计：**通过输入一个en_US.lang和输入一个可以不填的词汇过滤器，LA将统计该文件中所有单词出现的频率并由高到低输出到指定的文件中。


### Downloads

您可以在此仓库的[releases页面](https://github.com/GWYOG/LocalizationAssitant/releases)中找到所有可用版本LA的下载链接. 这个页面将提供LA所有主要版本(即形如x.x的版本)的下载链接，而LA的次要版本(即形如x.x.x的版本)将不被包含在内.

### Things you need to pay attention to

* 由于此软件是针对Minecraft Mod的本地化而开发的，故默认您操作的文件是.lang文件，因此它们是UTF-8(无BOM)编码的. 如果您的文件不是该编码，则很可能会出现乱码现象.

### Suggestions

如果您有好的建议，比如对于现有功能的改进或是对于新功能的构思，欢迎在[issues](https://github.com/GWYOG/LocalizationAssitant/issues)页面中向我提出^^

### Issue reporting

使用软件遇上bug是常有的事，请及时向我反馈，以便及时修好它.  
您可以在此仓库的[issues](https://github.com/GWYOG/LocalizationAssitant/issues)页面中向我反馈，如果能使用英文那就更好了:)  
我将对您的反馈表示万分的感谢！

### License
此项目使用的证书是[GNU General Public License v3.0](https://github.com/GWYOG/LocalizationAssitant/blob/master/LICENSE)，欢迎贡献你的代码！
