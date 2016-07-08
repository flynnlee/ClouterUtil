package com.clouter.clouterutil.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil {
	private static final Log log = LogFactory.getLog(FileUtil.class);
	
	public static List<String> readLines(String filePath){
		BufferedReader br = null;
		try {
			List<String> rt = new ArrayList<String>();
			File file = new File(filePath);
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = null;
			while((line = br.readLine()) != null){
				rt.add(line);
			}
	
			return rt;
		} catch (FileNotFoundException e) {
			log.error("file is not exist:" + filePath, e);
		} catch (IOException e) {
			log.error("read file fail:" + filePath, e);
		} finally{
			try {
				br.close();
			} catch (IOException e) {
				log.error("close BufferedReader fail.", e);
			}
		}
		return null;
	}
	
	public static void writeToFile(String filePath, String content){
		BufferedWriter bw = null;
		try {
			File file = new File(filePath);
			if(file.exists()){
				file.delete();
			}else{
				if(!file.getParentFile().exists()){
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
		} catch (IOException e) {
			log.error("write file fail:" + filePath, e);
		} finally{
			try {
				bw.close();
			} catch (IOException e) {
				log.error("close BufferedWriter fail.", e);
			}
		}
	}
	
	public static void writeLines(String filePath, List<String> lines){
		StringBuilder builder = new StringBuilder();
		for(String line : lines){
			builder.append(line).append("\n");
		}
		writeToFile(filePath, builder.toString());
	}
	
	public static void saveFileContent(FileContent content){
		writeToFile(content.getFilePath() + "/" + content.getFileName(), content.getContent());
	}
	
	/**
	 * 根据项目内文件相对路径获得绝对路径
	 * @param relativePath
	 * @return - 文件不存在则返回null
	 */
	public static String getAbsolutePathInProeject(String relativePath){
		URL url = new FileUtil().getClass().getClassLoader().getResource(relativePath);
		return url == null ? null : url.getPath();
	}
}
