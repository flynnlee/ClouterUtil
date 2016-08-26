package com.clouter.clouterutil.math;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class MathUtil {
	private static Log logger = LogFactory.getLog(MathUtil.class);
	private static Random random = new Random(System.currentTimeMillis());

	public static int randomInt(int value){
		return random.nextInt(value);
	}
	public static byte randomByte(int value){
		return (byte)random.nextInt(value);
	}
	public static short randomShort(int value){
		return (short)random.nextInt(value);
	}
	public static boolean randomBoolean(){
		return random.nextBoolean();
	}

	public static <T>T random(T[] values){
		return values[random.nextInt(values.length)];
	}
	public static <T>T[] random(T[][] values){
		return values[random.nextInt(values.length)];
	}

	public static <T extends RandomWeight>T getRandomWeight(Collection<T> collection){
		List<T> rt = getRandomWeight(1, collection, false);
		if(rt == null || rt.isEmpty()) return null;
		return rt.get(0);
	}
	public static <T extends RandomWeight>List<T> getRandomWeight(int needCount, Collection<T> collection, boolean canRepeat){
		if(collection.isEmpty()){
			logger.error("random weight collection not enough.");
			return null;
		}
		List<T> rt = new ArrayList<>(needCount);

		LinkedList<T> sourceList = new LinkedList<>(collection);
		for(int i = 0; i < needCount; i++){
			int sum = 0;
			for(RandomWeight rw : sourceList){
				sum += rw.getWeight();
			}
			int rdm = random.nextInt(sum);
			int index = 0;
			T result = null;
			for(T rw : sourceList){
				if(rdm >= index && rdm < index + rw.getWeight()){
					result = rw;
					break;
				}else{
					index += rw.getWeight();
				}
			}
			if(!canRepeat){
				sourceList.remove(result);
			}
			rt.add(result);
		}
		return rt;
	}

	public static <T>T random(List<T> list){
		int index = random.nextInt(list.size());
		return list.get(index);
	}

	public static <T>List<T> random(List<T>list, int count){
		count = Math.min(count, list.size());
		List<T> rt = new LinkedList<>();
		List<T> tmpList = new LinkedList<>(list);
		for(int i = 0; i < count; i++){
			T tmp = MathUtil.random(tmpList);
			tmpList.remove(tmp);
			rt.add(tmp);
		}
		return rt;
	}

	public static <T extends PercentValue>List<T> getPercentValue(List<T>list){
		List<T> rt = new ArrayList<>();
		for(T data : list){
			if(random.nextInt(100) <= data.getPercent()){
				rt.add(data);
			}
		}
		return rt;
	}
}