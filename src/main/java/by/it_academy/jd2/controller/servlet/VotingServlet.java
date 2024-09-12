package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.dto.InfoFromClientDTO;
import by.it_academy.jd2.dto.ParticipantsDTO;
import by.it_academy.jd2.service.api.IFormManagerService;
import by.it_academy.jd2.service.api.IVotingService;
import by.it_academy.jd2.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

public class VotingServlet extends HttpServlet {

    public static final String USERNAME_PARAMETER = "firstname";
    public static final String ARTIST_PARAMETER = "artist";
    public static final String GENRE_PARAMETER = "genre";
    public static final String COMMENT_PARAMETER = "txtpole";
    private final static String VOTED_HEADER_NAME = "voted";

    IFormManagerService formManagerService = ServiceFactory.getFormManagerService();
    IVotingService votingService = ServiceFactory.getVotingService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ParticipantsDTO participants = formManagerService.getParticipants();

        req.setAttribute("artists", participants.getArtists());
        req.setAttribute("genres", participants.getGenres());
        req.getRequestDispatcher("/template/votingForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Writer writer = resp.getWriter();

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (VOTED_HEADER_NAME.equalsIgnoreCase(cookie.getName())) {
                    writer.write("<h3>Вы уже проголосовали</h3>");
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
            }
        }

        String userName = req.getParameter(USERNAME_PARAMETER);
        String artist = req.getParameter(ARTIST_PARAMETER);
        String[] genres = req.getParameterValues(GENRE_PARAMETER);
        String comment = req.getParameter(COMMENT_PARAMETER);

        req.setAttribute("name", userName);


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

            req.getRequestDispatcher("/template/accepted.jsp").forward(req, resp);


        } catch (IllegalArgumentException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/template/error.jsp").forward(req, resp);
        }
    }
}
