package process;

import dao.ProcessDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

@WebServlet(urlPatterns = { "/process/process_register" })
public class ProcessRegister extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            List<String> collections = Collections.list(request.getParameterNames());
            String name = "";
            List<String> processes = new ArrayList<>();
            List<String> times = new ArrayList<>();

            for (String c : collections) {
                if (c.equals("cook")) {
                    name = request.getParameter("cook");
                } else if (c.startsWith("process")) {
                    processes.add(request.getParameter(c));
                } else if (c.startsWith("time")) {
                    times.add(request.getParameter(c));
                }
            }

            ProcessDAO dao = new ProcessDAO();
            int line = dao.insert(name, processes, times);

            out.println(line);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
