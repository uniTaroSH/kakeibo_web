package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {


    /**
     * Default constructor.
     */
    public EncodingFilter() {
        //コンストラクタ(フィルタのインスタンスが生成される時に実行される)
    }


    /**
    * @see Filter#destroy()
    */
    public void destroy() {
        //フィルタの処理が不要になったためフィルタを破棄するという時の処理を定義するメソッド
    }


    /**
    * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
    */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //フィルタとしての実行内容を定義するメソッド

        request.setCharacterEncoding("UTF-8");      //リクエスト時の文字エンコードをUTF-8に設定
        response.setCharacterEncoding("UTF-8");     //レスポンス時の文字エンコードをUTF-8に設定

        //↓ここより上に書いた処理はサーブレットが処理を実行する「前」に行われる。
        chain.doFilter(request, response);
        //↑ここより下に書いた処理はサーブレットが処理を実行した「後」に行われる。
    }


    /**
    * @see Filter#init(FilterConfig)
    */
    public void init(FilterConfig fConfig) throws ServletException {
        //フィルタの処理が初めて実行される時の処理を定義するメソッド
    }


}
