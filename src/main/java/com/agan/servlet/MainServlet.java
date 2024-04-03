package com.agan.servlet;

import com.agan.controller.PostController;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainServlet extends HttpServlet {
  private static final String API_POSTS_PATH = "/api/posts";
  private static final String API_POSTS_ID_PATH_REGEX = "/api/posts/\\d+";
  private PostController controller;

  @Override
  public void init() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.agan");
    controller = context.getBean(PostController.class);
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) {
    try {
      final String path = req.getRequestURI();
      final String method = req.getMethod();
      // primitive routing
      if (method.equals("GET") && path.equals(API_POSTS_PATH)) {
        controller.all();
        return;
      }
      if (method.equals("GET") && path.matches(API_POSTS_ID_PATH_REGEX)) {
        // easy way
        final long id = Long.parseLong(path.substring(path.lastIndexOf("/")));
        controller.getById(id);
        return;
      }
      if (method.equals("POST") && path.equals(API_POSTS_PATH)) {
        controller.save();
        return;
      }
      if (method.equals("DELETE") && path.matches(API_POSTS_ID_PATH_REGEX)) {
        // easy way
        final long id = Long.parseLong(path.substring(path.lastIndexOf("/")));
        controller.removeById(id);
        return;
      }
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}

