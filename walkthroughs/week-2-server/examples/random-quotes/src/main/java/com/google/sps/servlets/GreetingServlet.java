package com.google.sps.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main-greet")
public final class GreetingServlet extends HttpServlet {
    
    private List<String> greets;

    @Override
    public void init() {
        greets = new ArrayList<>();
        greets.add("Hello Lorenzo");
        greets.add("Hello Google SPS member!");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String greet = greets.get((int) (Math.random() * greets.size()));

    response.setContentType("text/html;");
    response.getWriter().println(greet);
  }
}
