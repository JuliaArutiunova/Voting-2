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

public class VotingServlet extends HttpServlet {

    public static final String USERNAME_PARAMETER = "firstname";
    public static final String ARTIST_PARAMETER = "artist";
    public static final String GENRE_PARAMETER = "genre";
    public static final String COMMENT_PARAMETER = "txtpole";
    private final static String VOTED_HEADER_NAME = "voted";

    private final static String VOTING_FORM_PATH = "/template/votingForm.jsp";
    private final static String MESSAGE_PAGE_PATH = "/template/message.jsp";
    private final static String ACCEPTED_PAGE_PATH = "/template/accepted.jsp";
    private final static String ERROR_PAGE_PATH = "/template/voteError.jsp";

    private final static String VOTED_MESSAGE = "Вы уже проголосовали";


    IArtistService artistService = ServiceFactory.getArtistService();
    IGenreService genreService = ServiceFactory.getGenreService();
    IVotingService votingService = ServiceFactory.getVotingService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       req.setAttribute("artists", artistService.get());
       req.setAttribute("genres", genreService.get());
       req.getRequestDispatcher(VOTING_FORM_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (VOTED_HEADER_NAME.equalsIgnoreCase(cookie.getName())) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    req.setAttribute("message",VOTED_MESSAGE);
                    req.getRequestDispatcher(MESSAGE_PAGE_PATH).forward(req,resp);
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
