/**
 * 
 */
package com.lhj.servlets;

import java.util.ArrayList;
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
		String vehicleId = request.getParameter("vehicleId");
		Map<String, String> map = new HashMap<String, String>();
		VehicleDaoImp vehicleDaoImp = new VehicleDaoImp();
		List<VehicleBean> result=new ArrayList<>();
		if(vehicleId!=null){
			result=vehicleDaoImp.getDataLists(Integer.parseInt(typeId),businessId,Integer.parseInt(vehicleId));;
		}else if(Integer.parseInt(typeId)==1){
			result=vehicleDaoImp.getDataLists(businessId);
		}else{
			result=vehicleDaoImp.getDataLists(Integer.parseInt(typeId),businessId);;
		}
		JSONArray itemJson = JSONArray.fromObject(result);
		map.put("result", "success");
		map.put("data", itemJson.toString());
		return map;
	}
	@Override
	protected boolean tokenChecked(String userPhone, String token) {
		// TODO Auto-generated method stub
		return true;
	}
}
