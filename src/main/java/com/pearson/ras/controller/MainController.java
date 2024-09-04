package com.pearson.ras.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.logging.Logger;
import com.pearson.ras.domain.HeaderInfo;

@Controller
//@RequestMapping("/")
public class MainController implements ErrorController {

	Logger log = Logger.getLogger("com.pearson.ras.controllers.MainController");

	@RequestMapping("/")
	public ModelAndView homeScreen()
	{
		System.out.println("home screen api");
		ModelAndView loginmv = new ModelAndView();
		loginmv.setViewName("index");
		System.out.println("home screen api after");
		return loginmv;
	}

	@RequestMapping("/showHome")
	public ModelAndView showHome(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String ssoUserID = ReqParamHelper.getssoUserID(request);
		String ssoUserFirstName = ReqParamHelper.getUserFirstName(request);
		String ssoUserLastName = ReqParamHelper.getUserLastName(request);

		if (ssoUserID == "test") {
			ModelAndView loginmv = new ModelAndView();
			loginmv.setViewName("login");
			return loginmv;
		}
		log.info("userid: " + ssoUserID + " First Name: " + ssoUserFirstName + " last name: " + ssoUserLastName);
		System.out.println(
				"userid: " + ssoUserID + " First Name: " + ssoUserFirstName + " last name: " + ssoUserLastName);
		HeaderInfo headerInfo = new HeaderInfo();
		headerInfo.setUserFirstName(ssoUserFirstName);
		headerInfo.setUserLastName(ssoUserLastName);
		headerInfo.setUserId(ssoUserID);
		ModelAndView mv = new ModelAndView();
		mv.addObject("headerInfo", headerInfo);
		mv.setViewName("home");

		return mv;
	}
	/*@RequestMapping(name="/error")
	public String handleError(HttpServletRequest request) {
		Object status =
				request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				//return "error-404";
				return "error";
			}
			else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				//return "error-500";
				return "error";
			}
		}
		return "error";
	}*/
}
