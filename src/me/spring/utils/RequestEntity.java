package me.spring.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.util.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import me.spring.entity.ImageInfo;

public class RequestEntity {
	public static Map<String, Object> resolve(HttpServletRequest request) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        byte[] entityArray = request.getInputStream().readAllBytes();
        String entityString = new String(entityArray, "UTF-8");
        String contentType = request.getContentType();
        String boundaryTag = "boundary=";
        int boundaryTagStartPosition = contentType.indexOf(boundaryTag);
        String boundary = "--" + contentType.substring(boundaryTagStartPosition + boundaryTag.length());
        int boundaryLength = boundary.length();
        String endBoundary = boundary + "--";
        String boundaryCheck = "";
        String subEntity = "";
        List subEntities = new ArrayList();
        List subEntitiesParsed = new ArrayList();
        result.put("contentType", contentType);
        result.put("boundary", boundary);
        result.put("contentLength", request.getContentLength());
        result.put("entity", entityString);
        while (true) {
            boundaryTagStartPosition = entityString.indexOf(boundary);
            boundaryCheck = entityString.substring(boundaryTagStartPosition,
                    boundaryTagStartPosition + boundaryLength + 2);
            if (boundaryCheck.equals(endBoundary)) {
                break;
            }
            entityString = entityString.substring(boundaryTagStartPosition + boundaryLength + 2);
            boundaryTagStartPosition = entityString.indexOf(boundary);
            subEntity = entityString.substring(0, boundaryTagStartPosition);
            subEntities.add(subEntity);
            subEntitiesParsed.add(resolveEntity(subEntity));
        }
        result.put("subEntities", subEntities);
        result.put("subEntitiesParsed", subEntitiesParsed);
        return result;
    }
    private static Map<String, Object> resolveEntity(String entity) {
        Map<String, Object> result = new HashMap<String, Object>();
        String[] lines = entity.split("\r\n");
        int lineIndex = 0;
        boolean isEntityBegin = false;
        String entityValue = "";
        for (String line : lines) {
            if (line.isEmpty() & !isEntityBegin) {
                isEntityBegin = true;
                continue;
            }
            if (isEntityBegin) {
                if (lineIndex != lines.length - 2) {
                    entityValue += line + "\r\n";
                } else {
                    entityValue += line;
                    break;
                }
            } else {
                String[] params = line.split(":");
                String[] valuePairs = params[1].split(";");
                for (String valuePair : valuePairs) {
                    if (valuePair.indexOf("=") > -1) {
                        String[] values = valuePair.split("=");
                        result.put(values[0], values[1]);
                    }
                }
            }
            lineIndex++;
        }
        result.put("entity", entityValue);
        return result;
    }
    

	public static Map<String, Object> saveSingleFile(HttpServletRequest request, CommonsMultipartFile file){
		ImageInfo imageinfo = new ImageInfo();
        Map<String, Object> result = new HashMap<String, Object>();
        //??????????????????
        String originalFilename = file.getOriginalFilename();
        //??????????????????????????????
        String savingFolder = request.getServletContext().getRealPath("/WEB-INF/savedFiles");
        //????????????????????????????????????
        File checkFile = new File(savingFolder);
        if(!checkFile.exists()) {
        	checkFile.mkdirs();
        }
    	//??????UUID???????????????????????????
        int index = file.getOriginalFilename().lastIndexOf(".");
        String houzhui = file.getOriginalFilename().substring(index);//???????????????
        String uuidFileName = UUID.randomUUID().toString().replace("-","") + houzhui;
        //????????????????????????
        String savingFilename = savingFolder +"\\"+ uuidFileName;
        imageinfo.setSavingfilename(savingFilename);
        
        //??????????????????
        Path path = Paths.get(savingFilename);  
        String contentType = null;  
        try {  
            contentType = Files.probeContentType(path);  
            imageinfo.setContenttype(contentType);
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        
        File newFile = new File(savingFilename);
        imageinfo.setOriginalfilename(uuidFileName);
        try {
            file.transferTo(newFile);           
            result.put("resultCode", 0);
            result.put("resultMsg", "??????????????????");
            result.put("imageinfo", imageinfo);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("resultCode", -1);
            result.put("resultMsg", e.getMessage());
        }
        
        return result;
    }
	
	public static List<Map<String, Object>> saveMultipleFile(HttpServletRequest request) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>(); 
		//1. ??????Session??????
        HttpSession session = request.getSession();
		Object obj = session.getAttribute("results");
		if(obj!=null){
			results = (List<Map<String, Object>>) obj;
		}
		
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            Iterator<String> filenames = multipartHttpServletRequest.getFileNames();
            while (filenames.hasNext()) {
                Map<String, Object> result = new HashMap<String, Object>();
                String filename = filenames.next().toString();   
                String savingFolder = request.getServletContext().getRealPath("/WEB-INF/savedFiles");  
                session.setAttribute("savingFolder", savingFolder);
                //????????????????????????????????????
                File checkFile = new File(savingFolder);
                if(!checkFile.exists()) {
                	checkFile.mkdirs();
                }
                MultipartFile file = multipartHttpServletRequest.getFile(filename);
                if (!file.isEmpty()) {
                	//??????UUID???????????????????????????
                    int index=file.getOriginalFilename().lastIndexOf(".");
                    String houzhui=file.getOriginalFilename().substring(index);//???????????????
                    String uuidFileName=UUID.randomUUID().toString().replace("-","")+houzhui;
                    
                    String savingFilename = savingFolder + "\\" + uuidFileName;
                    //??????????????????
                    Path path = Paths.get(file.getOriginalFilename());  
                    String contentType = null;  
                    try {  
                        contentType = Files.probeContentType(path);  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }
                    
                    //????????????
                    result.put("contentType", contentType);
                    //???????????????
                    result.put("originalFilename", file.getOriginalFilename());
                    //???????????????????????????????????????
                    result.put("randomeFileName", uuidFileName);
                    //?????????????????????
                    result.put("savingFilename", savingFilename);
                    //????????????
                    result.put("fileSize", String.format("%,d", file.getSize() / 1024 + 1) + "KB");
                    File newFile = new File(savingFilename);
                    try {
                        file.transferTo(newFile);
                        result.put("resultCode", 0);
                        result.put("resultMsg", "??????????????????");
                    } catch (Exception e) {
                        e.printStackTrace();
                        result.put("resultCode", -1);
                        result.put("resultMsg", e.getMessage());
                    }
                    results.add(result);
                }
            }
        }
        session.setAttribute("results", results);
        
        return results;
    }
	
	
	public static ResponseEntity<byte[]> download(String savingFilename,String filename) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus statusCode = HttpStatus.OK;       
        byte[] data = null;
        try {
            data = FileUtils.readFileToByteArray(new File(savingFilename));
            filename = URLEncoder.encode(filename,"UTF-8");
            //Content-Disposition:inline|attchment
            headers.set("Content-Type", "application/octet-stream");
            headers.set("Content-Disposition", "attchment;filename="+filename);                        
        } catch (Exception e) {
            e.printStackTrace();
            headers.set("Content-Type", "text/html;charset=utf-8");
            try {
                data = e.getMessage().getBytes("UTF-8");
            } catch (UnsupportedEncodingException uee) {
                data = e.getMessage().getBytes();
            }
        }       
        return new ResponseEntity<byte[]>(data,headers,statusCode);
    }


	public static ResponseEntity<byte[]> check(String savingFilename,String filename,String contentType) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus statusCode = HttpStatus.OK;       
        byte[] data = null;
        try {
            data = FileUtils.readFileToByteArray(new File(savingFilename));
            filename = URLEncoder.encode(filename,"UTF-8");
            //Content-Disposition:inline|attchment
            headers.set("Content-Type", contentType);
            headers.set("Content-Disposition", "inline;filename="+filename);                        
        } catch (Exception e) {
            e.printStackTrace();
            headers.set("Content-Type", "text/html;charset=utf-8");
            try {
                data = e.getMessage().getBytes("UTF-8");
            } catch (UnsupportedEncodingException uee) {
                data = e.getMessage().getBytes();
            }
        }       
        return new ResponseEntity<byte[]>(data,headers,statusCode);
    }
	

	public static String dataBase64(String savingFilename) {               
        byte[] data = null;      
        try {
            data = FileUtils.readFileToByteArray(new File(savingFilename));                                  
        } catch (Exception e) {
            try {
                data = e.getMessage().getBytes("UTF-8");
            } catch (UnsupportedEncodingException uee) {
                data = e.getMessage().getBytes();
            }
        }
        return Base64.encodeBase64String(data);
    }
	
	public static boolean deleteServerFile(String savingFilename){
		boolean delete_flag = false;
		File file = new File(savingFilename);
		if (file.exists() && file.isFile() && file.delete())
			delete_flag = true;
        else
        	delete_flag = false;
		return delete_flag;
	}
}
