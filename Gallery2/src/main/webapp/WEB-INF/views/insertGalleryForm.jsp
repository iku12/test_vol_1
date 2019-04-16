<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<%response.setContentType("text/html;charset=utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>인덱스</title>
</head>
<body>

<form action="insertGallery.do" method="post" enctype="multipart/form-data">


<div>
<span>상호명</span> <input type="text" name="g_name">
</div>

<div>
<span>소갯말</span> <input type="text" name="g_intro">
</div>


<div>
<span>주소</span> <input type="text" name="g_adress">
</div>

<div>
<span>번호</span> <input type="text" name="g_tel">
</div>

<div>
<span>내부사진1</span> <input multiple="multiple" type="file" name="file" required="required">
</div>

<div>
<span>내부사진2</span> <input multiple="multiple" type="file" name="file" required="required">
</div>

<div>
<span>내부사진3</span> <input multiple="multiple" type="file" name="file" required="required">
</div>

<div>
<span>내부사진4</span> <input multiple="multiple" type="file" name="file" required="required">
</div>

<input type="submit">

</form>











</div>






</body>
</html>