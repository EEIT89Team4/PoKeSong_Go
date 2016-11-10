

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import product.ProductDAO;
import product.ProductVO;

/**
 * Servlet implementation class UpdatePicServlet
 */
@WebServlet("/UpdatePicServlet.java")
@MultipartConfig()
public class UpdatePicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdatePicServlet() {
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
    Integer productno=null;
    InputStream is=null;
    System.out.println(is);
    for(Part mypart: part){
    	   String fileName=mypart.getName();
    	    String textValue=request.getParameter(fileName);
    	if(mypart.getContentType()==null){
    		if(fileName.equals("productno")){
    			productno=Integer.valueOf(textValue); //cast
    		}
    	}
    	else if (mypart.getContentType()!=null){
    	   String picName =  mypart.getName();
    	   if(picName.equals("pic")){
    	   is=mypart.getInputStream();	   
    	   }  	   
    	}
    }//end loop
    ProductDAO productdao= new ProductDAO();
    ProductVO productVO=productdao.getProductById(productno);
    
   
    System.out.println(is);
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
  //�w��
  byte buffer[] = new byte[512];
  //����Ū�i�Ӫ���
  int length = 0;
  //���p����-1�N��S����ƤF
  byte data []=null;
  while( (length = is.read(buffer)) != -1){
  //�q�w�İ�Ū��buffer�̭�0~length-1����m
  baos.write(buffer, 0, length);
  //ByteArrayOutputStream�ন�줸�}�C
   data = baos.toByteArray();
  }
//  productVO.setPictrue(data);  //小圖picture
  	productVO.setBsizepic(data); //大圖bsizepic
  
    productdao.update(productVO);
    System.out.println(productVO.getProduct_name());
    is.close();
    baos.close();
  }
}