<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${kakeibo != null}">
                <h2>id : ${kakeibo.pageName} の詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>家計簿の名前</th>
                            <td><c:out value="${kakeibo.pageName}" /></td>
                        </tr>
                        <tr>
                            <th>作成日時</th>
                            <td>
                                <fmt:formatDate value="${kakeibo.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${kakeibo.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/kakeibo/edit?id=${kakeibo.id}' />">この家計簿の情報を編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/' />">一覧に戻る</a></p>
    </c:param>
</c:import>