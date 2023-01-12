package edu.upc.dsa.services;


import edu.upc.dsa.dao.FAQDAO;
import edu.upc.dsa.dao.implementations.FAQDAOImpl;
import edu.upc.dsa.dao.implementations.ItemDAOImpl;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Question;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/faq", description = "Endpoint to Match Service")
@Path("/faq")
public class FAQService {
    private FAQDAO faqmanager;

    public FAQService() {
        this.faqmanager = FAQDAOImpl.getInstance();
    }

    // get de todas las preguntas y respuestas frecuentes
    @GET
    @ApiOperation(value = "Get all questions", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Item.class, responseContainer = "List"),
    })
    @Path("/questionsList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestionLists() {

        List<Question> questionsList = new ArrayList<>();  // new ArrayList<>(); //this.itemManager.getItems();
        questionsList.add(new Question("Q1", "¿Cómo?", "Comiendo lomo"));
        questionsList.add(new Question("Q2", "¿Cuándo?", "Antes que Armando"));
        questionsList.add(new Question("Q3", "3331", "DESC3"));
        GenericEntity<List<Question>> entity = new GenericEntity<List<Question>>(questionsList) {};
        return Response.status(200).entity(entity).build();
    }
}

