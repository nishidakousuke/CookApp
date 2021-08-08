package cooking;

import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

@WebServlet(urlPatterns = { "/cooking/process_search" })
public class ProcessSearch extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String cooking_name = request.getParameter("cooking");
            ProcessDAO dao = new ProcessDAO();
            List<String> processes = new ArrayList<>();
            List<String> times = new ArrayList<>();

            dao.search(cooking_name, processes, times);

            request.setAttribute("processes", processes);
            request.setAttribute("times", times);

            request.getRequestDispatcher("cookingNow.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
