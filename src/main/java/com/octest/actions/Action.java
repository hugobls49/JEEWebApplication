package com.octest.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public interface Action<T> {
	T execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
