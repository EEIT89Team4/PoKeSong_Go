package order;

import java.sql.SQLException;
import java.util.*;

import javax.naming.NamingException;

import order.OrderlistVO;

public interface OrderlistDAO_interface {
	      public void insert(OrderlistVO orderlistVO) ;
          public void update(OrderlistVO orderlistVO);
          public void delete(Integer order_no);
	      public List<OrderlistVO> getAllOrderlist();
		  public OrderlistVO findByPrimaryKey(Integer order_no);
		  public List<OrderlistVO> findByMember_no(Integer member_no);
		  public Integer findInsertOrder_no();
}
		
