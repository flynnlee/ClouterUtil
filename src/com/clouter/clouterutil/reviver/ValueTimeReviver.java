package com.clouter.clouterutil.reviver;

/**
 * 数值恢复封装
 * @author flynn
 *
 */
public class ValueTimeReviver {
	/**上次恢复的数值*/
	private int lastValue;
	/**上次恢复的时间*/
	private long lastReceiveStamp;
	/**最大值*/
	private int maxValue;
	/**增长CD(毫秒)*/
	private long growthCd;

	public ValueTimeReviver(int value, long lastReceiveStamp, int maxValue, int growthCd){
		this.lastValue = value;
		this.lastReceiveStamp = lastReceiveStamp;
		this.maxValue = maxValue;
		this.growthCd = growthCd;
	}

	/**
	 * 更新一次数值
	 */
	public int updateValue(){
		long currentMillis = getCurrentMillis();
		if(lastValue >= maxValue){
			lastReceiveStamp = getCurrentMillis();
			return 0;
		}

		int preValue = lastValue;
		if(lastReceiveStamp > currentMillis){
			lastReceiveStamp = currentMillis;
		}
		int addValue = growthCd == 0 ? 0 : (int)((currentMillis - lastReceiveStamp) / growthCd);
		lastReceiveStamp += addValue * growthCd;

		lastValue += addValue;
		if(lastValue >= maxValue){
			lastReceiveStamp = currentMillis;
			lastValue = maxValue;
		}
		return lastValue - preValue;
	}

	/**
	 * 增减数值
	 * @param value - 对数值的变化幅度
	 */
	public void adjValue(int value){
		if(value == 0) return;
		updateValue();
		this.lastValue += value;
	}

	/**
	 * 距离下次恢复剩余毫秒数
	 * @return - 若已达到最大值则为0
	 */
	public long getNextReceiveCd(){
		updateValue();
		if(lastValue >= maxValue) return 0;
		return lastReceiveStamp + growthCd - getCurrentMillis();
	}

	/**
	 * 获取当前数值
	 * @return - 获取最新数值
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
	 * @return - 获取当前时间戳
	 */
	protected long getCurrentMillis(){
		return System.currentTimeMillis();
	}
}
