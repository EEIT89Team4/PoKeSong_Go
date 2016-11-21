

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetCarrefulImg")
public class GetCarrefulImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
//		System.out.println("carreful");
		ServletContext sc = null;
		InputStream is = null;
		OutputStream os = null;
		byte datas [] =  new byte[512];
		int count=0;
		try {
			is = getServletContext().getResourceAsStream("/images/carreful.ico");
			os=response.getOutputStream();
			
			while((count=is.read(datas))!=-1){
				os.write(datas, 0, count);
			}
			
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
