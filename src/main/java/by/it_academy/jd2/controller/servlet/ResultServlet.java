package by.it_academy.jd2.controller.servlet;


import by.it_academy.jd2.dto.ResultsDTO;
import by.it_academy.jd2.service.api.IVoteService;
import by.it_academy.jd2.service.factory.VoteServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static by.it_academy.jd2.util.FilePathUtil.RESULTS_JSP_PATH;
public class ResultServlet extends HttpServlet {

    IVoteService votingService = VoteServiceFactory.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm").withLocale(Locale.forLanguageTag("ru"));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       ResultsDTO results = votingService.getResults();


        req.setAttribute("artists", results.getArtists());
        req.setAttribute("genres", results.getGenres());
        req.setAttribute("comments", results.getSortedComments());

        req.setAttribute("formatter",formatter);

        req.getRequestDispatcher(RESULTS_JSP_PATH).forward(req, resp);

    }

}
