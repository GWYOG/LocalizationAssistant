# LocalizationAssitant

It doesn't support i18n at this moment, so the following README is written in Chinese.

### What's this?

Localization Assitant，简称LA，是一个用java写的小型软件，为了使本地化minecraft mod更加方便.
由于是用java写的，所以基本上只要有装JRE，就可以使用这个软件，而这对于mc玩家来说应该不是个问题.
文件的选择和其他参数的配置都可以在LA的GUI中快捷地选择，用起来十分地方便.

### What can it do?
就目前来说，它实现了三个功能：
* 条件替换：智能的替换.lang文件中的内容.它只会在本地化内容中完成替换(即"="后面的内容)，而且可以自定义替换的行数，也可以设定为对于一些字符中间的内容不进行替换(比如可以设置成不替换双引号中的内容)
* 等距添加字符串：有些mod，比如Agricraft或是Psi，在本地化它们的手册时，需要每隔几个字符(比如10个)就手动打入一个空格来进行换行.而这个功能可以自动实现这个手动加空格的过程.同样地，它可以自定义选择操作的行数.
* 可用文本更新：在mod依赖的mc版本大升级后，你是不是为了巨量的未更新的.lang条目而发愁呢？有了这个功能你就不用再担心了.它可以将旧版zh_CN.lang中可用的条目自动替换掉新版的en_US.lang中的内容. 在这里，“可用的”指的是它们有相同的"="前的内容，或是有相同的"="后的英文名字，你可以自定义选择使用这两个更新方法中的某一个，或是一次性交替使用它们俩. 当使用后者时，对比英文名字时将忽略大小写.

### Issue reporting

使用软件遇上bug是很常有的事，请及时向我反映，以便快速修好它. 
您可以在此仓库的[issues](https://github.com/GWYOG/LocalizationAssitant/issues)页面中向我反馈，如果能使用英文那就更好了:)
我将对您的反馈表示万分的感谢！

### Licenses
此项目使用的证书是[GNU General Public License v3.0](https://github.com/GWYOG/LocalizationAssitant/blob/master/LICENSE)，欢迎贡献你的代码！
