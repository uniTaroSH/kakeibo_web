package controllers.records;

import java.io.IOException;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Kakeibo;
import models.Record;
import utils.DBUtil;

/**
 * Servlet implementation class RecordsNewServlet
 */
@WebServlet("/records/new")
public class RecordsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordsNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());


        EntityManager em = DBUtil.createEntityManager();
        Kakeibo k = em.find(Kakeibo.class, Integer.parseInt(request.getParameter("id")));

        Record r = new Record();
        r.setDate(new Date(System.currentTimeMillis()));


        request.setAttribute("record", r);
        request.setAttribute("kakeibo", k);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/records/new.jsp");
        rd.forward(request, response);
    }

}
