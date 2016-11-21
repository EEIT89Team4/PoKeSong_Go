package com.cm.model;

import java.util.*;
import com.cm.model.CmVO;

public interface CmDAO_interface {
	      public void insert(CmVO CmVO);
          public void update(CmVO CmVO);
          public void delete(Integer cm_no);
          public CmVO findByPrimaryKey(Integer cm_no);
	      public List<CmVO> getAllCm();

}
