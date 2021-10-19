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

// �n�ɤJcommons-fileupload��commons-io.jar
public class FileServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �P�_�ШD�O���q����٬O�a��󪺪��
		if(!ServletFileUpload.isMultipartContent(req)) {
			return; // �פ��k�A�������q���A������^
		}
		
		// �إߤW�Ǥ�󪺫O�s���|�A��ĳ�bWEB_INF�U�A�Τ�L�k�����X��
		String uploadPath = this.getServletContext().getRealPath("/WEB-INF/upload");
		File uploadFile = new File(uploadPath);
		if(!uploadFile.exists()) {
			uploadFile.mkdir(); // �إߤ��s�񪺸�Ƨ�
		}
		
		// �إ߼Ȯɸ�Ƨ��A�p�G�ɮפӤj��o�̡A�L�X�ѧR�����A�δ����Τ�
		String tmpPath = this.getServletContext().getRealPath("/WEB-INF/tmp");
		File file = new File(tmpPath);
		if(!file.exists()) {
			file.mkdir(); // �إ߼Ȯɤ��s�񪺸�Ƨ�
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
		// 1.�إ�DiskFileItemFactory����A�B�z���W�Ǹ��|�Τj�p����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// �q�L�o��factory�]�m�w�İϡA�W�Ǥ��j��o�ӽw�İϡA�N����m�Ȯɸ�Ƨ�
		factory.setSizeThreshold(1024*1024);
		factory.setRepository(file);
		
		return factory;
	}
	
	public static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory) {
		// 2.��oServletFileUpload����
		ServletFileUpload upload = new ServletFileUpload(factory);
				
		
		// ��ť���W�Ƕi��
		upload.setProgressListener(new ProgressListener() {
			@Override
			// pBytesRead �w�g���o�����j�p
			// pContentLength ���j�p
			public void update(long pBytesRead, long pContentLength, int pItem) {
				System.out.println("�`�j�p:" + pContentLength + "�w�W��:" + pBytesRead);
			}
		});
				
		// �]�w��r�榡
		upload.setHeaderEncoding("UTF-8");
		// �]�w�@�Ӥ��̤j��
		upload.setFileSizeMax(1024 * 1024 * 10);
		// �]�w�`�@��W�Ǫ����j�p
		upload.setSizeMax(1024 * 1024 * 10);
		
		return upload;
	}
	
	
	public static String uploadParseRequest(ServletFileUpload upload, HttpServletRequest req, String uploadPath) {
		String msg = "";
		
		// �B�z�W�Ǳo���
		// �ѪR�e�ݽШD�A�ʸ˦��@��FileItem����A�qServletFileUpload��o
		try {
			List<FileItem> fileItems = upload.parseRequest(req);
			
			// �P�_�����q����٬O�a�����
			for(FileItem fileItem : fileItems) {
				if(fileItem.isFormField()) {
					// ���q���
					String name = fileItem.getFieldName(); // �e�ݪ�檺name
					String value = fileItem.getString("UTF-8");
					System.out.println(name + ":" + value);
			}
				else {
					// **************�B�z��檺���**************
					String uploadFileName = fileItem.getName();
					if(uploadFileName.trim().equals("")||uploadFileName==null) {
			 	    continue;
				}
					// ���W��
					String filename = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
					// �����ɦW
					String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
					
					System.out.println(filename);
					
					
					// �ϥ�UUID.class(�ߤ@�ѧO�X)�A�T�O���W�٤�����
					String uuidPath = UUID.randomUUID().toString();
							
					// **************�B�z���s���m************
					// ����ڦs���m
					String realPath = uploadPath + "\\" + uuidPath;
					// ���C�Ӥ�󳣫إߤ@�Ӹ�Ƨ�
					File realPathFile = new File(realPath);
					if(!realPathFile.exists()) {
						realPathFile.mkdir();
					}
							
							
					// **************���ǿ�**************
					InputStream inputStream = fileItem.getInputStream();
					FileOutputStream fos = new FileOutputStream(realPath + "/" + filename);
					byte[] buffer = new byte[1024 * 1024];
					int len = 0;
					while((len=inputStream.read(buffer))>0) {
						fos.write(buffer,0,len);
					}
					fos.close();
					inputStream.close();
					
					msg = "���W�Ǧ��\";
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
