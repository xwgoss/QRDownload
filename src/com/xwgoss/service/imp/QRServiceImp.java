package com.xwgoss.service.imp;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;
import java.util.Random;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.xwgoss.bean.Pack;
import com.xwgoss.service.IQRService;

public class QRServiceImp implements IQRService {
	   private static final String CHARSET = "utf-8";
	   private static final String FORMAT_NAME = "JPG";
	   private static final int QRCODE_SIZE = 300;
	   private static final int WIDTH = 60;
	   private static final int HEIGHT = 60;
	   private String url;
	   private String apk_path;
	   
	public String getApk_path() {
		return apk_path;
	}

	public void setApk_path(String apk_path) {
		this.apk_path = apk_path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void generQRCode(String content,String destPath, String filename) throws Exception {
		// TODO Auto-generated method stub
	    BufferedImage image =this.createImage(content, filename,
                true);
        mkdirs(destPath);
        String file="";
        if(filename==""){
        	file = new Random().nextInt(99999999)+".jpg";
        }else{
        	file = filename;
        }
        ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));
	}
	
	 private  BufferedImage createImage(String content, String imgPath,
	            boolean needCompress) throws Exception {
	        Hashtable hints = new Hashtable();
	        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
	        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
	        hints.put(EncodeHintType.MARGIN, 1);
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
	                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
	        int width = bitMatrix.getWidth();
	        int height = bitMatrix.getHeight();
	        BufferedImage image = new BufferedImage(width, height,
	                BufferedImage.TYPE_INT_RGB);
	        for (int x = 0; x < width; x++) {
	            for (int y = 0; y < height; y++) {
	                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
	                        : 0xFFFFFFFF);
	            }
	        }
	        if (imgPath == null || "".equals(imgPath)) {
	            return image;
	        }
	        this.insertImage(image, imgPath, true);
	        return image;
	    }
	  private  void insertImage(BufferedImage source, String imgPath,
	            boolean needCompress) throws Exception {
	        File file = new File(imgPath);
	        if (!file.exists()) {
	            return;
	        }
	        Image src = ImageIO.read(new File(imgPath));
	        int width = src.getWidth(null);
	        int height = src.getHeight(null);
	        if (needCompress) { 
	            if (width > WIDTH) {
	                width = WIDTH;
	            }
	            if (height > HEIGHT) {
	                height = HEIGHT;
	            }
	            Image image = src.getScaledInstance(width, height,
	                    Image.SCALE_SMOOTH);
	            BufferedImage tag = new BufferedImage(width, height,
	                    BufferedImage.TYPE_INT_RGB);
	            Graphics g = tag.getGraphics();
	            g.drawImage(image, 0, 0, null); 
	            g.dispose();
	            src = image;
	        }
	        Graphics2D graph = source.createGraphics();
	        int x = (QRCODE_SIZE - width) / 2;
	        int y = (QRCODE_SIZE - height) / 2;
	        graph.drawImage(src, x, y, width, height, null);
	        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
	        graph.setStroke(new BasicStroke(3f));
	        graph.draw(shape);
	        graph.dispose();
	    }
	  private  void mkdirs(String destPath) {
	        File file =new File(destPath);   
	        if (!file.exists() && !file.isDirectory()) {
	            file.mkdirs();
	        }
	    }

	@Override
	public String getUrl4QR(Pack pack) {
		// TODO Auto-generated method stub
		String str=url+"/qr/download/"+pack.getProject()+"/"+pack.getBuild_num();
		System.out.println(str);
		return str;
	}

	@Override
	public File getFile(Pack pack) {
		// TODO Auto-generated method stub
		System.out.println(this.getApk_path()+pack.getApkPath());
		File file=new File(this.getApk_path()+pack.getApkPath());
		if(file.listFiles().length==1)
			System.out.println("找到一个APK");
		return file.listFiles()[0];
	}

	  
}
