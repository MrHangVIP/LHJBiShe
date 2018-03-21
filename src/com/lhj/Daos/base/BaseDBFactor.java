package com.lhj.Daos.base;

import java.util.List;

import com.lhj.utils.DBUtil;

/**
 * 鏁版嵁搴撴搷浣滃�?绫�
 * @author moram
 *
 * @param <T>
 */
public abstract class BaseDBFactor<T> extends DBUtil{
	
	/**
	 * 鏁版嵁搴撴暟鎹鍔�?
	 * @return
	 */
	public abstract boolean insertData(T t);

	/**
	 * 鏁版嵁鏌ヨ
	 * @return
	 */
	public abstract List<T> getDataList(Object... obj);
	
	public abstract T getData(Object... obj);
	
	/**
	 * 鏇存柊鏁版嵁
	 * @param obj
	 * @return
	 */
	public abstract boolean updateData(Object... obj);
	
	/**
	 * 鍒犻櫎鏁版嵁
	 * @param id
	 * @return
	 */
	public abstract boolean deleteData(int id);
	
	

}
