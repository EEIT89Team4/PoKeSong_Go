

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Collection<Part> part=request.getParts();
    Integer productno=null;
    InputStream is=null;
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
    
   
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
  
  byte buffer[] = new byte[512];

  int length = 0;

  byte data []=null;
  while( (length = is.read(buffer)) != -1){

  baos.write(buffer, 0, length);

   data = baos.toByteArray();
  }
  productVO.setPictrue(data);
  
    productdao.update(productVO);
//    System.out.println(productVO.getProduct_name());
    is.close();
    baos.close();
  }
}