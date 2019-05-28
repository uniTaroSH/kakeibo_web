<!-- topPageのindex  -->
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
        <h2>家計簿アプリ　トップページ</h2>
        <h3>【家計簿　一覧】</h3>
        <table class="kakeibo_list">
            <tbody>
                <tr>
                    <th class="kakeibo_pageName">家計簿名</th>
                    <th class="kakeibo_updated">最終更新日</th>
                    <th class="kakeibo_action"></th>
                    <th class="kakeibo_action"></th>
                </tr>
<!-- ***************************************************************************************************** -->
                <c:forEach var="kakeibo" items="${kakeibo}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="kakeibo_pageName"><c:out value="${kakeibo.pageName}" /></td>
                        <td class="kakeibo_updated"><fmt:formatDate value='${kakeibo.updated_at}' pattern='yyyy-MM-dd　HH:mm:ss' /></td>
                        <td class="kakeibo_action">
                            <form method="GET" action="<c:url value='/records/index' />">
                                <input type="submit" value="開く">
                                <input type="hidden" name="id" value="${kakeibo.id}" />
                            </form>
                        </td>
                        <td class="kakeibo_action">
                            <form method="GET" action="<c:url value='/kakeibo/show' />">
                                <input type="submit" value="詳細">
                                <input type="hidden" name="id" value="${kakeibo.id}" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
<!-- ***************************************************************************************************** -->
        <div id="pagination">
            （全 ${kakeibo_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((kakeibo_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>



    </c:param>
</c:import>