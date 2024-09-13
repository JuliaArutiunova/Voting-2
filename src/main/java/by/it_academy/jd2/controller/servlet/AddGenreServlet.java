package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.service.api.IFormManagerService;
import by.it_academy.jd2.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

public class AddGenreServlet extends HttpServlet {
    private final IFormManagerService formManagerService = ServiceFactory.getFormManagerService();

    private static final String ADD_GENRE_PATH = "/template/formManager/addGenre.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ADD_GENRE_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String newGenre = req.getParameter("newGenre");

        Writer writer = resp.getWriter();

        try {
            formManagerService.createGenre(newGenre);
            writer.write("Жанр " + newGenre + " успешно добавлен");
        } catch (IllegalArgumentException e) {
            writer.write(e.getMessage());
        }

    }
}
