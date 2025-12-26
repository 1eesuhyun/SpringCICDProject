<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section class="breadcrumb-section set-bg" data-setbg="/img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>상세보기</h2>
                        <div class="breadcrumb__option">
                           <span>부산</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->
    <!-- Product Details Section Begin -->
    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__pic">
                        <div class="product__details__pic__item">
                            <img class="product__details__pic__item--large" src="${vo.poster }" style="width: 100%";height="350px;">
                        </div>
                        
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__text">
                        <h3>${vo.name }</h3>
                        <div class="product__details__rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star-half-o"></i>
                            <span>${vo.score }</span>
                        </div>
                        <p></p>
                        <ul>
                            <li><b>주소</b> <span>${vo.address }</span></li>
                            <li><b>전화번호</b> <span>${vo.phone }</span></li>
                            <li><b>음식종류</b> <span>${vo.type}</span></li>
                            <li><b>주차</b> <span>${vo.parking }</span></li>
                            <li><b>테마</b> <span>${vo.theme }</span></li>
                            <li><b>가격대</b> <span>${vo.price}</span></li>
                            <li><b>영업시간</b> <span>${vo.time }</span></li>
                            <li><b>조회수</b> <span>${vo.hit }</span></li>
                            <li class="text-right"><button class="btn-xs btn-info" onclick="javascript:history.back()">목록</button></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="product__details__tab">
                    <ul>${vo.content }</ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Product Details Section End -->
    
    <!-- Related Product Section Begin -->
    <section class="related-product">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title related__product__title">
                        <h2>주변 명소</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="product__item">
                        <div class="product__item__pic set-bg">
                         <img src="#">
                            <ul class="product__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-calendar"></i></a></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6><a href="#"></a></h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>