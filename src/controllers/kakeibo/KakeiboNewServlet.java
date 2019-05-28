package controllers.kakeibo;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Kakeibo;

/**
 * Servlet implementation class KakeiboNewServlet
 */
@WebServlet("/kakeibo/new")
public class KakeiboNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakeiboNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());


        Kakeibo k = new Kakeibo();
        k.setCreated_at(new Timestamp(System.currentTimeMillis()));
        request.setAttribute("kakeibo", k);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/kakeibo/new.jsp");
        rd.forward(request, response);
    }

}
