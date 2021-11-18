<%@ page import="com.koreait.board2.model.BoardVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% BoardVO vo = (BoardVO) request.getAttribute("up"); %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UPDATE</title>
</head>
<body>
    <h1>수정</h1>

    <form action="/board/mod" method="post" id="fom">
        <input type="hidden" name="iboard" value="<%=vo.getIboard()%>">
        <div><input type="text" name="title" value="<%=vo.getTitle()%>"></div>
        <div><textarea name="ctnt"><%=vo.getCtnt()%></textarea></div>

        <div>
            <input type="submit" value="저장">
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
