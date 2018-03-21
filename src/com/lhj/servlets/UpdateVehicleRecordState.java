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

public class UpdateVehicleRecordState extends BaseServletFactory {
	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		String state=request.getParameter("state");
		String id=request.getParameter("id");
		String vehicleId=request.getParameter("vehicleId");
		VehicleRecordDaoImp vehicleRecordDaoImp = new VehicleRecordDaoImp();
		Boolean result = vehicleRecordDaoImp.updateData(id,state,vehicleId);
		Map<String, String> map = new HashMap<String, String>();
		if (result) {
			map.put("result", "success");
			map.put("data", "success");
		} else {
		    map.put("result", "fail");
		    map.put("data", "update_fail");
		}
		return map;
	}
}
