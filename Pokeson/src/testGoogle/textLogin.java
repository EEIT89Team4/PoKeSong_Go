package testGoogle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/Login.do")
public class textLogin extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		URL urlObtainToken = new URL("https://accounts.google.com/o/oauth2/token");
		
		HttpURLConnection connectionObtainToken = (HttpURLConnection) urlObtainToken.openConnection();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<h1>");
		out.println("22");
		out.println("</h1>");
		connectionObtainToken.setRequestMethod("POST");
		connectionObtainToken.setDoOutput(true);

		OutputStreamWriter writer = new OutputStreamWriter(connectionObtainToken.getOutputStream());
		writer.write("code=" + req.getParameter("code") + "&"); 
		writer.write("client_id=444437052805-69ml6pka06cockj17ndvuocdnd9dnifg.apps.googleusercontent.com&"); 
		writer.write("client_secret=t9yzi1jsrEU6Sh0rwxTm1yuW&"); 
		writer.write("redirect_uri=http://localhost:8081/Pokeson/Login.do&"); 
		writer.write("grant_type=authorization_code");
		writer.close();

		
		if (connectionObtainToken.getResponseCode() == HttpURLConnection.HTTP_OK) {
			System.out.println("認證成功");
			StringBuilder sbLines = new StringBuilder("");

			
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(connectionObtainToken.getInputStream(), "utf-8"));
			String strLine = "";
			while ((strLine = reader.readLine()) != null) {
				sbLines.append(strLine);
			}

			try {
				
				JSONObject jo = new JSONObject(sbLines.toString());

				

				resp.getWriter().println(jo.getString("access_token"));
				URL urUserInfo =   
					     new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token="+jo.getString("access_token")); 
					 HttpURLConnection connObtainUserInfo =  (HttpURLConnection) urUserInfo.openConnection();
					 
					 
					 if (connObtainUserInfo.getResponseCode() == HttpURLConnection.HTTP_OK){
						  StringBuilder sbLines1   = new StringBuilder("");
						   
						  
						  BufferedReader reader1 = 
						   new BufferedReader(new InputStreamReader(connObtainUserInfo.getInputStream(),"utf-8"));
						  String strLine1 = "";
						  while((strLine1=reader1.readLine())!=null){
						   sbLines1.append(strLine1);
						  }
						  try {
							   
							   JSONObject jo1 = new JSONObject(sbLines1.toString());
//							    System.out.println(jo1);
							   
							   String email=jo1.getString("email");
							   
							   String id=jo1.getString("id"); 
							   
							   String name=jo1.getString("name");
//							  System.out.println(email+","+id+","+name);
							    req.setAttribute("email",email);
							    req.setAttribute("id",id);
							    req.setAttribute("name",name);
							    req.setAttribute("action","sign");
							    RequestDispatcher rd = req.getRequestDispatcher("/googlesign");
							    rd.forward(req, resp);
							  } catch (JSONException e) {
							   e.printStackTrace();
							  }
							 }

			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
		    rd.forward(req, resp);
		}

	}

}
