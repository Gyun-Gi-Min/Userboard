<%@ page import="com.koreait.board2.model.BoardVO" %>
<%@ page import="com.koreait.board2.model.UserVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BoardVO vo = (BoardVO) request.getAttribute("aaa");
    UserVO loginUser = (UserVO)session.getAttribute("loginUser");
%>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${requestScope.aaa.title}</title>
</head>
<body>
    <h1>Detail</h1>
    <% if(loginUser != null && vo.getWriter() == loginUser.getIuser()) { %>
    <a href="/board/mod?iboard=${requestScope.aaa.iboard}"><input type="button" value="수정"></a>
    <a href="/board/del?iboard=${requestScope.aaa.iboard}"><input type="button" value="삭제"></a>
    <%  } %>
    <div>
        <a href="/board/list">GO List</a>
    </div>
    <table>
        <div>${requestScope.err}</div>
        <div>번호 : ${requestScope.aaa.iboard}</div>
        <div>제목 :${requestScope.aaa.title}</div>
        <div>작성자 :${requestScope.aaa.writerNm}</div>
        <div>작성일시 : ${requestScope.aaa.rdt}</div>
        <div>내용 : ${requestScope.aaa.ctnt}</div>
    </table>

</body>
</html>
