package controllers.kakeibo;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Kakeibo;
import utils.DBUtil;

/**
 * Servlet implementation class KakeiboDestroyServlet
 */
@WebServlet("/kakeibo/destroy")
public class KakeiboDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakeiboDestroyServlet() {
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

            Kakeibo k = em.find(Kakeibo.class, (Integer)(request.getSession().getAttribute("kakeibo_id")));
            k.setDelete_flag(1);
            k.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "削除が完了しました。");

            response.sendRedirect(request.getContextPath() + "/");
        }
    }

}
