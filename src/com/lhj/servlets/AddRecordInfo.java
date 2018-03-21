package com.lhj.servlets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhj.Daos.TagDaoImp;
import com.lhj.Daos.VehicleRecordDaoImp;
import com.lhj.beans.TagBean;
import com.lhj.beans.VehicleRecordBean;
import com.lhj.servlets.base.BaseServletFactory;

import net.sf.json.JSONObject;

public class AddRecordInfo extends BaseServletFactory {
	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		String businessId=request.getParameter("businessId");
		String userId=request.getParameter("userId");
		String starttime=request.getParameter("starttime");
		String finishtime=request.getParameter("finishtime");
		String message=request.getParameter("message");
		String vehicleId=request.getParameter("vehicleId");
		VehicleRecordDaoImp vehicleRecordDaoImp = new VehicleRecordDaoImp();
		VehicleRecordBean bean=new VehicleRecordBean();
		bean.setBusinessId(businessId);
		bean.setUserId(Integer.parseInt(userId));
		bean.setVehicleId(Integer.parseInt(vehicleId));
		bean.setState("0");//…Í«Î÷–
		bean.setMessage(message);
		bean.setStartTime(starttime);
		bean.setFinishTime(finishtime);
		Boolean result = vehicleRecordDaoImp.insertData(bean);
		Map<String, String> map = new HashMap<String, String>();
		if (result) {
			map.put("result", "success");
			map.put("data", "success");
		} else {
		    map.put("result", "fail");
		    map.put("data", "insertfail");
		}
		return map;
	}
}
