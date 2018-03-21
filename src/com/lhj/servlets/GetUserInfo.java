package com.lhj.servlets;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhj.Daos.UserDaoImp;
import com.lhj.beans.UserBean;
import com.lhj.servlets.base.BaseServletFactory;

import net.sf.json.JSONObject;

public class GetUserInfo extends BaseServletFactory {
	private static final long serialVersionUID = 1L;
	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		String userPhone=request.getParameter("userPhone");
		String token=request.getParameter("token");
		String businessId=request.getParameter("businessId");
		Map<String, String> map = new HashMap<String, String>();
		if(!tokenChecked(userPhone, token)){
			map=getJsonMap();
		}else{
			UserDaoImp usermodel=new UserDaoImp();
			UserBean userInfo=usermodel.getUserInfo(userPhone,businessId);
			JSONObject itemJson = JSONObject.fromObject(userInfo);
			map.put("result", token);
			map.put("data",  itemJson.toString());
		}
		return map;
	}
}
