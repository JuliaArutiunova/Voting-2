package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.exception.DeleteParticipantException;
import by.it_academy.jd2.service.api.IArtistService;
import by.it_academy.jd2.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

import static by.it_academy.jd2.util.FilePathUtil.ARTIST_JSP_PATH;

public class ArtistServlet extends HttpServlet {

    private final IArtistService artistService = ServiceFactory.getArtistService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Long, String> artists = artistService.get();
        req.setAttribute("deleteArtists", artists);

        req.getRequestDispatcher(ARTIST_JSP_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String createArtist = req.getParameter("CreateArtist");
        String deleteArtists = req.getParameter("deleteArtist");

        if (!createArtist.isBlank()) {
            try {
                artistService.create(createArtist);
                resp.getWriter().write("новый артист с именем " + createArtist + " был создан");
            } catch (IllegalArgumentException e) {
                resp.getWriter().write(e.getMessage());
            }
        }

        if (!deleteArtists.isBlank()) {
            try {
                artistService.delete(Long.valueOf(deleteArtists));
                resp.getWriter().write(deleteArtists + " был успешно удалён");
            } catch (IllegalArgumentException | DeleteParticipantException e) {
                resp.getWriter().write(e.getMessage());
            }

        }
    }
}
