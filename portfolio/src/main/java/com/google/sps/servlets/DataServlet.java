// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {

    ArrayList<String> list = new ArrayList<String>();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String json = convertToJson(list);
    response.setContentType("application/json;");
    response.getWriter().println(json);
}

@Override
public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
    String comment = getComment(request);

    response.setContentType("text/html;");
    response.getWriter().println(comment);
}

private String getComment(HttpServletRequest request) {
    String comment = request.getParameter("comment");
    return comment;
}

  private String convertToJson(ArrayList<String> data) {
    String json = "{ \n";
    json += "\"comment\": ";
    json += "\"" + data + "\"";
    json += ", ";
    json += "\n";  
    json += "}";
    return json;
}

  private String convertToJsonUsingGson(String data) {
    Gson gson = new Gson();

    String json = gson.toJson(data);
    return json;
  }
}
