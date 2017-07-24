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
		}else{
			map.put(key, count);
		}
	}

	private Map.Entry<K, Integer> getMaxCountEntry(){
		Map.Entry<K, Integer> rt = null;
		for(Map.Entry<K, Integer> entry : map.entrySet()){
			if(rt == null || rt.getValue() < entry.getValue()){
				rt = entry;
			}
		}
		return rt;
	}

	public K getMaxCountKey(){
		Map.Entry<K, Integer> entry = getMaxCountEntry();
		return entry == null ? null : entry.getKey();
	}
	public int getMaxCount(){
		Map.Entry<K, Integer> entry = getMaxCountEntry();
		return entry == null ? null : entry.getValue();
	}
}
