/**
 * 
 */
package com.lhj.servlets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhj.Daos.VehicleDaoImp;
import com.lhj.beans.VehicleBean;
import com.lhj.servlets.base.BaseServletFactory;

import net.sf.json.JSONArray;

/**
 * @author moram
 *
 */
public class GetVehicleData extends BaseServletFactory {

	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		String businessId = request.getParameter("businessId");
		String typeId = request.getParameter("typeId");
		Map<String, String> map = new HashMap<String, String>();
		VehicleDaoImp vehicleDaoImp = new VehicleDaoImp();
		List<VehicleBean> result = vehicleDaoImp.getDataList(businessId,typeId);
		JSONArray itemJson = JSONArray.fromObject(result);
		map.put("result", "success");
		map.put("data", itemJson.toString());
		return map;
	}

}
