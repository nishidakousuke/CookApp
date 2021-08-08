package process;

import dao.CookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

@WebServlet(urlPatterns={"/process/all"})
public class All extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            CookDAO dao = new CookDAO();
            List<String> CookingNames = dao.search();

            request.setAttribute("CookingNames", CookingNames);

            request.getRequestDispatcher("cookingName.jsp").forward(request, response);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
