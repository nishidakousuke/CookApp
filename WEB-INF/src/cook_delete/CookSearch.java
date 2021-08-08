package cook_delete;

import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

@WebServlet(urlPatterns = { "/cook_delete/search" })
public class CookSearch extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            CookDAO dao = new CookDAO();
            List<String> cooks = dao.search();

            request.setAttribute("cooks", cooks);
            request.getRequestDispatcher("cook_delete.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
