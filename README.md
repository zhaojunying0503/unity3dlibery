# unity3dlibery
unity 3d 模型展示
效果如图 :


![](https://github.com/zhaojunying0503/PictureBed/blob/master/unity_3d_mode.gif)


modle 文件夹下的模型文件,需要放在sdcard下

引用添加： 
  
      implementation 'com.zhaojunying:unity3dlibrary:1.0.0'


使用说明：


        //创建view
        mUnity3DView = new Unity3DView(this);
        //初始化手势
        mUnity3DView.initScaleGestureDetector(this);
        //设置view
        setContentView(mUnity3DView);

方法说明：

  
        setModlePath(String path) // 设置modle的路径
        
        setSdcardUnderModlePath(String path) // 设置sdcard下的路径





