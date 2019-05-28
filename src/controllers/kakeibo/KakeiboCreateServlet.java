package controllers.kakeibo;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Kakeibo;
import models.User;
import models.validators.KakeiboValidator;
import utils.DBUtil;



/**
 * Servlet implementation class KakeiboCreateServlet
 */
@WebServlet("/kakeibo/create")
public class KakeiboCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakeiboCreateServlet() {
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


            Kakeibo k = new Kakeibo();


            k.setUser((User)request.getSession().getAttribute("login_user"));


            k.setPageName(request.getParameter("title"));


            k.setDelete_flag(0);


            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            k.setCreated_at(currentTime);
            k.setUpdated_at(currentTime);


            List<String> errors = KakeiboValidator.validate(k);
            if(errors.size() > 0){
                em.close();


                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("kakeibo", k);
                request.setAttribute("errors", errors);


                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/kakeibo/new.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.persist(k);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "家計簿を作成しました。");


                response.sendRedirect(request.getContextPath() + "/");
            }
        }
    }
}
