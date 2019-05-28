<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="date">日付</label><br />
<input type="date" name="date" value="<fmt:formatDate value='${record.date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="item">項目</label><br />
<input type="text" name="item" value="${record.item}" />
<br /><br />

<label for="tag">タグ</label><br />
<input type="text" name="tag" value="${record.tag}" />
<br /><br />

<label for="income">収入</label><br />
<input type="text" name="income" value="${record.income}" />
<br /><br />

<label for="spending">支出</label><br />
<input type="text" name="spending" value="${record.spending}" />
<br /><br />


<input type="hidden" name="_token" value="${_token}" />
<button type="submit">入力</button>