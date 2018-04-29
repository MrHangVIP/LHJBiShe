package com.lhj.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lhj.Daos.base.BaseDBFactor;
import com.lhj.beans.UserBean;
import com.lhj.beans.VehicleBean;
import com.lhj.beans.VehicleRecordBean;
import com.lhj.utils.DateUtil;

public class VehicleRecordDaoImp extends BaseDBFactor<VehicleRecordBean> {

	@Override
	public boolean insertData(VehicleRecordBean t) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "insert into t_borrow_record(vehicleid,userid,businessid, createtime,createtimestmp,starttime,finishtime,message,state) value"
					+ "(?,?,?,?,?,?,?,?,?)";
			stat = conn.prepareStatement(sql);
			// 璁剧疆鍊�
			stat.setInt(1, t.getVehicleId());
			stat.setInt(2, t.getUserId());
			stat.setString(3, t.getBusinessId());
			stat.setLong(5, System.currentTimeMillis());
			stat.setString(4, DateUtil.getCurrentDate());
			stat.setString(6, t.getStartTime());
			stat.setString(7, t.getFinishTime());
			stat.setString(8, t.getMessage());
			stat.setString(9, t.getState());
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
	public List<VehicleRecordBean> getDataList(Object... obj) {
		return null;
	}

	@Override
	public VehicleRecordBean getData(Object... obj) {
		Connection conn = null;
		String businessid=obj[0].toString();
		int userid=(int)obj[1];
		String state=obj[2].toString();
		VehicleRecordBean vehicleBean = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from t_borrow_record where businessid = ? , userid= ? and state = ?";
			vehicleBean = (VehicleRecordBean) qr.query(conn, sql, new BeanListHandler<VehicleRecordBean>(VehicleRecordBean.class), businessid,userid,state);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(null, conn);
		}
		return vehicleBean;
	}

	@Override
	public boolean updateData(Object... obj) {
		int recordId=(int)obj[0];
		String state=obj[1].toString();
		int vehicleId=(int)obj[2];
		if("1".equals(state) ){
			StatusDaoImp daoImp=new StatusDaoImp();
			daoImp.updateData(1,"使用中",vehicleId);
		}
		if("3".equals(state)){
			StatusDaoImp daoImp=new StatusDaoImp();
			daoImp.updateData(0,"可用",vehicleId);
		}
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "update t_borrow_record set state = ? where id = ? ";
			stat = conn.prepareStatement(sql);
			// 璁剧疆鍊�
			stat.setString(1,state);
			stat.setInt(2,recordId);
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
	 * 同意某一申请或者 修改车辆状态 如维修和报废时，需要调用此方法
	 * @param vehicleId
	 * @param state
	 * @return
	 */
	public boolean updateData(int vehicleId,String state) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "update t_borrow_record set state = ? where vehicleId = ? and state = 0";
			stat = conn.prepareStatement(sql);
			// 璁剧疆鍊�
			stat.setString(1,state);
			stat.setInt(2,vehicleId);
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
		return false;
	}
	
	/**
	 * 用戶根据状态获取
	 * @return
	 */
	public List<VehicleRecordBean> getDatasWithState(String businessId,int userid,String state,String  vehicleId){
		
		Connection conn = null;
		List<VehicleRecordBean> VehicleRecordBeans = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
//			String sql = "select a.*,b.statusid,b.statustype from t_vehicle as a " + "inner join t_status "
//					+ "as b on a.vehicleid = b.vehicleid  inner join t_borrow_record as c on a.vehicleid = c.vehicleid group by createtimestmp desc";
			String sql = "select * from t_borrow_record ";
			StringBuilder sb=new StringBuilder(sql);
			sb.append(" where businessid = \"" + businessId+"\"");
			if(userid!=-1){
				sb.append(" and userid = " + userid);
			}
			if(!"".equals(state) && state!=null){
				sb.append(" and state = " + state);
			}
			if(!"".equals(vehicleId) && vehicleId!=null){
				sb.append(" and vehicleId = " + vehicleId);
			}
			sb.append(" group by createtimestmp desc");
			VehicleRecordBeans = (List<VehicleRecordBean>) qr.query(conn, sb.toString(), new BeanListHandler<VehicleRecordBean>(VehicleRecordBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(null, conn);
		}
		if(VehicleRecordBeans!=null && VehicleRecordBeans.size()>0){//获取用户信息
			UserDaoImp daoImp=new UserDaoImp();
			for(VehicleRecordBean bean:VehicleRecordBeans){
				UserBean user=daoImp.getData(bean.getUserId(),bean.getBusinessId());
				bean.setUserBean(user);
			}
		}
		if(VehicleRecordBeans!=null && VehicleRecordBeans.size()>0){//获取用户信息
			VehicleDaoImp daoImp=new VehicleDaoImp();
			for(VehicleRecordBean bean:VehicleRecordBeans){
				VehicleBean vehicle=daoImp.getData(bean.getVehicleId());
				bean.setVehicleBean(vehicle);
			}
		}

		return VehicleRecordBeans;
	}
	
}
