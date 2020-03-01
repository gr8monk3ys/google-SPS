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
    list.add("This is really intersting");
    list.add("Hello world");
    list.add("I don't like this as much");

    String json = convertToJson(list);
    
    response.setContentType("application/json;");
    response.getWriter().println(json);
}

  private String convertToJson(ArrayList<String> comment) {
    String json = "{ \n";
    for(int i = 0; i < 3; i++){
        json += "\"comment\": ";
        json += "\"" + comment.get(i) + "\"";
        json += ", ";
        json += "\n";  
    }
    json += "}"
    return json;
}

  private String convertToJsonUsingGson(String comment) {
    Gson gson = new Gson();

    String json = gson.toJson(comment);
    return json;
  }
}
