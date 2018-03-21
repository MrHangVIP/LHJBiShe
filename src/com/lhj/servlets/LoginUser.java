/**
 * 
 */
package com.lhj.servlets;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class LoginUser extends BaseServletFactory {

	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		String userPhone = request.getParameter("userPhone");
		String userPass = request.getParameter("userPass");
		String businessId = request.getParameter("businessId");
		String mac = request.getParameter("MAC");
		UserDaoImp usermodel = new UserDaoImp();
		UserBean userbean = usermodel.login(businessId,userPhone, userPass);
		Map<String, String> map = new HashMap<String, String>();
		if (userbean == null) {
			map.put("result", "fail");
			map.put("data", "");
		} else {
			new TokenDaoImp().deleteData(userPhone);
			JSONObject itemJson = JSONObject.fromObject(userbean);
			String token = Constant.productToken(mac);
			if (!new TokenDaoImp().insertData(new TokenBean(token, userPhone))) {
				new TokenDaoImp().insertData(new TokenBean(token, userPhone));
			}
			map.put("result", token);
			map.put("data", itemJson.toString());
		}

		return map;
	}

}
