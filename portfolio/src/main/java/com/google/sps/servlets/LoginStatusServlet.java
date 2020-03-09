package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/status")
public class LoginStatusServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    String userStatus;
    UserService userService = UserServiceFactory.getUserService();
    if (userService.isUserLoggedIn()) {
      userStatus = getUserStatus(userService.isUserLoggedIn());
      String urlToRedirectToAfterUserLogsOut = "/";
      String logoutUrl = userService.createLogoutURL(urlToRedirectToAfterUserLogsOut);

      response.getWriter().println("<h3>Status: " + userStatus + "</h3>");
    } else {
      userStatus = getUserStatus(!(userService.isUserLoggedIn()));
      String urlToRedirectToAfterUserLogsIn = "/";
      String loginUrl = userService.createLoginURL(urlToRedirectToAfterUserLogsIn);

      response.getWriter().println("<h3>Status: " + userStatus + "</h3>");
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      UserService userService = UserServiceFactory.getUserService();
    if (!userService.isUserLoggedIn()) {
      response.sendRedirect("/status");
      return;
    }

    String status = request.getParameter("status");
    boolean state = !(userService.isUserLoggedIn());

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Entity entity = new Entity("UserStatus");
    entity.setProperty("state", state);
    entity.setProperty("status", status);
    datastore.put(entity);
    response.sendRedirect("/status");
  }

  private String getUserStatus(boolean state) {
    String status = "";
    if (state == false) {
      status = "Failed";
    } else {
      status = "Logged In";
    }
    return status;
  }
}

