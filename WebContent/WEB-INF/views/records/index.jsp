<!-- recordのindex -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2><c:out value="${kakeibo.pageName}" />入力ページ</h2>



        <div class="y_data_area">
            <table class="y_data_title">
                <tr>
                    <th class="date">日付</th>
                    <th class="item">項目</th>
                    <th class="tag">タグ</th>
                    <th class="income">収入</th>
                    <th class="spending">支出</th>
                    <th class="total">合計</th>
                    <th class="action">操作</th>
                </tr>
            </table>
            <div class="y_scroll_box">
                <div class="y_hidden">
                    <table class="y_data">
                        <c:set var="total" value="0" />
                        <c:if test="${records != null}" >
                            <c:forEach var="records" items="${records}" varStatus="status">
                                <tr class="row${status.count % 2}">
                                    <td class="date"><p><fmt:formatDate value='${records.date}' pattern='yyyy-MM-dd' /></p></td>
                                    <td class="item"><p><c:out value="${records.item}" /></p></td>
                                    <td class="tag"><p><c:out value="${records.tag}" /></p></td>
                                    <td class="income"><p><c:out value="${records.income}" /></p></td>
                                    <td class="spending"><p><c:out value="${records.spending}" /></p></td>
                                    <td class="total">
                                        <p>
                                            <c:set var="income" value="${records.income}" />
                                            <c:set var="spending" value="${records.spending}" />
                                            <c:set var="total" value="${total + income - spending}" />
                                            <c:out value="${total}" />
                                        </p>
                                    </td>
                                    <td class="action">
                                        <p><a href="<c:url value='/records/edit?id=${records.id}&kakeibo_id=${kakeibo.id}' />">修正</a></p>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                </div>
            </div>
        </div>



        <p>＜入力フォーム＞</p>





            <form method="POST" action="<c:url value='/records/create?id=${kakeibo.id}' />">
                <div class="input_area">

                    <label for="date">日付</label>
                    <input type="date" name="date" value="<fmt:formatDate value='${record.date}' pattern='yyyy-MM-dd' />" />



                    <label for="item">　　項目</label>
                    <input type="text" name="item" value="${record.item}" />



                    <label for="tag">　　タグ</label>
                    <input type="text" name="tag" value="${record.tag}" />



                    <label for="income">　　収入</label>
                    <input type="text" name="income" value="${record.income}" />



                    <label for="spending">　　支出</label>
                    <input type="text" name="spending" value="${record.spending}" />



                    <input type="hidden" name="_token" value="${_token}" />
                    <button type="submit">入力</button>
                </div>
            </form>


        <script>
        $(document).ready( function(){
            var height
            $('div.y_scroll_box').scrollTop($('div.y_scroll_box')[0].scrollHeight);
        });



        </script>


    </c:param>
    <c:param name="footer_content">

    </c:param>
</c:import>