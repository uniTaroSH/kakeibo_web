<!-- recordのindex -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2><c:out value="${kakeibo.pageName}" />入力ページ</h2>
        <table class="record_list">
            <tbody>
                <tr>
                    <th class="record_date">日付</th>
                    <th class="record_item">項目</th>
                    <th class="record_tag">タグ</th>
                    <th class="record_income">収入</th>
                    <th class="record_spending">支出</th>
                    <th class="record_total">合計</th>
                    <th class="record_action"></th>
                </tr>
                <c:set var="total" value="0" />
                <c:if test="${records != null}" >
                    <c:forEach var="records" items="${records}" varStatus="status">
                        <tr class="row${status.count % 2}">
                            <td class="record_date"><fmt:formatDate value='${records.date}' pattern='yyyy-MM-dd' /></td>
                            <td class="record_item"><c:out value="${records.item}" /></td>
                            <td class="record_tag"><c:out value="${records.tag}" /></td>
                            <td class="record_income"><c:out value="${records.income}" /></td>
                            <td class="record_spending"><c:out value="${records.spending}" /></td>
                            <td class="record_total">
                                <c:set var="income" value="${records.income}" />
                                <c:set var="spending" value="${records.spending}" />
                                <c:set var="total" value="${total + income - spending}" />
                                <c:out value="${total}" />
                            </td>
                            <td class="record_action">
                                <a href="<c:url value='/records/edit?id=${records.id}&kakeibo_id=${kakeibo.id}' />">修正</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>


        <p><a href="<c:url value='/records/new?id=${kakeibo.id}' />">新規レコードの入力</a></p>
        <br />
    </c:param>
</c:import>