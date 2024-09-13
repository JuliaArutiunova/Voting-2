package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.service.api.IFormManagerService;
import by.it_academy.jd2.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

public class AddArtistServlet extends HttpServlet {
    private final IFormManagerService formManagerService = ServiceFactory.getFormManagerService();

    private static final String ADD_ARTIST_PATH = "/template/formManager/addArtist.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ADD_ARTIST_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String newArtist = req.getParameter("newArtist");

        Writer writer = resp.getWriter();

        try {
            formManagerService.createArtist(newArtist);
            writer.write("Артист " + newArtist + " успешно добавлен");
        } catch (IllegalArgumentException e) {
            writer.write(e.getMessage());
        }

    }
}
