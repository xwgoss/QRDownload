package com.xwgoss.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.xwgoss.bean.LeakInfo;

public interface ILeakCanaryService {
	public boolean saveLeakInfo(MultipartFile f,String str);
	public boolean saveLeakInfo(String str);
	public List<LeakInfo> getAllInfo(List<Map<String,String>> params);
}
