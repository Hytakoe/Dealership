package selyutinrv.controller.web.servlet;

import selyutinrv.model.Customer;
import selyutinrv.model.builder.CustomerBuilder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SaleServlet", urlPatterns = "/sales/*")
public class SaleServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/") || pathInfo.equals("/list")) {
                showList(request, response);
            } else if (pathInfo.equals("/sell")) {
                showSellForm(request, response);
            } else if (pathInfo.equals("/by-customer")) {
                showByCustomer(request, response);
            } else if (pathInfo.equals("/by-vehicle")) {
                showByVehicle(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                forwardToJsp(request, response, "error.jsp");
            }
        } catch (Exception e) {
            setErrorAttribute(request, e.getMessage());
            forwardToJsp(request, response, "error.jsp");
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("sales", saleService.getAllSales());
        forwardToJsp(request, response, "sales/list.jsp");
    }

    private void showSellForm(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("availableVehicles", vehicleService.getAvailableVehicles());
        request.setAttribute("customers", customerService.getAllCustomers());
        forwardToJsp(request, response, "sales/sell.jsp");
    }

    private void showByCustomer(HttpServletRequest request, HttpServletResponse response) {
        Long customerId = getParameterAsLong(request, "id");
        if (customerId != null) {
            request.setAttribute("sales", saleService.getSalesByCustomer(customerId));
            request.setAttribute("customer", customerService.getCustomerById(customerId));
        }
        forwardToJsp(request, response, "sales/by-customer.jsp");
    }

    private void showByVehicle(HttpServletRequest request, HttpServletResponse response) {
        Long vehicleId = getParameterAsLong(request, "id");
        if (vehicleId != null) {
            request.setAttribute("sales", saleService.getSalesByVehicle(vehicleId));
            request.setAttribute("vehicle", vehicleService.getVehicleById(vehicleId));
        }
        forwardToJsp(request, response, "sales/by-vehicle.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        try {
            if ("sell".equals(action)) {
                sellVehicle(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                forwardToJsp(request, response, "error.jsp");
            }
        } catch (Exception e) {
            setErrorAttribute(request, e.getMessage());
            forwardToJsp(request, response, "error.jsp");
        }
    }

    private void sellVehicle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long vehicleId = Long.parseLong(request.getParameter("vehicleId"));
        String customerOption = request.getParameter("customerOption");

        Customer customer;

        if ("existing".equals(customerOption)) {
            Long customerId = Long.parseLong(request.getParameter("customerId"));
            customer = customerService.getCustomerById(customerId);
        } else {
            customer = new CustomerBuilder()
                    .fullName(request.getParameter("fullName"))
                    .age(Integer.parseInt(request.getParameter("age")))
                    .gender(request.getParameter("gender"))
                    .phone(request.getParameter("phone"))
                    .email(request.getParameter("email"))
                    .address(request.getParameter("address"))
                    .build();
        }

        saleService.sellVehicle(vehicleId, customer);
        redirectTo(response, request.getContextPath() + "/sales");
    }
}