package by.it_academy.jd2.controller.servlet;


import by.it_academy.jd2.dto.ResultsDTO;
import by.it_academy.jd2.service.api.IVotingService;
import by.it_academy.jd2.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class ResultServlet extends HttpServlet {
    IVotingService votingService = ServiceFactory.getVotingService();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultsDTO results = votingService.getResults();


        req.setAttribute("artists", results.getArtists());
        req.setAttribute("genres", results.getGenres());
        req.setAttribute("comments", results.getSortedComments());

        req.getRequestDispatcher("/template/votingResults.jsp").forward(req, resp);

    }

}
