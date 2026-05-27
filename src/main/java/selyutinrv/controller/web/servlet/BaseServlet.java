package selyutinrv.controller.web.servlet;

import selyutinrv.repository.impl.jdbc.JdbcCustomerRepository;
import selyutinrv.repository.impl.jdbc.JdbcSaleRepository;
import selyutinrv.repository.impl.jdbc.JdbcVehicleRepository;
import selyutinrv.service.CustomerService;
import selyutinrv.service.SaleService;
import selyutinrv.service.VehicleService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {

    protected VehicleService vehicleService;
    protected CustomerService customerService;
    protected SaleService saleService;

    @Override
    public void init() {
        vehicleService = new VehicleService(new JdbcVehicleRepository());
        customerService = new CustomerService(new JdbcCustomerRepository());
        saleService = new SaleService(
                new JdbcVehicleRepository(),
                new JdbcCustomerRepository(),
                new JdbcSaleRepository(new JdbcVehicleRepository(), new JdbcCustomerRepository())
        );
    }

    protected Long getParameterAsLong(HttpServletRequest request, String paramName) {
        String param = request.getParameter(paramName);
        if (param == null || param.isEmpty()) {
            return null;
        }
        try {
            return Long.parseLong(param);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    protected Integer getParameterAsInt(HttpServletRequest request, String paramName) {
        String param = request.getParameter(paramName);
        if (param == null || param.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    protected Double getParameterAsDouble(HttpServletRequest request, String paramName) {
        String param = request.getParameter(paramName);
        if (param == null || param.isEmpty()) {
            return null;
        }
        try {
            return Double.parseDouble(param);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    protected void forwardToJsp(HttpServletRequest request, HttpServletResponse response, String jspPath) {
        try {
            request.getRequestDispatcher("/WEB-INF/jsp/" + jspPath)
                    .forward(request, response);  // ← Правильно: request и response
        } catch (Exception e) {
            throw new RuntimeException("Error forwarding to JSP", e);
        }
    }
    protected void redirectTo(HttpServletResponse response, String path) {
        try {
            response.sendRedirect(path);
        } catch (Exception e) {
            throw new RuntimeException("Error redirecting", e);
        }
    }
    protected void setErrorAttribute(HttpServletRequest request, String errorMessage) {
        request.setAttribute("error", errorMessage);
    }
}