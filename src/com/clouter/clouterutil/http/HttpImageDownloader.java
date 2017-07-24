package com.clouter.clouterutil.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by flynn on 2016/9/4 0004.
 */
public class HttpImageDownloader {
	private static final Log log = LogFactory.getLog(HttpImageDownloader.class);
	private String imageUrl;
	private String saveDir;

	private int bufferSize = 4096;

	public boolean download(){
		HttpURLConnection httpConnection = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		log.info("download image:" + imageUrl + " to " + saveDir);
		try{
			URL url = new URL(imageUrl);
			httpConnection = (HttpURLConnection)url.openConnection();
			dis = new DataInputStream(httpConnection.getInputStream());
			String imageName = HttpUtil.getLastUrl(imageUrl, 1);
			File file = new File(saveDir);
			if(!file.exists() || file.isDirectory()){
				file.mkdirs();
			}
			dos = new DataOutputStream(new FileOutputStream(saveDir + "/" + imageName));
			byte[] buffer = new byte[bufferSize];
			int count;
			while((count = dis.read(buffer)) > 0) {
				dos.write(buffer, 0, count);
			}
			return true;
		}catch(Exception e){
			log.error("download image:" + imageUrl + " fail", e);
		}finally{
			try{
				if(httpConnection != null){
					httpConnection.disconnect();
				}
				if(dis != null){
					dis.close();
				}
				if(dos != null){
					dos.close();
				}
			}catch(IOException e){
				log.error("download image " + imageUrl + " close fail.", e);
			}
		}
		return false;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public String getSaveDir() {
		return saveDir;
	}
	public int getBufferSize() {
		return bufferSize;
	}
	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}
}
