package edu.upc.dsa.dao.implementations;

import edu.upc.dsa.dao.FAQDAO;
import edu.upc.dsa.dao.ItemDAO;
import edu.upc.dsa.models.Question;
import org.apache.log4j.Logger;

import java.util.List;

public class FAQDAOImpl implements FAQDAO {
    private SessionImpl session;

    final static Logger logger = Logger.getLogger(FAQDAOImpl.class);

    private static FAQDAOImpl instance;

    private FAQDAOImpl(){
        this.session = SessionImpl.getInstance();
    }

    public static FAQDAO getInstance(){
        if(instance == null){
            instance = new FAQDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Question> getFaqs() {
       List<Question> questionsList = this.session.findAll(Question.class);
       return questionsList;
    }
}
