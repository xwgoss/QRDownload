package com.xwgoss.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xwgoss.bean.LeakInfo;
import com.xwgoss.service.ILeakCanaryService;

@Controller
@RequestMapping("/lc")
public class LeakCanaryInfoControl {
	@Autowired
	private ILeakCanaryService leakCanaryServiceImp;
	
	@RequestMapping(value="/infofile",method=RequestMethod.POST)
	public void getinfo(@RequestParam("file")MultipartFile file,HttpServletRequest req,HttpServletResponse rsp){
			String str=req.getParameter("info");
			boolean flag=leakCanaryServiceImp.saveLeakInfo(file, req.getParameter("leakinfo"));
			if(flag){
				rsp.setStatus(200);
			}else{
				rsp.setStatus(500);
			}
				
	}	
	@RequestMapping(value="/info")
	public void getinfo(HttpServletRequest req,HttpServletResponse rsp){
			String str=req.getParameter("leakinfo");
			leakCanaryServiceImp.saveLeakInfo(str);
			rsp.setStatus(200);
	}
	@RequestMapping(value="/allInfo",method=RequestMethod.GET)
	public String getAllInfo(HttpServletRequest req){
		List<LeakInfo> leakInfos=leakCanaryServiceImp.getAllInfo(null);
		if(leakInfos.size()>0)
		{
			req.setAttribute("apk_packagename", leakInfos.get(0).getApk_packagename());
			req.setAttribute("apk_version", leakInfos.get(0).getApk_version());
		}
		req.setAttribute("infos",leakInfos);
		return "allInfo";
	}
}
