/**
 * 
 */
package com.lhj.servlets;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhj.Daos.BusinessDaoImp;
import com.lhj.Daos.UserDaoImp;
import com.lhj.Daos.VehicleDaoImp;
import com.lhj.beans.BusinessBean;
import com.lhj.beans.UserBean;
import com.lhj.beans.VehicleBean;
import com.lhj.servlets.base.BaseServletFactory;

/**
 * @author moram
 *
 */
public class AddVehicleInfo extends BaseServletFactory {

	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		String identity = request.getParameter("identity");
		String businessId = request.getParameter("businessId");
		String name = request.getParameter("name");
		String typeId = request.getParameter("typeId");
		String biref = request.getParameter("biref");
		String indexpicurl = request.getParameter("indexpicurl");
		String price = request.getParameter("price");
		String typeTitle = request.getParameter("typeTitle");
		String level = request.getParameter("level");
		Map<String, String> map = new HashMap<String, String>();
		Boolean isExist=new VehicleDaoImp().identityCheck(identity);
		if(isExist){
			map.put("result", "fail");
			map.put("data", "exist");
			return map;
		}
		VehicleBean vehicleBean = new VehicleBean();
		vehicleBean.setIdentity(identity);
		vehicleBean.setBusinessId(businessId);
		vehicleBean.setName(name);
		vehicleBean.setBiref(biref);
		vehicleBean.setIndexpicurl(indexpicurl);
		vehicleBean.setPrice(Double.parseDouble(price));
		vehicleBean.setTypeTitle(typeTitle);
		vehicleBean.setLevel(level);
		vehicleBean.setTypeId(Integer.parseInt(typeId));
		VehicleDaoImp vehicleDaoImp = new VehicleDaoImp();
		boolean result = vehicleDaoImp.insertData(vehicleBean);
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
