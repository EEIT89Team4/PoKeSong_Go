package myfavorite;

import java.sql.SQLException;
import java.util.List;

public interface MyFavorite_Interface {
	public void insert(MyFavoriteVO myfavoriteVO)throws SQLException;
	public void delete(MyFavoriteVO myfavoriteVO);
	public List<MyFavoriteVO> getMyFavoriteById(MyFavoriteVO myfavoriteVO);
	
}
