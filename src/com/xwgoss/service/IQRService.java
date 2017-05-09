package com.xwgoss.service;

import java.io.File;

import com.xwgoss.bean.Pack;

public interface IQRService {
	public void generQRCode(String content,String destpah,String filename) throws Exception;

	public String getUrl4QR(Pack pack);

	public File getFile(Pack pack);
}
