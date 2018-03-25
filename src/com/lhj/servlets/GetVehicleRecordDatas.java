package com.lhj.servlets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhj.Daos.VehicleRecordDaoImp;
import com.lhj.beans.VehicleRecordBean;
import com.lhj.servlets.base.BaseServletFactory;

import net.sf.json.JSONObject;

public class GetVehicleRecordDatas extends BaseServletFactory {
	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		String businessId=request.getParameter("businessId");
		String userId=request.getParameter("userId");
		String state=request.getParameter("state");
		String vehicleId=request.getParameter("vehicleId");
		Map<String, String> map = new HashMap<String, String>();
		VehicleRecordDaoImp vehicleRecordDaoImp = new VehicleRecordDaoImp();
		List<VehicleRecordBean> vehicleRecordBeans = vehicleRecordDaoImp.getDatasWithState(businessId,userId,state,vehicleId);
		JSONObject itemJson = JSONObject.fromObject(vehicleRecordBeans);
		map.put("result", "success");
		map.put("data", itemJson.toString());
		return map;
	}
}
