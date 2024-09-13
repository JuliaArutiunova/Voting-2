package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.service.api.IFormManagerService;
import by.it_academy.jd2.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

public class DeleteGenreServlet extends HttpServlet {

    private static final String DELETE_GENRE_PATH = "/template/formManager/deleteGenre.jsp";

    private final IFormManagerService formManagerService = ServiceFactory.getFormManagerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("deleteGenres", formManagerService.getParticipants().getGenres());
        req.getRequestDispatcher(DELETE_GENRE_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteGenre = req.getParameter("deleteGenre");

        Writer writer = resp.getWriter();

        try {
            writer.write("Жанр " + formManagerService.deleteGenre(deleteGenre) + " был успешно удалён");
        } catch (IllegalArgumentException e) {
            writer.write(e.getMessage());
        }
    }
}
