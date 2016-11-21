package member;

import java.util.Base64;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class SmtpAuthenticator extends Authenticator {

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		String username = "pokeson1111@gmail.com";
		String password = "pokeson123";
		if ((username != null) && (username.length() > 0) && (password != null) && (password.length() > 0)) {

			return new PasswordAuthentication(username, password);
		}

		return null;
	}
}

public class MailService {

//	public static void main(String[] args) {
	public void resetPwd(String member_Email){
		String host = "smtp.gmail.com";
		String from = "pokeson1111@gmail.com";
		String to = member_Email; 
		// 得到系統屬性
		Properties props = new Properties();
		// 設定SMTP server
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.stmp.user", "username");

		// To use TLS
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.password", "password");
		// To use SSL
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		// 依據Properties建立一個Session
		Session session = Session.getInstance(props, null);
		// 從Session建立一個Message
		SmtpAuthenticator authentication = new SmtpAuthenticator();
		javax.mail.Message msg = new MimeMessage(Session.getDefaultInstance(props, new SmtpAuthenticator()));
		// Message mailMessage = new MimeMessage(session);
		try {
			// Set from email address
			msg.setFrom(new InternetAddress(from));
			// Set to mail address
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// 設定標題
			msg.setSubject("會員密碼認證信");
			// 設定內容
			msg.setText("親愛的會員您好:\n請點以下連結來重置密碼\n"
					+ "http://localhost:8081/Pokeson/MemberServlet?action=getMbrByEmail&member_Email="
					+Base64.getEncoder().encodeToString(to.getBytes()));
			// Send the actual HTML message, as big as you like
//			msg.setContent("<a href=\"https://tw.yahoo.com\"><h1>開通帳號</h1></a>", "text/html");
//			msg.setContent("<a href=\"http://localhost:8081/Pokeson/MemberServlet?action=getMbrByEmail&member_Email=yifang828@gmail.com\"><h3>Reset Password!!!</h3></a>", "text/html");

			// 傳送
			Transport.send(msg);
			System.out.println("密碼重置信件已寄出");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	public void sendMail(String member_Email){
		String host = "smtp.gmail.com";
		String from = "pokeson1111@gmail.com";
		String to = member_Email; 
		// 得到系統屬性
		Properties props = new Properties();
		// 設定SMTP server
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.stmp.user", "username");

		// To use TLS
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.password", "password");
		// To use SSL
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		// 依據Properties建立一個Session
		Session session = Session.getInstance(props, null);
		// 從Session建立一個Message
		SmtpAuthenticator authentication = new SmtpAuthenticator();
		javax.mail.Message msg = new MimeMessage(Session.getDefaultInstance(props, new SmtpAuthenticator()));
		// Message mailMessage = new MimeMessage(session);
		try {
			// Set from email address
			msg.setFrom(new InternetAddress(from));
			// Set to mail address
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// 設定標題
			msg.setSubject("寶可送確認信");
			// 設定內容
			msg.setText("您已成功加入寶可送會員");
			// Send the actual HTML message, as big as you like
//			msg.setContent("<a href=\"https://tw.yahoo.com\"><h1>開通帳號</h1></a>", "text/html");
			msg.setContent("<a href=\"http://localhost:8081/Pokeson/index.jsp\"><h1>Go Shopping!!!</h1></a>", "text/html");

			// 傳送
			Transport.send(msg);
			System.out.println("信件已寄出");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
// If you want to send an e-mail to multiple recipients then the following
// methods would be used to specify multiple e-mail IDs −
//
// void addRecipients(Message.RecipientType type, Address[] addresses)
// throws MessagingException
