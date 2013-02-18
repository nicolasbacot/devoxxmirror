package com.devoxx.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JSONPFilter extends HttpFilter {

	@Override
	public void doHttpFilter(HttpServletRequest httpRequest,
			ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		if (isJSONPRequest(httpRequest)) {
			ServletOutputStream out = response.getOutputStream();

			out.println(getCallbackParameter(httpRequest) + "(");
			chain.doFilter(httpRequest, response);
			out.println(");");

			response.setContentType("text/javascript");
		} else {
			chain.doFilter(httpRequest, response);
		}
	}

	private String getCallbackParameter(HttpServletRequest httpRequest) {
		return httpRequest.getParameter("callback");
	}

	private boolean isJSONPRequest(HttpServletRequest httpRequest) {
		String callbackMethod = getCallbackParameter(httpRequest);
		return (callbackMethod != null && callbackMethod.length() > 0);
	}
}