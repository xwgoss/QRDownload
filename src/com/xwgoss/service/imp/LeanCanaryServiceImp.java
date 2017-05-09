package com.xwgoss.service.imp;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.xwgoss.bean.LeakInfo;
import com.xwgoss.dao.LeakInfoDao;
import com.xwgoss.service.ILeakCanaryService;
import com.xwgoss.util.StringUtil;

public class LeanCanaryServiceImp implements ILeakCanaryService {
	private String humppath;
	private String descfile=".desc";
	@Autowired
	private LeakInfoDao leakInfoDao;
	@Override
	public boolean saveLeakInfo(MultipartFile f, String str) {
		// TODO Auto-generated method stub
		str=str.replaceAll("\\\\n", "<br>");
		LeakInfo lf=JSON.parseObject(str, LeakInfo.class);
		lf.setId(StringUtil.getId());
		lf.setLeakinfo(lf.getLeakinfo().split("Retaining")[0]);
		if(f!=null){
		}
		return leakInfoDao.insertLeakInfo(lf);
	}
	public boolean saveLeakInfo(String str){
		return this.saveLeakInfo(null, str);
	}
	@Override
	public List<LeakInfo> getAllInfo(List<Map<String,String>> params) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		if(params==null)
			sb.append("order by create_time desc");
		return leakInfoDao.getLeakInfos(sb.toString());
	}
	
	public String getHumppath() {
		return humppath;
	}
	public void setHumppath(String humppath) {
		this.humppath = humppath;
	}
}
