<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section class="breadcrumb-section set-bg"
		data-setbg="/img/breadcrumb.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
						<h2>맛집</h2>
						<div class="breadcrumb__option">
							<span>상세보기</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="product-details spad" id="food_detail">
		<div class="container">
		 <div class="row" style="margin-top: 0px auto;">
		  <table class="table">
		   <tr>
		    <td width="30%" class="text-center" rowspan="8">
		     <img :src="store.food_detail.poster" style="width: 270px;height: 300px">
		    </td>
		    <td colspan="2">
		     <h3>{{store.food_detail.name}}&nbsp;<span style="color: orange;">{{store.food_detail.score}}</span></h3>
		    </td>
		   </tr>
		   <tr>
		    <td width="10%" class="text-center">주소</td>
		    <td width="60%" class="text-left">{{store.food_detail.address}}</td>
		   </tr>
		   <tr>
		    <td width="10%" class="text-center">전화</td>
		    <td width="60%" class="text-left">{{store.food_detail.phone}}</td>
		   </tr>
		   <tr>
		    <td width="10%" class="text-center">음식종류</td>
		    <td width="60%" class="text-left">{{store.food_detail.type}}</td>
		   </tr>
		   <tr>
		    <td width="10%" class="text-center">영업시간</td>
		    <td width="60%" class="text-left">{{store.food_detail.time}}</td>
		   </tr>
		   <tr>
		    <td width="10%" class="text-center">주차</td>
		    <td width="60%" class="text-left">{{store.food_detail.parking}}</td>
		   </tr>
		   <tr>
		    <td width="10%" class="text-center">가격대</td>
		    <td width="60%" class="text-left">{{store.food_detail.price}}</td>
		   </tr>
		   <tr>
		    <td width="10%" class="text-center">테마</td>
		    <td width="60%" class="text-left">{{store.food_detail.theme}}</td>
		   </tr>
		  </table>
		  <table class="table">
		   <tr>
		    <td>{{store.food_detail.content}}</td>
		   </tr>
		   <tr>
		    <td class="text-right">
		     <button class="btn-xs btn-warning">예약</button>
		     <button class="btn-xs btn-info" onclick="javascript:history.back()">목록</button>
		    </td>
		   </tr>
		  </table>
		 </div>
		 <!-- 지도 -->
		 <div id="map" style="width:100%;height:350px;"></div>
		 <!-- 댓글 -->
		 <div class="row" style="margin-top: 20px auto;">
		  <table class="table">
		   <tr>
		    <td>
		     <table class="table" v-for="rvo in store.reply">
		      <tr>
		       <td class="text-left">🤡{{rvo.name}} {{rvo.dbday}}</td>
		       <td class="text-right">
		       <a href="#" class="btn btn-xs btn-primary" v-if="store.sessionId===rvo.id">수정</a>
		       <a href="#" class="btn btn-xs btn-danger" v-if="store.sessionId===rvo.id">삭제</a>
		       </td>
		      </tr>
		      <tr>
		       <td colspan="2">
		        <pre style="white-space: pre-wrap;background-color: white;">{{rvo.msg}}</pre>
		       </td>
		      </tr>
		     </table>
		    </td>
		   </tr>
		  </table>
		  <c:if test="${sessionScope.id!=null }">
		  <table class="table">
		   <tr>
		    <td class="text-center">
		     <textarea rows="5" cols="60" style="float: left" ref="msgRef" v-model="store.msg"></textarea>
		     <button class="btn-success" style="width: 80px;height: 127px;float: left" @click="store.foodReplyInsert(fno,msgRef)">댓글쓰기</button>
		    </td> 
		   </tr>
		  </table>
		  </c:if>
		 </div>
		</div>
    </section>
    <script src="/foodjs/foodStore.js"></script>
    <script>
     const {createApp,onMounted,ref}=Vue
     const {createPinia}=Pinia
     const app=createApp({
    	 setup(){
    		 const store=useFoodStore()
    		 const params=new URLSearchParams(location.search)
    		 const fno=params.get('fno')
    		 const msgRef=ref(null)
    		 
    		 onMounted(()=>{
    			 store.foodDetailData(fno)
    			 store.foodReplyData(fno)
    			 // 데이터 변경 감지
    			 if(!store.address) return
    			 if(window.kakao && window.kakao.maps)
    			 {
    				 initMap()
    			 }
    			 else
    			 {
    				 addScript()
    			 }
    			})
    			const initMap=()=>{
    				var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    			    mapOption = {
    			        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
    			        level: 3 // 지도의 확대 레벨
    			    };  

    			// 지도를 생성합니다    
    			var map = new kakao.maps.Map(mapContainer, mapOption); 

    			// 주소-좌표 변환 객체를 생성합니다
    			var geocoder = new kakao.maps.services.Geocoder();

    			// 주소로 좌표를 검색합니다
    			geocoder.addressSearch(store.food_detail.address, function(result, status) {

    			    // 정상적으로 검색이 완료됐으면 
    			     if (status === kakao.maps.services.Status.OK) {

    			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

    			        // 결과값으로 받은 위치를 마커로 표시합니다
    			        var marker = new kakao.maps.Marker({
    			            map: map,
    			            position: coords
    			        });

    			        // 인포윈도우로 장소에 대한 설명을 표시합니다
    			        var infowindow = new kakao.maps.InfoWindow({
    			            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+store.food_detail.name+'</div>'
    			        });
    			        infowindow.open(map, marker);

    			        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    			        map.setCenter(coords);
    			    } 
    			});    
    			}
    			const addScript=()=>{
    				const script=document.createElement("script")
    				script.onload=()=>kakao.maps.load(initMap)
    				script.src="http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e1bce25e48b8adc14d4c02bcf026654c&libraries=services"
    				document.head.appendChild(script)
    			}
    			return {
    			  store,msgRef,fno
    		 }
    	 }
     })
     app.use(createPinia())
     app.mount("#food_detail")
    </script>
</body>
</html>