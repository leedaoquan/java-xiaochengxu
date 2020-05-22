;
(function($, window) {
	var XjjUtil = {};
	if (!window.XjjUtil) {
		window.XjjUtil = XjjUtil;
	}
	
	//获取路径
	var pathName = window.document.location.pathname;
	//截取，得到项目名称
	var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	
	/**
	 * 选择图片组件
	 */
	XjjUtil.chooseImg=function(options){
		
		//是否多选
		var multi = true;
		//回调id标识
		var cbId = null;
		var width=1000;
		var height=700;
		var title = "选择图片";
		if(null!=options)
		{
			if(null!=options.multi)
			{
				multi = options.multi;
			}
			if(null!=options.cbId)
			{
				cbId = options.cbId;
			}
			if(null!=options.width)
			{
				width = options.width;
			}
			if(null!=options.height)
			{
				height = options.height;
			}
			if(null!=options.title)
			{
				title = options.title;
			}
		}
		
		//window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
		//获得窗口的垂直位置;
		var iTop = (window.screen.height-30-height)/2;       
		var iLeft = (window.screen.width-10-width)/2; 
		var windowOptions='height='+height+',innerHeight='+height+',width='+width+',innerWidth='+width+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no';
		var url = projectName+"/sys/image/info/select?multi="+multi;
		if(null!=cbId)
		{
			url+="&cbId="+cbId;
		}
		
	    window.open(url,title,windowOptions);
	}
	
	//删除节点 deep：深度，例2，就是删除爷爷节点
	XjjUtil.removeParent=function(that,deep){
		var tempNode  = $(that);
		for(i=0;i<deep;i++)
		{
			tempNode = tempNode.parent();
		}
		tempNode.remove();
	}
	
	//获得项目路径
	XjjUtil.getRootPath_web=function(){
        //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
        var curWwwPath = window.document.location.href;
        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8083
        var localhostPaht = curWwwPath.substring(0, pos);
        //获取带"/"的项目名，如：/uimcardprj
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        return (localhostPaht + projectName);
    }
	
})(jQuery, window);
