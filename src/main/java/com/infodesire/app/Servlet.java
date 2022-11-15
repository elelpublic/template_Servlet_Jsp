package com.infodesire.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

public class Servlet extends HttpServlet {

  private static Logger logger = LoggerFactory.getLogger( "Server" );

  private static AtomicInteger sessionNumbers = new AtomicInteger();

  public void init() throws ServletException {
    logger.info( "Servlet init" );
  }

  public void doGet( HttpServletRequest request, HttpServletResponse response)
    throws ServletException {

    try {
      sendPage( request, response );
    }
    catch( IOException ex ) {
      throw new ServletException( ex );
    }

  }

  public void doPost( HttpServletRequest request, HttpServletResponse response)
    throws ServletException {

    String query = request.getQueryString();
    if( query != null && query.equals( "cmd=logout" ) ) {
      HttpSession session = request.getSession();
      session.invalidate();
      try {
        response.sendRedirect( "hello" );
        return;
      }
      catch( IOException ex ) {
        throw new ServletException( ex );
      }
    }

    String value = request.getParameter( "value" );
    if( value != null ) {

      HttpSession session = request.getSession();
      session.setAttribute( "value", value );

    }

    try {
      sendPage( request, response );
    }
    catch( IOException ex ) {
      throw new ServletException( ex );
    }

  }


  private void sendPage( HttpServletRequest request, HttpServletResponse response ) throws IOException {

    // Set response content type
    response.setContentType("text/html");

    // Actual logic goes here.
    PrintWriter out = response.getWriter();
    out.println("<h1>Hello World</h1>");
    out.println("Sent from servlet");

    HttpSession session = request.getSession();

    Integer sessionNumber = (Integer) session.getAttribute( "session-number" );
    if( sessionNumber == null ) {
      sessionNumber = sessionNumbers.incrementAndGet();
      session.setAttribute( "session-number", sessionNumber );
    }

    out.println( "<table border=1>" );

    printRow( out, "session", session );
    printRow( out, "session id", session.getId() );
    printRow( out, "session number", sessionNumber );
    printRow( out, "session timestamp", new Date( session.getCreationTime() ) );
    printRow( out, "query", request.getQueryString() );
    printRow( out, "session last access", new Date( session.getLastAccessedTime() ) );

    printRow( out, "&nbsp;", "" );
    printRow( out, "attributes", "" );

    int i = 0;
    for( Enumeration<String> names = session.getAttributeNames(); names.hasMoreElements(); ) {
      String name = names.nextElement();
      printRow( out, name, session.getAttribute( name ) );
      i++;
    }
    printRow( out, "attribute count", "" + i );

    out.println( "</table>" );

    out.println( "<p>" );
    out.println( "<form method=post>" );
    out.println( "<label for=value>Set a value</label>" );
    String value = (String) session.getAttribute( "value" );
    out.println( "<input type=text name=value id=value value=\"" + ( value == null ? "" : value ) + "\" />" );
    out.println( "<button type=submit>Save</button>" );
    out.println( "</form>" );
    out.println( "</p>" );

    out.println( "<p>" );
    out.println( "<form method=post action=hello?cmd=logout>" );
    out.println( "<button type=submit>Logout</button>" );
    out.println( "</form>" );
    out.println( "</p>" );

  }

  private void printRow( PrintWriter out, String name, Object value ) {
    out.println( "<tr>" );
    out.println( "<td>" );
    out.println( name );
    out.println( "</td>" );
    out.println( "<td>" );
    out.println( value == null ? "" : value );
    out.println( "</td>" );
    out.println( "</tr>" );
  }

  public void destroy() {
    logger.info( "Servlet destroy" );
  }

}
