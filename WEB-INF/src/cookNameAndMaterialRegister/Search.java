package test;

import bean.*;
import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

@WebServlet(urlPatterns = { "/test/search" })
public class Search extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String keyword = request.getParameter("keyword");
            MaterialDAO dao = new MaterialDAO();
            Map<String, String> MaterialMap = dao.search(keyword);

            for (String MaterialName : MaterialMap.keySet()) {
                String MaterialAmount = MaterialMap.get(MaterialName);
                out.println(MaterialName + ":" + MaterialAmount);
                out.println(":");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
