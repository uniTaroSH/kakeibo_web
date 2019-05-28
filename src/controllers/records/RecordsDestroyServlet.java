package controllers.records;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Kakeibo;
import models.Record;
import utils.DBUtil;

/**
 * Servlet implementation class RecordsDestroyServlet
 */
@WebServlet("/records/destroy")
public class RecordsDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordsDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Record r = em.find(Record.class, (Integer)(request.getSession().getAttribute("record_id")));
            Kakeibo k = em.find(Kakeibo.class, (Integer)(request.getSession().getAttribute("kakeibo_id")));


            r.setDelete_flag(1);
            r.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            k.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "削除が完了しました。");


            request.getSession().removeAttribute("record_id");
            request.getSession().removeAttribute("kakeibo_id");


            response.sendRedirect(request.getContextPath() + "/records/index?id=" + k.getId());
        }
    }

}
