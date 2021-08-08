package cookNameAndMaterialRegister;

import dao.CookMaterialDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

@WebServlet(urlPatterns = { "/cookNameAndMaterialRegister/insert" })
public class Insert extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            List<String> sendedParameters = Collections.list(request.getParameterNames());
            int sendedParameterNums = sendedParameters.size();
            String cookName = "";
            List<String> MaterialNames = new ArrayList<>();
            List<String> MaterialAmounts = new ArrayList<>();

            for (String sp : sendedParameters) {
                if (sp.equals("name")) {
                    cookName = request.getParameter(sp);
                } else if (sp.startsWith("material")) {
                    MaterialNames.add(request.getParameter(sp));
                } else if (sp.startsWith("amount")) {
                    MaterialAmounts.add(request.getParameter(sp));
                }
            }

            CookMaterialDAO cm_dao = new CookMaterialDAO();
            boolean updateSuccess = cm_dao.CookMaterialInsert(cookName, MaterialNames, MaterialAmounts,
                    sendedParameterNums);

            if (updateSuccess) {
                out.println("成功です");
            } else {
                out.println("失敗です");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
