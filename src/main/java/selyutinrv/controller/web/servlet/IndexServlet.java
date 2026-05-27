package selyutinrv.controller.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "IndexServlet", urlPatterns = {"/index", "/"})
public class IndexServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        long availableCount = vehicleService.getAvailableVehicles().size();
        long salesCount = saleService.getAllSales().size();
        long customersCount = customerService.getAllCustomers().size();

        request.setAttribute("availableCount", availableCount);
        request.setAttribute("salesCount", salesCount);
        request.setAttribute("customersCount", customersCount);

        forwardToJsp(request, response, "index.jsp");
    }
}