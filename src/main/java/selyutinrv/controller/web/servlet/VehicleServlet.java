package selyutinrv.controller.web.servlet;

import selyutinrv.model.BodyType;
import selyutinrv.model.Car;
import selyutinrv.model.Truck;
import selyutinrv.model.TruckType;
import selyutinrv.model.Vehicle;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VehicleServlet", urlPatterns = "/vehicles/*")
public class VehicleServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            request.setAttribute("vehicles", vehicleService.getAllVehicles());
            request.setAttribute("availableVehicles", vehicleService.getAvailableVehicles());
            forwardToJsp(request, response, "vehicles/list.jsp");

        } else if (pathInfo.equals("/add")) {
            request.setAttribute("bodyTypes", BodyType.values());
            request.setAttribute("truckTypes", TruckType.values());
            forwardToJsp(request, response, "vehicles/add.jsp");

        } else if (pathInfo.matches("/\\d+")) {
            Long id = Long.parseLong(pathInfo.substring(1));
            Vehicle vehicle = vehicleService.getVehicleById(id);
            request.setAttribute("vehicle", vehicle);
            forwardToJsp(request, response, "vehicles/view.jsp");

        } else if (pathInfo.equals("/edit")) {
            Long id = getParameterAsLong(request, "id");
            if (id != null) {
                Vehicle vehicle = vehicleService.getVehicleById(id);
                request.setAttribute("vehicle", vehicle);
                request.setAttribute("bodyTypes", BodyType.values());
                request.setAttribute("truckTypes", TruckType.values());
                forwardToJsp(request, response, "vehicles/edit.jsp");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                forwardToJsp(request, response, "error.jsp");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            forwardToJsp(request, response, "error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo();
        String action = request.getParameter("action");

        try {
            if ("add".equals(action) || pathInfo.equals("/add")) {
                addVehicle(request, response);
            } else if ("update".equals(action)) {
                updateVehicle(request, response);
            } else if ("delete".equals(action)) {
                deleteVehicle(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            forwardToJsp(request, response, "error.jsp");
        }
    }

    private void addVehicle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String type = request.getParameter("vehicleType");

        if ("CAR".equals(type)) {
            Car car = new Car();
            car.setBrand(request.getParameter("brand"));
            car.setModel(request.getParameter("model"));
            car.setYear(Integer.parseInt(request.getParameter("year")));
            car.setPrice(Double.parseDouble(request.getParameter("price")));
            car.setColor(request.getParameter("color"));
            car.setBodyType(BodyType.valueOf(request.getParameter("bodyType")));
            car.setDoorCount(Integer.parseInt(request.getParameter("doorCount")));
            car.setPassengerCapacity(Integer.parseInt(request.getParameter("passengerCapacity")));
            car.setTransmission(request.getParameter("transmission"));
            car.setEngineVolume(Double.parseDouble(request.getParameter("engineVolume")));
            car.setFuelType(request.getParameter("fuelType"));
            vehicleService.addVehicle(car);

        } else if ("TRUCK".equals(type)) {
            Truck truck = new Truck();
            truck.setBrand(request.getParameter("brand"));
            truck.setModel(request.getParameter("model"));
            truck.setYear(Integer.parseInt(request.getParameter("year")));
            truck.setPrice(Double.parseDouble(request.getParameter("price")));
            truck.setColor(request.getParameter("color"));
            truck.setTruckType(TruckType.valueOf(request.getParameter("truckType")));
            truck.setLoadCapacity(Double.parseDouble(request.getParameter("loadCapacity")));
            truck.setAxleCount(Integer.parseInt(request.getParameter("axleCount")));
            truck.setCargoVolume(Double.parseDouble(request.getParameter("cargoVolume")));
            truck.setHasTrailerHitch(Boolean.parseBoolean(request.getParameter("hasTrailerHitch")));
            truck.setBodyMaterial(request.getParameter("bodyMaterial"));
            vehicleService.addVehicle(truck);
        }

        response.sendRedirect(request.getContextPath() + "/vehicles");
    }

    private void updateVehicle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.parseLong(request.getParameter("id"));
        Vehicle existing = vehicleService.getVehicleById(id);

        if (existing instanceof Car) {
            Car car = (Car) existing;
            String brand = request.getParameter("brand");
            if (brand != null && !brand.isEmpty()) car.setBrand(brand);

            String model = request.getParameter("model");
            if (model != null && !model.isEmpty()) car.setModel(model);

            String year = request.getParameter("year");
            if (year != null && !year.isEmpty()) car.setYear(Integer.parseInt(year));

            String price = request.getParameter("price");
            if (price != null && !price.isEmpty()) car.setPrice(Double.parseDouble(price));

            String color = request.getParameter("color");
            if (color != null && !color.isEmpty()) car.setColor(color);

            vehicleService.updateVehicle(car);

        } else if (existing instanceof Truck) {
            Truck truck = (Truck) existing;
            String brand = request.getParameter("brand");
            if (brand != null && !brand.isEmpty()) truck.setBrand(brand);

            String model = request.getParameter("model");
            if (model != null && !model.isEmpty()) truck.setModel(model);

            vehicleService.updateVehicle(truck);
        }

        response.sendRedirect(request.getContextPath() + "/vehicles");
    }

    private void deleteVehicle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long id = Long.parseLong(request.getParameter("id"));
        vehicleService.deleteVehicle(id);
        response.sendRedirect(request.getContextPath() + "/vehicles");
    }
}