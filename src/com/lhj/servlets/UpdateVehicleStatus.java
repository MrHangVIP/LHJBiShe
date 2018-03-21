package com.lhj.servlets;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhj.Daos.BusinessDaoImp;
import com.lhj.Daos.StatusDaoImp;
import com.lhj.Daos.UserDaoImp;
import com.lhj.beans.BusinessBean;
import com.lhj.beans.UserBean;
import com.lhj.servlets.base.BaseServletFactory;

import net.sf.json.JSONObject;

public class UpdateVehicleStatus extends BaseServletFactory {
	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		String statusId = request.getParameter("statusId");
		String statusType = request.getParameter("statusType");
		String vehicleId = request.getParameter("vehicleId");
		Map<String, String> map = new HashMap<String, String>();
			StatusDaoImp statusDaoImp = new StatusDaoImp();
			Boolean result = statusDaoImp.updateData(Integer.parseInt(statusId),statusType,Integer.parseInt(vehicleId));
			if(result){
				map.put("result", "success");
				map.put("data","update_success");
			}else{
				map.put("result", "fail");
				map.put("data","update_fail");
			}
		
		return map;
	}
}
