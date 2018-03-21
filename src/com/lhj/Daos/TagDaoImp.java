package com.lhj.Daos;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.lhj.Daos.base.BaseDBFactor;
import com.lhj.beans.TagBean;

public class TagDaoImp extends BaseDBFactor<TagBean> {

	@Override
	public boolean insertData(TagBean t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TagBean> getDataList(Object... obj) {
		int userid = Integer.parseInt(obj[0].toString());
		Connection conn = null;
		List<TagBean> tagBeans = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from t_type ";
			tagBeans = (List<TagBean>) qr.query(conn, sql, new BeanHandler(TagBean.class), userid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(null, conn);
		}
		return tagBeans;
	}

	@Override
	public TagBean getData(Object... obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateData(Object... obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteData(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
