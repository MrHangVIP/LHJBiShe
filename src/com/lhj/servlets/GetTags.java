package com.lhj.servlets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhj.Daos.TagDaoImp;
import com.lhj.beans.TagBean;
import com.lhj.servlets.base.BaseServletFactory;

import net.sf.json.JSONObject;

public class GetTags extends BaseServletFactory {
	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
			TagDaoImp tagDaoImp = new TagDaoImp();
			List<TagBean> tags = tagDaoImp.getDataList();
			JSONObject itemJson = JSONObject.fromObject(tags);
			map.put("result", "success");
			map.put("data", itemJson.toString());
		return map;
	}
}
