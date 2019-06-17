package controllers.records;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Kakeibo;
import models.Record;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class RecordsEditServlet2
 */
@WebServlet("/records/edit2")
public class RecordsEditServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordsEditServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();


        Record r = em.find(Record.class, Integer.parseInt(request.getParameter("id")));
        Kakeibo k = em.find(Kakeibo.class, Integer.parseInt(request.getParameter("kakeibo_id")));

        em.close();


        User login_user = (User)request.getSession().getAttribute("login_user");
        if(login_user.getId() == k.getUser().getId() && k.getId() == r.getKakeibo().getId()){
            request.setAttribute("record", r);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("record_id", r.getId());
            request.getSession().setAttribute("kakeibo_id", k.getId());
        }


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/records/edit.jsp");
        rd.forward(request, response);
    }

}
