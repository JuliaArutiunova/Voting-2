package by.it_academy.jd2.controller.servlet;

import by.it_academy.jd2.dto.FormCreateDTO;
import by.it_academy.jd2.service.api.IFormManagerService;
import by.it_academy.jd2.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.ArrayList;



public class CreateVotingServlet extends HttpServlet {

    private static final String CREATE_FORM_PATH = "/template/formManager/createVoting.jsp";

    IFormManagerService formManagerService = ServiceFactory.getFormManagerService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(CREATE_FORM_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String votingName = req.getParameter("votingName");
        ArrayList<String> newArtists = getFilledInFields(req.getParameterValues("artist"));
        ArrayList<String> newGenres = getFilledInFields(req.getParameterValues("genre"));

        Writer writer = resp.getWriter();

        if (!newArtists.isEmpty() && !newGenres.isEmpty()) {
            try {
                formManagerService.create(FormCreateDTO.builder()
                        .setVotingName(votingName)
                        .setNewArtists(newArtists)
                        .setNewGenres(newGenres)
                        .setStart(LocalDateTime.now())
                        .build());

                writer.write("<h3>Голосование успешно создано<h3>");
            } catch (IllegalArgumentException e) {
                writer.write("<h3>Голосование не создано!</h3>");
                writer.write("<p>" + e.getMessage() + "<p>");
            }
        } else {
            writer.write("<p>Данные не были внесены. Голосование не создано</p>");
        }
    }

    public ArrayList<String> getFilledInFields(String[] parameters) {
        ArrayList<String> filledInFields = new ArrayList<>();
        for (String parameter : parameters) {
            if (!parameter.isBlank()) {
                filledInFields.add(parameter);
            }
        }
        return filledInFields;
    }


}
