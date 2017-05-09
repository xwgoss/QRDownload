package com.xwgoss.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xwgoss.bean.Pack;
import com.xwgoss.service.IQRService;
import com.xwgoss.util.QRCodeUtil;
@Controller
@RequestMapping("/qr")
public class QRControl {
	@Autowired
	private IQRService qrServiceImp;
	@RequestMapping(value="/{project}/{build_num}",method=RequestMethod.GET)
	public void qrcode(Pack pack,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str=qrServiceImp.getUrl4QR(pack);
		String path=request.getSession().getServletContext().getRealPath("/img/");
		String filename=pack.getfilename()+".jpg";
		qrServiceImp.generQRCode(str, path,filename);
//		request.setAttribute("url", "../../img/"+filename);
//		request.getRequestDispatcher("/qrcode.jsp").forward(request, response);
		 response.setContentType("image/gif");
		 FileInputStream fis = null;
		 try {
		        OutputStream out = response.getOutputStream();
		        File file = new File(path+"/"+filename);
		        fis = new FileInputStream(file);
		        byte[] b = new byte[fis.available()];
		        fis.read(b);
		        out.write(b);
		        out.flush();
		    } catch (Exception e) {
		         e.printStackTrace();
		    } finally {
		        if (fis != null) {
		            try {
		               fis.close();
		            } catch (IOException e) {
			        e.printStackTrace();
			    }   
		          }
		    }
	}
	@RequestMapping(value="/download/{project}/{build_num}",method=RequestMethod.GET,produces="application/apk")
	public ResponseEntity download(Pack pack) {
		try{
		String dfilename=new String((pack.getfilename()+".apk").getBytes("utf-8"),"iso8859-1");
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
		headers.setContentDispositionFormData("attachment", dfilename); 
		File file=qrServiceImp.getFile(pack);
		InputStreamResource isResource = new InputStreamResource(new FileInputStream(file));
		FileSystemResource fileSystemResource = new FileSystemResource(file);
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		System.out.println(fileSystemResource.contentLength());
		headers.setContentLength(fileSystemResource.contentLength());
		return new ResponseEntity(isResource, headers, HttpStatus.OK);}
		catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
}
