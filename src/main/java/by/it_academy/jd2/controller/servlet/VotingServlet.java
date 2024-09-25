package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.dto.InfoFromClientDTO;
import by.it_academy.jd2.service.api.IArtistService;
import by.it_academy.jd2.service.api.IGenreService;
import by.it_academy.jd2.service.api.IVotingService;
import by.it_academy.jd2.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static by.it_academy.jd2.util.FilePathUtil.*;

public class VotingServlet extends HttpServlet {
    private static final String ARTISTS_ATTRIBUTE_NAME = "artists";
    private static final String GENRES_ATTRIBUTE_NAME = "genres";

    private static final String USERNAME_PARAMETER = "firstname";
    private static final String ARTIST_PARAMETER = "artist";
    private static final String GENRE_PARAMETER = "genre";
    private static final String COMMENT_PARAMETER = "txtpole";

    private final static String VOTED_HEADER_NAME = "voted";


    IArtistService artistService = ServiceFactory.getArtistService();
    IGenreService genreService = ServiceFactory.getGenreService();
    IVotingService votingService = ServiceFactory.getVotingService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       req.setAttribute(ARTISTS_ATTRIBUTE_NAME, artistService.get());
       req.setAttribute(GENRES_ATTRIBUTE_NAME, genreService.get());

       req.getRequestDispatcher(VOTING_FORM_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (VOTED_HEADER_NAME.equalsIgnoreCase(cookie.getName())) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write("Вы уже проголосовали");
                    return;
                }
            }
        }

        String userName = req.getParameter(USERNAME_PARAMETER);
        String artist = req.getParameter(ARTIST_PARAMETER);
        String[] genres = req.getParameterValues(GENRE_PARAMETER);
        String comment = req.getParameter(COMMENT_PARAMETER);

        try {
            votingService.create(InfoFromClientDTO.builder()
                    .setName(userName)
                    .setArtist(artist)
                    .setGenres(genres)
                    .setComment(comment)
                    .build());

            Cookie votingCookie = new Cookie(VOTED_HEADER_NAME, "true");
            votingCookie.setMaxAge(Math.toIntExact(TimeUnit.MINUTES.toSeconds(5)));
            resp.addCookie(votingCookie);

            req.setAttribute("name", userName);
            req.getRequestDispatcher(ACCEPTED_PAGE_PATH).forward(req, resp);

        } catch (IllegalArgumentException e) {

            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(ERROR_PAGE_PATH).forward(req, resp);
        }
    }
}
