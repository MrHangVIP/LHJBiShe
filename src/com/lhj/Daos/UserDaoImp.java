package com.lhj.Daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lhj.Daos.base.BaseDBFactor;
import com.lhj.beans.UserBean;
import com.lhj.beans.VehicleRecordBean;
import com.lhj.utils.DateUtil;

/**
 * 鐢ㄦ埛鐩稿叧鏁版嵁鎿嶄綔鐨勫疄鐜扮被
 * 
 * @author moram
 *
 */
public class UserDaoImp extends BaseDBFactor<UserBean> {

	@Override
	public boolean insertData(UserBean user) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "insert into t_user(userphone, userpass,createtime,businessid) value(?,?,?,?)";
			stat = conn.prepareStatement(sql);
			// 璁剧疆鍊�
			stat.setString(1, user.getUserPhone());
			stat.setString(2, user.getUserPass());
			stat.setString(3, DateUtil.getCurrentDate());
			stat.setString(4, user.getBusinessId());
			// 鎵ц
			rowCount = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(stat, conn);
		}
		if (rowCount > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<UserBean> getDataList(Object... obj) {
		int userid = Integer.parseInt(obj[0].toString());
		Connection conn = null;
		List<UserBean> userList = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from t_user where userId = ?";
			userList = (List<UserBean>) qr.query(conn, sql, new BeanListHandler<UserBean>(UserBean.class), userid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(null, conn);
		}
		return userList;
	}

	@Override
	public boolean updateData(Object... obj) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "update t_token set token = ? and createtime = ? where userphone = ? ";
			stat = conn.prepareStatement(sql);
			// 璁剧疆鍊�
			stat.setString(1, (String) obj[0]);
			stat.setString(2, (String) obj[1]);
			stat.setLong(3, System.currentTimeMillis());
			// 鎵ц
			rowCount = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(stat, conn);
		}
		if (rowCount > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteData(int id) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "delete t_user where userid = ? ";
			stat = conn.prepareStatement(sql);
			// 璁剧疆鍊�
			stat.setInt(1, id);
			// 鎵ц
			rowCount = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(stat, conn);
		}
		if (rowCount > 0) {
			return true;
		}
		return false;
	}

	public boolean updateData(UserBean user) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "update t_user set age = ? ,sex = ? , headurl = ? , lastupdatetime = ? ,truename = ?, licence = ?,"
					+ "type = ? where userphone = ? and businessid = ?";
			stat = conn.prepareStatement(sql);
			// 璁剧疆鍊�
			stat.setString(1, user.getAge());
			stat.setString(2, user.getSex());
			stat.setString(3, user.getHeadUrl());
			stat.setLong(4, System.currentTimeMillis());
			stat.setString(5, user.getTrueName());
			stat.setString(6, user.getLicence());
			stat.setString(7, user.getType());
			stat.setString(8, user.getUserPhone());
			stat.setString(9, user.getBusinessId());
			// 鎵ц
			rowCount = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(stat, conn);
		}
		if (rowCount > 0) {
			return true;
		}
		return false;
	}

	public boolean updateHeadUrl(String businessid,String userPhone, String headUrl) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "update t_user set headurl= ? , lastupdatetime = ? where userphone = ? and businessid = ?";
			stat = conn.prepareStatement(sql);
			// 璁剧疆鍊�
			stat.setString(1, headUrl);
			stat.setLong(2, System.currentTimeMillis());
			stat.setString(3, userPhone);
			stat.setString(4, businessid);
			// 鎵ц
			rowCount = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(stat, conn);
		}
		if (rowCount > 0) {
			return true;
		}
		return false;
	}
	
	
	public boolean finishUserInfo(String businessid,String userPhone, String trueName,
			String sex,String birth,String licence,String level) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "update t_user set truename= ? , sex = ? , age = ? , "
					+ " licence = ? , type = ? , lastupdatetime = ? where userphone = ? and businessid = ?";
			stat = conn.prepareStatement(sql);
			// 璁剧疆鍊�
			stat.setString(1, trueName);
			stat.setString(2, sex);
			stat.setString(3, birth);
			stat.setString(4, licence);
			stat.setString(5, level);
			stat.setLong(6, System.currentTimeMillis());
			stat.setString(7, userPhone);
			stat.setString(8, businessid);
			// 鎵ц
			rowCount = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(stat, conn);
		}
		if (rowCount > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 淇敼瀵嗙爜
	 * 
	 * @param userPhone
	 * @param password
	 * @return
	 */
	public boolean updatePassword(String businessid,String userPhone, String password) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "update t_user set userpass= ? , lastupdatetime = ? where userPhone = ? and businessid = ?";
			stat = conn.prepareStatement(sql);
			// 璁剧疆鍊�
			stat.setString(1, password);
			stat.setLong(2, System.currentTimeMillis());
			stat.setString(3, userPhone);
			stat.setString(3, businessid);
			// 鎵ц
			rowCount = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(stat, conn);
		}
		if (rowCount > 0) {
			return true;
		}
		return false;
	}

	public UserBean login(String businssid,String userPhone, String userPass) {
		Connection conn = null;
		UserBean userbean = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from t_user where userphone = ? and userpass = ? and businessid = ?";
			userbean = (UserBean) qr.query(conn, sql, new BeanHandler<UserBean>(UserBean.class), userPhone, userPass, businssid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(null, conn);
		}
		return userbean;

	}

	public UserBean getUserInfo(int UserId) {
		Connection conn = null;
		UserBean userbean = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
			String sql = "select a.* , b.money from t_user as a "
					+ "inner join t_account "
					+ "as b where a.userid = b.userid "
					+ "and a.userid = ?";
			userbean = (UserBean) qr.query(conn, sql, new BeanHandler<UserBean>(UserBean.class), UserId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(null, conn);
		}
		return userbean;

	}
	
	public UserBean getUserInfo(String userPhone ,String businessid) {
		Connection conn = null;
		UserBean userbean = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from t_user where userphone = ? and businessid = ?";
			userbean = (UserBean) qr.query(conn, sql, new BeanHandler<UserBean>(UserBean.class), userPhone,businessid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(null, conn);
		}
		return userbean;

	}


	public boolean userPhoneChecked(String userPhone,String businessId) {
		Connection conn = null;
		UserBean userBean = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from t_user where userphone = ? and businessId = ?";
			userBean = (UserBean) qr.query(conn, sql, new BeanHandler(UserBean.class), userPhone,businessId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(null, conn);
		}
		if (userBean == null) {
			return false;
		}
		return true;
	}

	@Override
	public UserBean getData(Object... obj) {
		int userid=(int)obj[0];
		String businessId=obj[1].toString();
		Connection conn = null;
		UserBean userbean = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from t_user where userid = ? and businessid = ?";
			userbean = (UserBean) qr.query(conn, sql, new BeanHandler<UserBean>(UserBean.class), userid,businessId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(null, conn);
		}
		return userbean;
	}

}
