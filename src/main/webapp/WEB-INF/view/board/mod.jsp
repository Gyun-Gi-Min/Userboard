<%@ page import="com.koreait.board2.model.BoardVO" %>
<%@ page import="com.koreait.board2.board.BoardDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% BoardVO vo = (BoardVO) request.getAttribute("up");
   BoardVO aa = (BoardVO) request.getAttribute("data");
    String err = (String)request.getAttribute("err");

%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOD</title>
</head>
<body>
    <h1>수정중</h1>
    <% if(err != null) { %>
    <div><%=err%></div>
    <% } %>
    <form action="/board/mod?iboard=${requestScope.up.iboard}" method="post" id="fom">
        <% if(aa == null) {%>
        <div><input type="text" name="title" placeholder="title" value="${requestScope.up.title}"></div>
        <div><textarea name="ctnt"  rows="20">${requestScope.up.ctnt}</textarea></div>
        <% } else { %>
        <div><input type="text" name="title" placeholder="title" value="${requestScope.data.title}"></div>
        <div><textarea name="ctnt" rows="20">${requestScope.data.ctnt}</textarea></div>
        <% } %>


        <div>
            <input type="submit" value="수정">
            <input type="reset" value="수정하기전">
            <input type="button" value="초기화" onclick="clean()";>
        </div>
    </form>

<script>

    function clean(){
        var fom = document.querySelector('#fom');
        if(fom){
            fom.title.value='';
            fom.ctnt.value='';
        }
    }

</script>


</body>
</html>
