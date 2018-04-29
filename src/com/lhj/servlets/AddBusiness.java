/**
 * 
 */
package com.lhj.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhj.Daos.BusinessDaoImp;
import com.lhj.beans.BusinessBean;
import com.lhj.servlets.base.BaseServletFactory;
import com.lhj.utils.Constant;

/**
 * @author moram
 *
 */
public class AddBusiness extends BaseServletFactory {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String userPass = request.getParameter("userPass");
		String companyname = request.getParameter("companyname");
		String address = request.getParameter("address");
		BusinessBean businessBean = new BusinessBean();
		businessBean.setEmail(email);
		businessBean.setPassWord(userPass);
		businessBean.setAddress(address);
		businessBean.setCompanyname(companyname);
		String respose="";
		if(!Constant.isEmail(email)){
			respose="娉ㄥ唽澶辫触锛侀偖绠卞湴鍧�涓嶅悎娉曪紒";
		}else{
			BusinessDaoImp businessDaoImp = new BusinessDaoImp();
			boolean isExist = businessDaoImp.emailCheck(email);
			if (isExist) {
				respose="娉ㄥ唽澶辫触锛侀偖绠卞凡瀛樺湪锛�";
			} else {
				String businessId=Constant.getRandomCharAndNumr(11);
				businessBean.setBusinessId(businessId);
				boolean result = businessDaoImp.insertData(businessBean);
				if (result) {
					respose="娉ㄥ唽鎴愬姛锛佹偍鐨勪紒涓氬彿ID鏄細"+businessId+" 銆傝璁颁綇锛�";
				} else {
					respose="娉ㄥ唽澶辫触锛�";
				}
			}
		}
		PrintWriter out = response.getWriter();
		out.println("<html>");  
	    out.println("<head>");  
	    out.println("<title>"+"娉ㄥ唽"+"</title>"); 
	    out.print("<meta http-equiv=content-type content=text/html; charset=UTF-8>");
	    out.println("</head>");  
	    out.println("<body>");  
	    out.println("<h1>"+respose+"</h1>");  
	    out.println("</body>");  
	    out.println("</html>");  
		System.out.println("json  :" + respose);
		out.close();
	}
	
	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}

}
