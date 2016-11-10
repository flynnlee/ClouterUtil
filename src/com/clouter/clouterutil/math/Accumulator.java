package com.clouter.clouterutil.math;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * Created by flynn on 2016/11/10.
 */
public class Accumulator<K> {
	private Map<K, Integer> map = new HashedMap();
	public int getCount(K key){
		return map.containsKey(key) ? map.get(key) : 0;
	}
	public void adjCount(K key, int count){
		if(map.containsKey(key)){
			map.put(key, map.get(key) + count);
		}
	}
}
