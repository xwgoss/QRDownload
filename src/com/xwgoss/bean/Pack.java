package com.xwgoss.bean;

import java.io.File;

public class Pack {
	private String project;
	private String build_num;
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getBuild_num() {
		return build_num;
	}
	public void setBuild_num(String build_num) {
		this.build_num = build_num;
	}
	
	public String getfilename(){
		return this.getProject()+"_"+this.getBuild_num();
	}
	public String getApkPath(){
		return File.separator+this.getProject()+File.separator+this.getBuild_num()+File.separator;
	}
	
}
