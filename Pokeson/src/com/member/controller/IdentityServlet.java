package com.member.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@WebServlet("/IdentityServlet")
public class IdentityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// 產生驗證碼
	// 1.可用的亂數們
	public final static char[] words = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };
	// 2.亂數
	public static Random random = new Random();

	// 3.取得六位數亂數
	public static String getRandomWords() {
		// 字串快取記憶體
		StringBuffer buffer = new StringBuffer();
		for (int i = 1; i <= 6; i++) {
			buffer.append(words[random.nextInt(words.length)]);
			// random.nextInt(42)=>0-41隨機亂數=>等價於Math.random()
		}
		return buffer.toString();
	}

	// 4.取得隨機顏色(底色用)
	public static Color getRandomColor() {

		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
		// 參數為RGB三原色(紅，綠，藍)
	}

	// 5.隨機顏色的反色(顯示字用)
	public static Color getReverseColor(Color c) {

		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
	}

	public IdentityServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		System.out.println("123");
		// 1.輸出類型
		response.setContentType("image/jpeg");

		// 2.隨機字串，放到session中?
		String randomWords = getRandomWords();
		request.getSession().setAttribute("randomWords", randomWords);
//		System.out.println(randomWords);
//		request.setAttribute("randomWords", randomWords);
		// 3.圖片屬性 長、寬、隨機顏色(背景、前景)
		int width = 150;
		int height = 50;
		Color color = getRandomColor();
		Color reverse = getReverseColor(color);

		// 4.建立彩色圖片BufferedImage
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 5.獲得繪圖物件=>設定字型、背景、顏色、隨機字元，放的順序很重要
		Graphics2D g = img.createGraphics();
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.setColor(color);
		g.fillRect(0, 0, width, height);// 背景範圍
		g.setColor(reverse);
		g.drawString(randomWords, 40, 30);

		// 6.噪音點
		for (int i = 0, n = random.nextInt(50); i < n; i++) {
			g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		// 7.轉成jpeg格式
		ServletOutputStream out = response.getOutputStream(); //轉成jpeg格式
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(img);
		out.flush();
//		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
//		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
