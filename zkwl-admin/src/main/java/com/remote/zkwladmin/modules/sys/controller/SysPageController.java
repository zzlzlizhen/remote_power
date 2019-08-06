package com.remote.zkwladmin.modules.sys.controller;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 系统页面视图

 */
@Controller
public class SysPageController {
	
	@RequestMapping("modules/{module}/{url}.html")
	public String module(@PathVariable("module") String module, @PathVariable("url") String url, HttpServletRequest request){
		Map<String,String[]> map = request.getParameterMap();
		if(map != null && map.size()>0){
			for(String key:map.keySet()){
				String value[] = map.get(key);
				if(value != null){
					request.setAttribute(key,value[0]);
				}
			}
		}
		return "modules/" + module + "/" + url;
	}

	@RequestMapping(value = {"/", "index.html"})
	public String index(HttpServletRequest request){
		Map<String,String[]> map = request.getParameterMap();
		if(map != null && map.size()>0){
			for(String key:map.keySet()){
				String value[] = map.get(key);
				if(value != null){
					request.setAttribute(key,value[0]);
				}
			}
		}
		return "index";
	}

	@RequestMapping("index1.html")
	public String index1(){
		return "index1";
	}

	@RequestMapping("login.html")
	public String login(){
		return "login";
	}

	@RequestMapping("main.html")
	public String main(){
		return "main";
	}

	@RequestMapping("404.html")
	public String notFound(){
		return "404";
	}

}
