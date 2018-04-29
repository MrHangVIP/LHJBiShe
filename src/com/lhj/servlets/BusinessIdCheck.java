/**
 * 
 */
package com.lhj.servlets;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhj.Daos.BusinessDaoImp;
import com.lhj.Daos.TokenDaoImp;
import com.lhj.Daos.UserDaoImp;
import com.lhj.beans.TokenBean;
import com.lhj.beans.UserBean;
import com.lhj.servlets.base.BaseServletFactory;
import com.lhj.utils.Constant;

import net.sf.json.JSONObject;

/**
 * @author moram
 *
 */
public class BusinessIdCheck extends BaseServletFactory {

	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		String businessId = request.getParameter("businessId");
		BusinessDaoImp businessDaoImp = new BusinessDaoImp();
		Boolean exist = businessDaoImp.checkBusinessId(businessId);
		Map<String, String> map = new HashMap<String, String>();
		if (exist) {
			map.put("result", "success");
			map.put("data", "exist");
		} else {
			map.put("result", "fail");
			map.put("data", "not_exist");
		}
		return map;
	}

}
