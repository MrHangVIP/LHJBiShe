package com.lhj.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lhj.Daos.base.BaseDBFactor;
import com.lhj.beans.BusinessBean;
import com.lhj.beans.StatusBean;
import com.lhj.beans.VehicleBean;
import com.lhj.utils.DateUtil;

/**
 * 鐢ㄦ埛鐩稿叧鏁版嵁鎿嶄綔鐨勫疄鐜扮被
 * 
 * @author moram
 *
 */
public class VehicleDaoImp extends BaseDBFactor<VehicleBean> {

	@Override
	public boolean insertData(VehicleBean t) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "insert into t_vehicle(businessid,typeid,name, biref,createtime,indexpicurl,price,typetitle,level,identity) value"
					+ "(?,?,?,?,?,?,?,?,?,?)";
			stat = conn.prepareStatement(sql);
			// 璁剧疆鍊�
			stat.setString(1, t.getBusinessId());
			stat.setInt(2, t.getTypeId());
			stat.setString(3, t.getName());
			stat.setString(4, t.getBiref());
			stat.setString(5, DateUtil.getCurrentDate());
			stat.setString(6, t.getIndexpicurl());
			stat.setDouble(7, t.getPrice());
			stat.setString(8, t.getTypeTitle());
			stat.setString(9, t.getLevel());
			stat.setString(10, t.getIdentity());
			rowCount = stat.executeUpdate();
			if (rowCount > 0) {
				int ret=-1;
				ResultSet rs = stat.getGeneratedKeys();
				if (rs.next()) {
					Object obj = rs.getObject(1);
					System.out.println("QuestionnaireDaoImp:"+obj.toString());
					ret=Integer.parseInt(obj.toString());
				}
				if(ret==-1){
					return false;
				}
				StatusDaoImp statusDaoImp=new StatusDaoImp();
				StatusBean statusBean=new StatusBean();
				statusBean.setStatusId(0);
				statusBean.setStatusType("可借");
				statusBean.setVehcileId(ret);
				boolean result=statusDaoImp.insertData(statusBean);
				if(result){
					return true;
				}else{
					deleteData(ret);
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(stat, conn);
		}
		return false;
	}

	@Override
	public List<VehicleBean> getDataList(Object... obj) {
		Connection conn = null;
		List<VehicleBean> ticketBeans = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from t_vehicle";
			ticketBeans = (List<VehicleBean>) qr.query(conn, sql, new BeanListHandler<VehicleBean>(VehicleBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(null, conn);
		}
		return ticketBeans;
	}

	/**
	 * 根据类型获取
	 */
	public List<VehicleBean> getDataLists(int typeId, String businessId) {
		Connection conn = null;
		List<VehicleBean> ticketBeans = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
			String sql = "select a.*,b.statusid,b.statustype from t_vehicle as a " + "inner join t_status "
					+ "as b on a.vehicleid = b.vehicleid where a.typeid= ? and a.businessId = ? group by vehicleid desc";
			ticketBeans = (List<VehicleBean>) qr.query(conn, sql, new BeanListHandler<VehicleBean>(VehicleBean.class), typeId, businessId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(null, conn);
		}
		return ticketBeans;
	}
	
	/**
	 * 根据类型获取
	 */
	public List<VehicleBean> getDataLists(int typeId, String businessId,int vehicleId) {
		Connection conn = null;
		List<VehicleBean> ticketBeans = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
			String sql = "select a.*,b.statusid,b.statustype from t_vehicle as a " + "inner join t_status "
					+ "as b on a.vehicleid = b.vehicleid where a.typeid= ? and a.businessId = ? and a.vehicleId != ? group by vehicleid desc";
			ticketBeans = (List<VehicleBean>) qr.query(conn, sql, new BeanListHandler<VehicleBean>(VehicleBean.class), typeId, businessId,vehicleId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(null, conn);
		}
		return ticketBeans;
	}
	
	/**
	 * 根据类型获取
	 */
	public List<VehicleBean> getDataLists(String businessId) {
		Connection conn = null;
		List<VehicleBean> ticketBeans = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
			String sql = "select a.*,b.statusid,b.statustype from t_vehicle as a " + "inner join t_status "
					+ "as b on a.vehicleid = b.vehicleid where a.businessId = ? group by vehicleid desc";
			ticketBeans = (List<VehicleBean>) qr.query(conn, sql, new BeanListHandler<VehicleBean>(VehicleBean.class), businessId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(null, conn);
		}
		return ticketBeans;
	}
	
	public boolean identityCheck(String identity) {
		Connection conn = null;
		VehicleBean vehicleBean = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from t_vehicle where identity = ?";
			vehicleBean = (VehicleBean) qr.query(conn, sql, new BeanHandler(VehicleBean.class), identity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(null, conn);
		}
		if (vehicleBean == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateData(Object... obj) {
//		Connection conn = null;
//		PreparedStatement stat = null;
//		int rowCount = 0;
//		try {
//			conn = getConn();
//			String sql = "update t_token set token = ? and createtime = ? where userphone = ? ";
//			stat = conn.prepareStatement(sql);
//			// 璁剧疆鍊�
//			stat.setString(1, (String) obj[0]);
//			stat.setString(2, (String) obj[1]);
//			stat.setLong(3, System.currentTimeMillis());
//			// 鎵ц
//			rowCount = stat.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeConn(stat, conn);
//		}
//		if (rowCount > 0) {
//			return true;
//		}
		return false;
	}

	@Override
	public boolean deleteData(int id) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "delete t_vehicle where vehicleid = ? ";
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

	@Override
	public VehicleBean getData(Object... obj) {
		int vehicleId=(int)obj[0];
		Connection conn = null;
		VehicleBean TicketBean = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from t_vehicle where vehicleId= ?";
			TicketBean = (VehicleBean) qr.query(conn, sql, new BeanHandler<VehicleBean>(VehicleBean.class), vehicleId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			closeConn(null, conn);
		}
		return TicketBean;
	}

}
