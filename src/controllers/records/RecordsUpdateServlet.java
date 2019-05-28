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
 * Servlet implementation class RecordsUpdateServlet
 */
@WebServlet("/records/update")
public class RecordsUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordsUpdateServlet() {
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


            r.setDate(Date.valueOf(request.getParameter("date")));
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


            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            r.setUpdated_at(currentTime);
            k.setUpdated_at(currentTime);


            List<String> errors = RecordValidator.validate(r);
            if(errors.size() > 0){
                em.close();


                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("errors", errors);
                request.setAttribute("record", r);
                request.setAttribute("kakeibo_id", k.getId());


                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/records/edit.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "修正しました。");

                request.getSession().removeAttribute("record_id");
                request.getSession().removeAttribute("kakeibo_id");


                response.sendRedirect(request.getContextPath() + "/records/index?id=" + k.getId());
            }
        }
    }

}
