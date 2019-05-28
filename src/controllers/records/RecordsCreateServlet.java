package controllers.records;

import java.io.IOException;
import java.sql.Date;
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
import models.Record;
import models.validators.RecordValidator;
import utils.DBUtil;

/**
 * Servlet implementation class RecordsCreateServlet
 */
@WebServlet("/records/create")
public class RecordsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordsCreateServlet() {
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


            Record r = new Record();
            Kakeibo k = em.find(Kakeibo.class, Integer.parseInt(request.getParameter("id")));


            Date date = new Date(System.currentTimeMillis());
            String date_str = request.getParameter("date");
            if(date_str != null && !date_str.equals("")) {
                date = Date.valueOf(request.getParameter("date"));
            }
            r.setDate(date);


            r.setItem(request.getParameter("item"));
            r.setTag(request.getParameter("tag"));


            String income = request.getParameter("income");
            if(income == null || income.equals("")){
                income = "0";
            }
            r.setIncome(Integer.parseInt(income));


            String spending = request.getParameter("spending");
            if(spending == null || spending.equals("")){
                spending = "0";
            }
            r.setSpending(Integer.parseInt(spending));


            r.setPageName(k.getPageName());
            r.setDelete_flag(0);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            r.setCreated_at(currentTime);
            r.setUpdated_at(currentTime);
            k.setUpdated_at(currentTime);

            r.setKakeibo(k);


            List<String> errors = RecordValidator.validate(r);
            if(errors.size() > 0){
                em.close();


                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("kakeibo", k);
                request.setAttribute("errors", errors);


                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/records/new.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.persist(r);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "入力しました。");


                response.sendRedirect(request.getContextPath() + "/records/index?id=" + k.getId());
            }
        }
    }
}
