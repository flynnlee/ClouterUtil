package com.clouter.clouterutil.json;

/**
 * Created by flynn on 16/9/19.
 */
public class JsonFormater {
	private String tabStr = "\t";
	private String json;
	private String newLineStr = "\n";

	public String getTabStr() {
		return tabStr;
	}
	public void setTabStr(String tabStr) {
		this.tabStr = tabStr;
	}
	public String getJson() {
		return json;
	}

	public String getNewLineStr() {
		return newLineStr;
	}

	public void setNewLineStr(String newLineStr) {
		this.newLineStr = newLineStr;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public JsonFormater(String json, String tabStr, String newLineStr){
		this(json);
		this.tabStr = tabStr;
		this.newLineStr = newLineStr;
	}
	public JsonFormater(String json) {
		this.json = json;
	}

	public String format(){
		StringBuilder builder = new StringBuilder();

		int tabIndex = 0;
		boolean valueSign = false;
		boolean specialSign = false;
		for(char c : json.toCharArray()){
			if(c == '}'){
				builder.append(newLineStr);
				writeTab(tabIndex - 1, builder);
			}
			builder.append(c);
			if(valueSign){
				if(!specialSign){
					if(c == '"'){
						valueSign = false;
					}else if(c == '\\') {
						specialSign = true;
					}
				}else{
					specialSign = false;
				}
			}else{
				if(c == '{'){
					tabIndex++;
					builder.append(newLineStr);
					writeTab(tabIndex, builder);
				}else if(c == '}'){
					tabIndex--;
				}else if(c == ','){
					builder.append(newLineStr);
					writeTab(tabIndex, builder);
				}else if(c == '"'){
					valueSign = true;
				}
			}
		}
		return builder.toString();
	}

	private void writeTab(int tabIndex, StringBuilder builder){
		for(int i = 0; i < tabIndex; i++){
			builder.append(tabStr);
		}
	}
}
