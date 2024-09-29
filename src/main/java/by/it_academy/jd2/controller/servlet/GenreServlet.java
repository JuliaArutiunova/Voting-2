package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.exception.DeleteParticipantException;
import by.it_academy.jd2.service.api.IGenreService;
import by.it_academy.jd2.service.factory.GenreServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

import static by.it_academy.jd2.util.FilePathUtil.GENRE_JSP_PATH;
public class GenreServlet extends HttpServlet {

    private final IGenreService genreService = GenreServiceFactory.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Long, String> genres = genreService.get();
        req.setAttribute("deleteGenres", genres);

        req.getRequestDispatcher(GENRE_JSP_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String createGenre = req.getParameter("createGenre");
        String deleteGenres = req.getParameter("deleteGenre");

        if (!createGenre.isBlank()) {
            try {
                genreService.create(createGenre);
            } catch (IllegalArgumentException e) {
                resp.getWriter().write(e.getMessage());
            }
        }

        if (!deleteGenres.isBlank()) {
            try {
                genreService.delete(Long.valueOf(deleteGenres));
            } catch (IllegalArgumentException | DeleteParticipantException e) {
                resp.getWriter().write(e.getMessage());
            }
        }

    }
}
