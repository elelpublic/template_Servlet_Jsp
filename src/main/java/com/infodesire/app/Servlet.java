package com.infodesire.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet extends HttpServlet {

  private static Logger logger = LoggerFactory.getLogger( "Server" );

  public void init() throws ServletException {
    logger.info( "Servlet init" );
  }

  public void doGet( HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    // Set response content type
    response.setContentType("text/html");

    // Actual logic goes here.
    PrintWriter out = response.getWriter();
    out.println("<h1>Hello World</h1>");
    out.println("Sent from servlet");
  }

  public void destroy() {
    logger.info( "Servlet destroy" );
  }

}
