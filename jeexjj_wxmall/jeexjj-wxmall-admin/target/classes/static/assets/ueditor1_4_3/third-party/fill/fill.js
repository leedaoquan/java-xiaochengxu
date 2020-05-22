///import core
///commands 填空
///commandsName  Fill
///commandsTitle  填空
/**
 * 填空题挖空
 * @function
 * @name baidu.editor.execCommands
*/
UE.commands['fill'] = {
	execCommand : function() {
		this.selection.getRange().select();
		var txt = this.selection.getText();
		if(txt){
			var tmpElement = '<span style="border: 1px solid;-webkit-border-radius: 12px;">'+txt+'</span>';
			this.execCommand('inserthtml',tmpElement);
		}
		return true;
	},
	queryCommandState : function(){
		return this.highlight ? -1 : 0;
	}
};