package com.clouter.clouterutil.receiver;

/**
 * 数值恢复封装
 * @author flynn
 *
 */
public class ValueTimeReceiver {
	/**上次恢复的数值*/
	private int lastValue;
	/**上次恢复的时间*/
	private long lastReceiveStamp;
	/**最大值*/
	private int maxValue;
	/**增长CD(毫秒)*/
	private long growthCd;
	
	public ValueTimeReceiver(int value, long lastReceiveStamp, int maxValue, int growthCd){
		this.lastValue = value;
		this.lastReceiveStamp = lastReceiveStamp;
		this.maxValue = maxValue;
		this.growthCd = growthCd;
	}
	
	/**
	 * 更新一次数值
	 */
	public void updateValue(){
		if(lastValue >= maxValue){
			return;
		}
		
		long currentMillis = getCurrentMillis();
		if(lastReceiveStamp > currentMillis){
			lastReceiveStamp = currentMillis;
		}
		int addValue = (int)((currentMillis - lastReceiveStamp) / growthCd);
		lastReceiveStamp += addValue * growthCd;
		
		lastValue += addValue;
		if(lastValue >= maxValue){
			lastReceiveStamp = currentMillis;
			lastValue = maxValue;
		}
	}
	
	/**
	 * 增减数值
	 * @param value
	 */
	public void adjValue(int value){
		if(value == 0) return;
		updateValue();
		this.lastValue += value;
	}
	
	/**
	 * 距离下次恢复剩余毫秒数(若已达到最大值则为0)
	 * @return
	 */
	public long getNextReceiveCd(){
		updateValue();
		if(lastValue >= maxValue) return 0;
		return lastReceiveStamp + growthCd - getCurrentMillis();
	}
	
	/**
	 * 获取当前数值
	 * @return
	 */
	public int getValue(){
		updateValue();
		return lastValue;
	}
	
	public long getLastReceiveStamp() {
		return lastReceiveStamp;
	}

	/**
	 * 获取当前时间戳
	 * @return
	 */
	protected long getCurrentMillis(){
		return System.currentTimeMillis();
	}
}
