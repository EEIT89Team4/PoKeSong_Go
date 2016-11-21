import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.productclass.model.ProductClassVO;

import product.ProductService;
import product.ProductVO;

public class TaskRun implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		ProductService ps = new ProductService();
		List<ProductVO> productlist = ps.getAllProduct();
		Date date = new Date();
		String nowdaystring = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String nowstr[] = nowdaystring.split("-");
		Calendar.Builder calbuilder = new Calendar.Builder().setDate(Integer.valueOf(nowstr[0]),
				Integer.valueOf(nowstr[1]), Integer.valueOf(nowstr[2]));
		Calendar nowcalendar = calbuilder.build();
		long nowsec = nowcalendar.getTimeInMillis(); // 現在時間秒數

		// 現在時間毫秒數

		for (ProductVO productvo : productlist) { // 檢驗每一個產品是否過期或是該進入即期品區
			// System.out.println("1");
			Calendar.Builder builder = new Calendar.Builder();
			Calendar cal = null;
			Date dateline = productvo.getDeadline();
			  int classno = productvo.getProductClassVO().getClass_no();
			if (dateline != null && classno != 4 ) {
				long daysec = productvo.getDeadlineday() * 24 * 60 * 60 * 1000; // 即期品天數
				String str[] = dateline.toString().split("-");
				cal = builder.setDate(Integer.valueOf(str[0].trim()), Integer.valueOf(str[1].trim()),
						Integer.valueOf(str[2].trim())).build();
				long productsec = cal.getTimeInMillis(); // 過期日毫秒數
				if (((productsec - nowsec) < daysec) && (productsec - nowsec) > 0) { // 商品未過期
					System.out.println(productvo.getProduct_name() + "即期");
					ProductClassVO vo = productvo.getProductClassVO();
					vo.setClass_no(classno);
					productvo.setProductClassVO(vo);
					ps.update(productvo);
				} else if (productsec - nowsec <= 0) { // 商品已過期
					System.out.println(productvo.getProduct_name() + "過期");
					productvo.setProduct_state(1);
					ps.update(productvo);
				}
			}
			if(classno == 4){
				productvo.setProduct_price((int) (productvo.getProduct_price()*0.9));
				ps.update(productvo);
			}
		}

	}

}
