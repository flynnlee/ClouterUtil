package com.clouter.clouterutil.velocity;

import com.clouter.clouterutil.file.FileContent;

/**
 * 使用Velocity生成文本的所需的封装类
 * @author flynn
 *
 */
public class ConverterParam {
	/**输入velocity的数据集合*/
	private Object inputData;
	/**生成的文件内容*/
	private FileContent fileContent;
	/**模板路径*/
	private String vmFile;
	/**输出文件的编码*/
	private String encodeStr;

	public ConverterParam(){
		fileContent = new FileContent();
	}

	public Object getInputData() {
		return inputData;
	}

	public void setInputData(Object inputData) {
		this.inputData = inputData;
	}

	public String getVmFile() {
		return vmFile;
	}

	public void setVmFile(String vmFile) {
		this.vmFile = vmFile;
	}

	public String getEncodeStr() {
		return encodeStr;
	}

	public void setEncodeStr(String encodeStr) {
		this.encodeStr = encodeStr;
	}

	public FileContent getFileContent() {
		return fileContent;
	}
}
