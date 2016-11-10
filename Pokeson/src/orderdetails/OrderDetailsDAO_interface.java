package orderdetails;

import java.util.List;

import order.OrderlistVO;

public interface OrderDetailsDAO_interface {
	public void insert(OrderDetailsVO orderdetailsVO);
	public void update(OrderDetailsVO orderdetailsVO);
	public void delete(OrderDetailsVO orderdetailsVO);
//	public List<OrderDetailsVO> findByPrimaryKey(OrderDetailsVO orderdetailsVO);
	public List<OrderDetailsVO> findByOrder_no(OrderDetailsVO orderdetailsVO);
//	public List<OrderDetailsVO> getAll();
}
