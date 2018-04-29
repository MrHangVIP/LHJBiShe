package com.lhj.servlets;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhj.Daos.UserDaoImp;
import com.lhj.servlets.base.BaseServletFactory;

/**
 * 完善个人资料
 * @author Songzhihang
 *
 */
public class FinishUserInfo extends BaseServletFactory {
	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		String userPhone=request.getParameter("userPhone");
		String token=request.getParameter("token");
		String businessid=request.getParameter("businessId");
		String trueName=request.getParameter("trueName");
		String sex=request.getParameter("sex");
		String birth=request.getParameter("birth");
		String licence=request.getParameter("licence");
		String level=request.getParameter("level");
		Map<String, String> map = new HashMap<String, String>();
		if(!tokenChecked(userPhone, token)){
			map=getJsonMap();
		}else{
			UserDaoImp usermodel=new UserDaoImp();
			boolean  update=usermodel.finishUserInfo(businessid,userPhone, trueName,sex,birth,licence,level);
			if(update){
				map.put("result", "success");
				map.put("data",  "");
			}else{
				map.put("result", "fail");
				map.put("data", "update fail");
			}
		}
		return map;
	}
}
