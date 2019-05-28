package controllers.records;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class RecordsIndexServlet
 */
@WebServlet("/records/index")
public class RecordsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordsIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();


        User login_user = (User)request.getSession().getAttribute("login_user");
        Kakeibo kakeibo = em.find(Kakeibo.class, Integer.parseInt(request.getParameter("id")));


        //ログインユーザーと開く家計簿の作成者を照合
        if(login_user.getId() == kakeibo.getUser().getId()){
            //家計簿のレコードをリストに格納
            List<Record> records = em.createNamedQuery("getRecords", Record.class)
                    .setParameter("kakeibo", kakeibo)
                    .getResultList();

            if(records == null){

            }


            em.close();

            request.setAttribute("kakeibo", kakeibo);
            request.setAttribute("records", records);


        }else{
            //TODO 合致しなかった場合の動作どうしよう？
        }


        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }



        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/records/index.jsp");
        rd.forward(request, response);
    }


}
