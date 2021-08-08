package cook_delete;

import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

@WebServlet(urlPatterns = { "/cook_delete/delete" })
public class CookDelete extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String cook_name = request.getParameter("cook_name");
            CookDAO dao = new CookDAO();
            int line = dao.delete(cook_name);
            out.println(line);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
