package com.clouter.clouterutil.reviver;

/**
 * 数值恢复封装
 * @author flynn
 *
 */
public class ValueFloatTimeReviver {
	/**上次恢复的数值*/
	private float lastValue;
	/**上次恢复的时间*/
	private long lastReceiveStamp;
	/**最大值*/
	private float maxValue;
	/**增长CD(毫秒)*/
	private long growthCd;

	public ValueFloatTimeReviver(float value, long lastReceiveStamp, float maxValue, long growthCd){
		this.lastValue = value;
		this.lastReceiveStamp = lastReceiveStamp;
		this.maxValue = maxValue;
		this.growthCd = growthCd;
	}

	/**
	 * 更新一次数值
	 * @return - 本次更新数值的变化值
	 */
	public float updateValue(){
		if(lastValue >= maxValue){
			return 0;
		}

		float preValue = lastValue;
		long currentMillis = getCurrentMillis();
		if(lastReceiveStamp > currentMillis){
			lastReceiveStamp = currentMillis;
		}
		long diff = currentMillis - lastReceiveStamp;
		float addValue = (float)diff / (float)growthCd;
		lastReceiveStamp += diff;

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
	public void adjValue(float value){
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
	public float getValue(){
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
