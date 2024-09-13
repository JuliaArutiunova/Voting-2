package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.service.api.IFormManagerService;
import by.it_academy.jd2.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

public class DeleteArtistServlet extends HttpServlet {
    private static final String DELETE_ARTIST_PATH = "/template/formManager/deleteArtist.jsp";
    private final IFormManagerService formManagerService = ServiceFactory.getFormManagerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("deleteArtists", formManagerService.getParticipants().getArtists());
        req.getRequestDispatcher(DELETE_ARTIST_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteArtist = req.getParameter("deleteArtist");

        Writer writer = resp.getWriter();

        try {
            writer.write("Артист " + formManagerService.deleteArtist(deleteArtist) + " был успешно удалён");
        } catch (IllegalArgumentException e) {
            writer.write(e.getMessage());
        }
    }
}
