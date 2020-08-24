package kr.or.ddit.member.controller;

import java.io.IOError;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IController {
	public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
}
