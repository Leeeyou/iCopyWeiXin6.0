CopyWeiXin5.4
=============

仿微信5.4(UI交互)

修改

/*****************************************************************************************************/

修改主界面的图标为变色效果，用到的知识点如下：

一：自定义属性的运用（TypeArray）

  在使用自定义属性的时候，碰到的 Error parsing XML: unbound prefix的问题，解决办法：http://stackoverflow.com/questions/2221221/frequent-problem-in-android-view-error-parsing-xml-unbound-prefix.

二：利用paint的xfermode设置遮罩层为DST_IN模式

  关于xfermode,实际为设置两张图片想交时的模式。在正常情况下，在已有图像上绘图将会在其上面添加一层新的形状。如果新的paint是完全不透明的，则将会遮挡下面的paint.
  
  通常可以简单的理解如下：
  
  canvas原有的图片可理解为背景-->dst
  
  新画上去的图片可理解为前景-->src
  

三：变色原理则是绘制颜色的时候设置alpha值

参考：
http://blog.csdn.net/lmj623565791/article/details/41087219 

以及 

http://blog.csdn.net/lmj623565791/article/details/24555655

/*****************************************************************************************************/
