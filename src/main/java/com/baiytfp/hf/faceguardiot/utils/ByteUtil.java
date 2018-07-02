package com.baiytfp.hf.faceguardiot.utils;

import java.io.*;

public class ByteUtil {

	//字节转流
	public static final InputStream byte2Input(byte[] buf) {
		return new ByteArrayInputStream(buf);
	}

	//流转字节
	public static final byte[] input2byte(InputStream inStream)
			throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		int rc = 0;
		int intNum = 0;//对循环做控制，否则附件太大时，会报内存溢出错误 
		while ((rc = inStream.read(buff, 0, 1024)) > 0) {
			if(intNum  > 10240) {
				break;
			}
			swapStream.write(buff, 0, rc);
			intNum++;
		}
		byte[] in2b = swapStream.toByteArray();
		swapStream.close();
		return in2b;
	}
	
	/** 
     * 获得指定文件的byte数组 
     */  
    public static byte[] getBytes(String filePath){  
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }  
  
    /** 
     * 根据byte数组，生成文件 
     */  
    public static void getFile(byte[] bfile, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
    }
    
    /**
	 * 把字节数组保存为一个文件
	 * 
	 * @param b
	 * @param outputFile
	 * @return
	 */
    public static File saveFileFromBytes(byte[] b, String outputFile) {
    	return  saveFileFromBytes(b, new File(outputFile));
    }
	public static File saveFileFromBytes(byte[] b, File file) {
		if(!file.getParentFile().exists()){	//如果目录不存在，则创建
			file.getParentFile().mkdirs();
		}
		
		BufferedOutputStream stream = null;
		try {
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			// log.error("helper:get file from byte process error!");
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					// log.error("helper:get file from byte process error!");
					e.printStackTrace();
				}
			}
		}
		return file;
	}
}
