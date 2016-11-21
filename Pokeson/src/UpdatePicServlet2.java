

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.cm.model.CmDAO;
import com.cm.model.CmVO;

/**
 * Servlet implementation class UpdatePicServlet
 */
@WebServlet("/UpdatePicServlet2.java")
@MultipartConfig()
public class UpdatePicServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdatePicServlet2() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
/*
 ByteArrayOutputStream baos = new ByteArrayOutputStream();
//�w��
byte buffer[] = new byte[512];
//����Ū�i�Ӫ���
int length = 0;
//���p����-1�N��S����ƤF
while( (length = is.read(buffer)) != -1){
//�q�w�İ�Ū��buffer�̭�0~length-1����m
baos.write(buffer, 0, length);
<div id="pix-detect-at-80-precent" style="width: 0px; height: 0px;"></div>    }
//ByteArrayOutputStream�ন�줸�}�C
byte data [] = baos.toByteArray();
is.close();
baos.close();
 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Collection<Part> part=request.getParts();
    Integer cmno;
    String imageName = request.getParameter("image");
	int i = Integer.parseInt(imageName);
	System.out.println(i+"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    InputStream is=null;
    for(Part mypart: part){
    	    String fileName=mypart.getName();
    	    String textValue=request.getParameter(fileName);
    	if(mypart.getContentType()==null){
    		if(fileName.equals("cmno")){
    			cmno=Integer.valueOf(textValue); //cast
    		}
    	}
    	else if (mypart.getContentType()!=null){
    	   String picName =  mypart.getName();
    	   if(picName.equals("pic")){
    	   is=mypart.getInputStream();	 
    	   System.out.println(is);
    	   }  	   
    	}
    }//end loop
    CmDAO cmdao= new CmDAO();
    CmVO cmVO=cmdao.findByPrimaryKey(i);
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
  byte buffer[] = new byte[512];
  int length = 0;
  byte data []=null;
  while( (length = is.read(buffer)) != -1){
  baos.write(buffer, 0, length);
   data = baos.toByteArray();
  }
  System.out.println(data);
  cmVO.setPicture(data);
    cmdao.update(cmVO);
    System.out.println(cmVO.getCm_no());
    is.close();
    baos.close();
    String url = "/SuccessTest.jsp";
	RequestDispatcher successView = request.getRequestDispatcher(url);
	successView.forward(request, response);
  }
}