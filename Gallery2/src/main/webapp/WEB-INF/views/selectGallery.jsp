<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
<%response.setContentType("text/html;charset=utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>인덱스</title>

<style type="text/css">




img{
	width: 100px;
	height: 100px;	
}

button {
	background-color: pink;
	border-radius: 10px;
	border: none; 
}
</style>
</head>

<script type="text/javascript">

function imgup() {
	
	document.getElementById("gallery_img1").click();
}

 function imageURL(input) {
	if (input.files && input.files[0]) {
		
		 var reader = new FileReader();
		
		reader.onload = function(e) {
			$("#preview_img1").attr("src",e.target.result);
		
		} 
		reader.readAsDataURL(input.files[0]);
		}
}

 




</script>

<body>

<div>

<form action="updateGallery.do" method="post" enctype="multipart/form-data">


<div>
<span>g_no 히든으로 처리합시다</span> <input type="text" name="g_no" value="${galleryDto.g_no}" readonly="readonly">
</div>

<div>
<span>m_no 히든으로 처리합시다</span> <input type="text" name="m_no" value="${galleryDto.m_no}" readonly="readonly">
</div>


<div>
<span>g_name</span> <input type="text" name="g_name" value="${galleryDto.g_name}" readonly="readonly">
</div>

<div>
<span>g_adress</span> <input type="text" name="g_adress" value="${galleryDto.g_adress}">
</div>

<div>
<span>g_tel</span> <input type="text" name="g_tel" value="${galleryDto.g_tel}">
</div>

<div>
<span>g_intro</span> <input type="text" name="g_intro" value="${galleryDto.g_intro}">
</div>



<div>
<span>g_img1</span> <span><img alt="g_img1" src="upload/${galleryDto.g_img1}"></span> 

<label for="gallery_img1"><button onclick="imgup()">파일추가</button></label>

<img style="width: 300px; height: 300px;" id="preview_img1" alt="" src="">

<input style="display: none;" onchange="imageURL(this)" id="gallery_img1" multiple="multiple" type="file" name="file" required="required" >
</div>

<div>
<span>g_img2</span> <span><img alt="g_img2" src="upload/${galleryDto.g_img2}"></span>  
<label for="gallery_img2"><button onclick="imgup()">파일추가</button></label>
<input style="display: none;" onchange="imageURL(this)" id="gallery_img2" multiple="multiple" type="file" name="file" required="required">
</div>

<div>
<span>g_img3</span> <span><img alt="g_img3" src="upload/${galleryDto.g_img3}"></span> 
<label for="gallery_img3"><button onclick="imgup()">파일추가</button></label>
<input style="display: none;" onchange="imageURL(this)" id="gallery_img3" multiple="multiple" type="file" name="file" required="required">
</div>

<div>
<span>g_img4</span> <span><img alt="g_img4" src="upload/${galleryDto.g_img4}"></span> <input multiple="multiple" type="file" name="file" required="required">
</div>


<div>
<span>g_state 히등으로 처리 합시다</span> <input type="text" name="g_state" value="${galleryDto.g_state}" readonly="readonly">
</div>

<input type="submit" value="수정을 해볼가">

</form>

<div id="map" style="width:500px;height:400px;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d61a4dc1efe55edbdfac5ab744921183"></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new daum.maps.LatLng(37.5256170,126.888900),
			level: 3
			
	
			
		};

		var map = new daum.maps.Map(container, options);
		
		// 마커가 표시될 위치입니다 
		var markerPosition  = new daum.maps.LatLng(37.5256170,126.888900); 

		// 마커를 생성합니다
		var marker = new daum.maps.Marker({
		    position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
		
		
		
	</script>



</div>

</body>
</html>