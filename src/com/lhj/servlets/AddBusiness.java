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
		businessBean.setUserPass(userPass);
		businessBean.setAddress(address);
		businessBean.setCompanyname(companyname);
		BusinessDaoImp businessDaoImp = new BusinessDaoImp();
		boolean isExist = businessDaoImp.emailCheck(email);
		String respose="";
		if (isExist) {
			respose="¸ÃÕËºÅÒÑ×¢²á£¡";
		} else {
			String businessId=Constant.getRandomCharAndNumr(11);
			businessBean.setBusinessId(businessId);
			boolean result = businessDaoImp.insertData(businessBean);
			if (result) {
				respose="×¢²á³É¹¦£¡";
			} else {
				respose="×¢²áÊ§°Ü£¡";
			}
		}
		PrintWriter out = response.getWriter();
		out.println("<html>");  
	    out.println("<head>");  
	    out.println("<title>"+"½Y¹û"+"</title>"); 
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
