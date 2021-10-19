package com.syun.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

// 要導入commons-fileupload及commons-io.jar
public class FileServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 判斷請求是普通表單還是帶文件的表單
		if(!ServletFileUpload.isMultipartContent(req)) {
			return; // 終止方法，此為普通表單，直接返回
		}
		
		// 建立上傳文件的保存路徑，建議在WEB_INF下，用戶無法直接訪問
		String uploadPath = this.getServletContext().getRealPath("/WEB-INF/upload");
		File uploadFile = new File(uploadPath);
		if(!uploadFile.exists()) {
			uploadFile.mkdir(); // 建立文件存放的資料夾
		}
		
		// 建立暫時資料夾，如果檔案太大放這裡，過幾天刪除文件，或提醒用戶
		String tmpPath = this.getServletContext().getRealPath("/WEB-INF/tmp");
		File file = new File(tmpPath);
		if(!file.exists()) {
			file.mkdir(); // 建立暫時文件存放的資料夾
		}
		
		DiskFileItemFactory factory = getDiskFileItemFactory(file);
		ServletFileUpload upload = getServletFileUpload(factory);
		String msg = uploadParseRequest(upload, req, uploadPath);
		
		try {
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("info.jsp").forward(req, resp);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	public static DiskFileItemFactory getDiskFileItemFactory(File file) {
		// 1.建立DiskFileItemFactory物件，處理文件上傳路徑或大小限制
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 通過這個factory設置緩衝區，上傳文件大於這個緩衝區，將文件放置暫時資料夾
		factory.setSizeThreshold(1024*1024);
		factory.setRepository(file);
		
		return factory;
	}
	
	public static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory) {
		// 2.獲得ServletFileUpload物件
		ServletFileUpload upload = new ServletFileUpload(factory);
				
		
		// 監聽文件上傳進度
		upload.setProgressListener(new ProgressListener() {
			@Override
			// pBytesRead 已經取得的文件大小
			// pContentLength 文件大小
			public void update(long pBytesRead, long pContentLength, int pItem) {
				System.out.println("總大小:" + pContentLength + "已上傳:" + pBytesRead);
			}
		});
				
		// 設定文字格式
		upload.setHeaderEncoding("UTF-8");
		// 設定一個文件最大值
		upload.setFileSizeMax(1024 * 1024 * 10);
		// 設定總共能上傳的文件大小
		upload.setSizeMax(1024 * 1024 * 10);
		
		return upload;
	}
	
	
	public static String uploadParseRequest(ServletFileUpload upload, HttpServletRequest req, String uploadPath) {
		String msg = "";
		
		// 處理上傳得文件
		// 解析前端請求，封裝成一個FileItem物件，從ServletFileUpload獲得
		try {
			List<FileItem> fileItems = upload.parseRequest(req);
			
			// 判斷為普通表單還是帶文件表單
			for(FileItem fileItem : fileItems) {
				if(fileItem.isFormField()) {
					// 普通表單
					String name = fileItem.getFieldName(); // 前端表單的name
					String value = fileItem.getString("UTF-8");
					System.out.println(name + ":" + value);
			}
				else {
					// **************處理表單的文件**************
					String uploadFileName = fileItem.getName();
					if(uploadFileName.trim().equals("")||uploadFileName==null) {
			 	    continue;
				}
					// 文件名稱
					String filename = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
					// 文件副檔名
					String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
					
					System.out.println(filename);
					
					
					// 使用UUID.class(唯一識別碼)，確保文件名稱不重複
					String uuidPath = UUID.randomUUID().toString();
							
					// **************處理文件存放位置************
					// 文件實際存放位置
					String realPath = uploadPath + "\\" + uuidPath;
					// 給每個文件都建立一個資料夾
					File realPathFile = new File(realPath);
					if(!realPathFile.exists()) {
						realPathFile.mkdir();
					}
							
							
					// **************文件傳輸**************
					InputStream inputStream = fileItem.getInputStream();
					FileOutputStream fos = new FileOutputStream(realPath + "/" + filename);
					byte[] buffer = new byte[1024 * 1024];
					int len = 0;
					while((len=inputStream.read(buffer))>0) {
						fos.write(buffer,0,len);
					}
					fos.close();
					inputStream.close();
					
					msg = "文件上傳成功";
					fileItem.delete();
				}
			}
					
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
}
