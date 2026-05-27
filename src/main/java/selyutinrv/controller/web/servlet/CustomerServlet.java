package selyutinrv.controller.web.servlet;

import selyutinrv.model.Customer;
import selyutinrv.model.builder.CustomerBuilder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers/*")
public class CustomerServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/") || pathInfo.equals("/list")) {
                showList(request, response);
            } else if (pathInfo.equals("/add")) {
                showAddForm(request, response);
            } else if (pathInfo.equals("/edit")) {
                showEditForm(request, response);
            } else if (pathInfo.matches("/\\d+")) {
                showDetails(request, response, Long.parseLong(pathInfo.substring(1)));
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
        request.setAttribute("customers", customerService.getAllCustomers());
        forwardToJsp(request, response, "customers/list.jsp");
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) {
        forwardToJsp(request, response, "customers/add.jsp");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        Long id = getParameterAsLong(request, "id");
        if (id == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            forwardToJsp(request, response, "error.jsp");
            return;
        }

        Customer customer = customerService.getCustomerById(id);
        request.setAttribute("customer", customer);
        forwardToJsp(request, response, "customers/edit.jsp");
    }

    private void showDetails(HttpServletRequest request, HttpServletResponse response, Long id) {
        Customer customer = customerService.getCustomerById(id);
        request.setAttribute("customer", customer);
        forwardToJsp(request, response, "customers/view.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                addCustomer(request, response);
            } else if ("update".equals(action)) {
                updateCustomer(request, response);
            } else if ("delete".equals(action)) {
                deleteCustomer(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                forwardToJsp(request, response, "error.jsp");
            }
        } catch (Exception e) {
            setErrorAttribute(request, e.getMessage());
            forwardToJsp(request, response, "error.jsp");
        }
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Customer customer = new CustomerBuilder()
                .fullName(request.getParameter("fullName"))
                .age(Integer.parseInt(request.getParameter("age")))
                .gender(request.getParameter("gender"))
                .phone(request.getParameter("phone"))
                .email(request.getParameter("email"))
                .address(request.getParameter("address"))
                .build();

        customerService.registerCustomer(customer);
        redirectTo(response, request.getContextPath() + "/customers");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.parseLong(request.getParameter("id"));
        Customer existing = customerService.getCustomerById(id);

        Customer updatedCustomer = new Customer(
                id,
                getParamOrDefault(request, "fullName", existing.getFullName()),
                Integer.parseInt(getParamOrDefault(request, "age", String.valueOf(existing.getAge()))),
                getParamOrDefault(request, "gender", existing.getGender()),
                getParamOrDefault(request, "phone", existing.getPhone()),
                getParamOrDefault(request, "email", existing.getEmail()),
                getParamOrDefault(request, "address", existing.getAddress())
        );

        customerService.updateCustomer(updatedCustomer);
        redirectTo(response, request.getContextPath() + "/customers");
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.parseLong(request.getParameter("id"));
        // Проверяем, есть ли у покупателя продажи
        boolean hasSales = !saleService.getSalesByCustomer(id).isEmpty();

        if (hasSales) {
            request.setAttribute("error", "Нельзя удалить покупателя с существующими продажами");
            showList(request, response);
        } else {
            request.setAttribute("error", "Удаление покупателей временно недоступно");
            showList(request, response);
        }
    }

    private String getParamOrDefault(HttpServletRequest request, String param, String defaultValue) {
        String value = request.getParameter(param);
        return (value == null || value.isEmpty()) ? defaultValue : value;
    }
}