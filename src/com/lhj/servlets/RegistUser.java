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
import com.lhj.beans.BusinessBean;
import com.lhj.beans.UserBean;
import com.lhj.servlets.base.BaseServletFactory;

/**
 * @author moram
 *
 */
public class RegistUser extends BaseServletFactory {

	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		String userPhone = request.getParameter("userPhone");
		String userPass = request.getParameter("userPass");
		String businessId = request.getParameter("businessId");
		Map<String, String> map = new HashMap<String, String>();
		BusinessBean businessBean=new BusinessDaoImp().getBusinessInfo(businessId);
		if(businessBean==null){
			map.put("result", "fail");
			map.put("data", "not_exist");
			return map;
		}
		UserBean user = new UserBean();
		user.setUserPhone(userPhone);
		user.setUserPass(userPass);
		user.setBusinessId(businessId);
		UserDaoImp usermodel = new UserDaoImp();
		boolean isExist = usermodel.userPhoneChecked(userPhone,businessId);
		if (isExist) {
			map.put("result", "fail");
			map.put("data", "exist");
		} else {
			boolean result = usermodel.insertData(user);
			if (result) {
				map.put("result", "success");
			} else {
				map.put("result", "fail");
			}
		}
		return map;
	}

}
