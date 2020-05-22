///import core
///commands 填空
///commandsName  Circle
///commandsTitle  填空
/**
 * 加圈
 * @function
 * @name baidu.editor.execCommands
*/
UE.commands['circle'] = {
	execCommand : function() {
		
		var rng = this.selection.getRange(),
        parentSpan = UE.dom.domUtils.findParentByTagName(rng.startContainer,"span",true);
		var size = "18px";
	    if (parentSpan) {
	    	var style = parentSpan.getAttribute("style");
	    	if(style.indexOf("font-size") >= -1){
	    		var fontSize = parentSpan.style.getPropertyValue("font-size");
	    		switch (fontSize) {
		    		case '10px' :size="10px";break;
	                case '11px' :size="11px";break;
	                case '12px' :size="12px";break;
	                case '14px' :size="16px";break;
	                case '16px' :size="18px";break;
	                case '18px' :size="20px";break;
	                case '20px' :size="22px";break;
	                case '24px' :size="28px";break;
	                case '36px' :size="40px";break;
	                default :;
	    		}
	    	}
	    }
    
		rng.select();
		var txt = this.selection.getText();
		if(txt){
			var tmpElement = '<span style="display:inline-block;width:'+size+';height:'+size+';line-height:'+size+';text-align:center;border: 1px solid;border-radius: 50%;">'+txt+'</span>';
			this.execCommand('inserthtml',tmpElement);
		}
		return true;
	},
	queryCommandState : function(){
		return this.highlight ? -1 : 0;
	}
};