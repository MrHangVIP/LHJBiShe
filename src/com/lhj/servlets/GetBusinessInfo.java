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

import net.sf.json.JSONObject;

public class GetBusinessInfo extends BaseServletFactory {
	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String passWord = request.getParameter("passWord");
		Map<String, String> map = new HashMap<String, String>();
			BusinessDaoImp biBusinessDaoImp = new BusinessDaoImp();
			BusinessBean businessBean = biBusinessDaoImp.login(email,passWord);
			if (businessBean == null) {
				map.put("result", "fail");
				map.put("data", "");
			} else {
				JSONObject itemJson = JSONObject.fromObject(businessBean);
				map.put("result", "success");
				map.put("data", itemJson.toString());
			}
		return map;
	}
	
	@Override
	protected boolean tokenChecked(String userPhone, String token) {
		// TODO Auto-generated method stub
		return true;
	}
}
