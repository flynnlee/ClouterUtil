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
	
	public static <T>T random(T[] values){
		return values[new Random().nextInt(values.length)];
	}
	public static <T>T[] random(T[][] values){
		return values[new Random().nextInt(values.length)];
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
		List<T> rt = new ArrayList<T>(needCount);
		
		LinkedList<T> sourceList = new LinkedList<T>(collection);
		for(int i = 0; i < needCount; i++){
			int sum = 0;
			for(RandomWeight rw : sourceList){
				sum += rw.getWeight();
			}
			int rdm = new Random().nextInt(sum);
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
		int index = new Random().nextInt(list.size());
		return list.get(index);
	}
	
	public static <T>List<T> random(List<T>list, int count){
		count = Math.min(count, list.size());
		List<T> rt = new LinkedList<>(list);
		List<T> tmpList = new LinkedList<>(list);
		for(int i = 0; i < count; i++){
			T tmp = MathUtil.random(tmpList);
			tmpList.remove(tmp);
			rt.add(tmp);
		}
		return tmpList;
	}
	
	public static <T extends PercentValue>List<T> getPercentValue(List<T>list){
		List<T> rt = new ArrayList<>();
		for(T data : list){
			if(new Random().nextInt(100) >= data.getPercent()){
				rt.add(data);
			}
		}
		return rt;
	}
}