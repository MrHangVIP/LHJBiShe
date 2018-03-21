package com.lhj.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.lhj.Daos.base.BaseDBFactor;
import com.lhj.beans.StatusBean;
import com.lhj.utils.DateUtil;

public class StatusDaoImp extends BaseDBFactor<StatusBean> {


	@Override
	public boolean insertData(StatusBean t) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "insert into t_status(statusId,vehicleid,statustype, updatetime) value(?,?,?,?)";
			stat = conn.prepareStatement(sql);
			// 璁剧疆鍊�
			stat.setInt(1, t.getStatusId());
			stat.setInt(2, t.getVehcileId());
			stat.setString(3, t.getStatusType());
			stat.setString(4, DateUtil.getCurrentDate());
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
	public List<StatusBean> getDataList(Object... obj) {
		return null;
	}

	@Override
	public StatusBean getData(Object... obj) {
		return null;
	}

	@Override
	public boolean updateData(Object... obj) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "update t_status set statusid = ? ,statustype = ? and updatetime = ?  where vehicleid = ? ";
			stat = conn.prepareStatement(sql);
			// 璁剧疆鍊�
			stat.setInt(1, (int) obj[0]);
			stat.setString(2, (String) obj[1]);
			stat.setString(3, DateUtil.getCurrentDate());
			stat.setInt(4, (int) obj[2]);
			// 鎵ц
			rowCount = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(stat, conn);
		}
		if (rowCount > 0) {
			if((int) obj[0] !=0){//维修或者报废或者使用  需要清除所有申请状态为拒绝
				VehicleRecordDaoImp daoImp=new VehicleRecordDaoImp();
				daoImp.updateData((int) obj[2], "2");
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteData(int id) {
		return false;
	}

}
