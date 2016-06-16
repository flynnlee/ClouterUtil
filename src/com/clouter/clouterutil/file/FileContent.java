package com.clouter.clouterutil.file;

/**
 * 一个文本文件的信息
 * @author flynn
 *
 */
public class FileContent {
	/**文件名*/
	private String fileName;
	/**文件绝对路径*/
	private String filePath;
	/**文件内容*/
	private String content;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("fileName:").append(fileName).append("\tfilePath:").append(filePath).append("\tcontent:").append(content);
		return builder.toString();
	}
}
