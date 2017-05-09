package com.xwgoss.util;

import java.util.ArrayList;
import java.util.List;

import com.xwgoss.bean.LeakInfo;

public class CacheUtil {
	private List<LeakInfo> l;
	private static CacheUtil cu;
	private CacheUtil(){
		
	}
	public synchronized static CacheUtil getInstance(){
		if(cu==null)
			cu=new CacheUtil();
		return cu;
	}
	public void putLeakInfo(LeakInfo info){
		if(l==null)
			l=new ArrayList<LeakInfo>();
		l.add(info);
	}
	public List<LeakInfo> getAllLeakInfo(){
		return l;
	}

}
