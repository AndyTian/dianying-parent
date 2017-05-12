<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/minified/aui-production.min.css"/>
    <!--jQuery(necessary for Bootstrap's JavaScript plugins)-->
    <script src="/assets/js/jquery-1.11.0.min.js"></script>
    <!--Custom-Theme-files-->
    <!--theme-style-->
    <link href="/assets/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <!--//theme-style-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Luxury Watches Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!--start-menu-->
    <link rel="stylesheet" type="text/css" href="/assets/css/minified/aui-production.min.css"/>
    <link href="/assets/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <!--dropdown-->
    <script src="/assets/js/bootstrap.js"></script>
</head>
<body>
<!--top-header-->
<div class="top-header">
    <div class="container">
        <div class="top-header-main">
<#if sessionUser?exists>
            <div class="col-md-12 top-header-left">
                <div class="cart box_1">
                    <font color="#FFFFFF">
                    ${sessionUser.username}已登录
                    </font>
                    <a href="ord/list">
                        <font color="#FFFFFF">
                           查看订单
                            </font>
                        <img src="/assets/images/cart-1.png" alt="" />
                    </a>
                    <a href="logout">
                        <font color="#FFFFFF">
                            切换用户
                        </font>
                    </a>
                    <div class="clearfix"> </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
</#if>
    </div>
</div>
<!--top-header-->
<!--start-logo-->
<div class="logo">
    <a href="http://localhost:8080/"><h1>爱丽丝影院</h1></a>
</div>
<!--start-logo-->
<!--bottom-header-->
<div class="header-bottom">
    <div class="container">
        <div class="header">
            <div class="col-md-3 header-right">
                <div class="search-bar">
                    <input type="text" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">
                    <input type="submit" value="">
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
<!--bottom-header-->
<!--banner-starts-->
<div class="bnr" id="home">
    <div  id="top" class="callbacks_container">
        <ul class="rslides" id="slider4">
            <li>
                <div class="banner">
                </div>
            </li>
            <li>
                <div class="banner1">
                </div>
            </li>
            <li>
                <div class="banner2">
                </div>
            </li>
        </ul>
    </div>
    <div class="clearfix"> </div>
</div>
<!--banner-ends-->
<!--Slider-Starts-Here-->
<script src="/assets/js/responsiveslides.min.js"></script>
<script>
    // You can also use "$(window).load(function() {"
    $(function () {
        // Slideshow 4
        $("#slider4").responsiveSlides({
            auto: true,
            pager: true,
            nav: true,
            speed: 500,
            namespace: "callbacks",
            before: function () {
                $('.events').append("<li>before event fired.</li>");
            },
            after: function () {
                $('.events').append("<li>after event fired.</li>");
            }
        });

    });
</script>
<!--End-slider-script-->
<!--about-starts-->
<!--about-end-->
<!--product-starts-->
<div class="product">
    <div class="container">
        <div class="product-top">
            <#list list as movie>
                <div class="col-md-3 product-left">
                    <div class="product-main simpleCart_shelfItem">
                        <a href="/movie/single/${movie.id}"  target="_blank" class="mask"><img class="img-responsive zoom-img" src="${movie.pic}" alt="" /></a>
                        <div class="product-bottom">
                            <h3>${movie.name}</h3>
                            <p>${movie.stars}</p>
                            <h4><a class="item_add" href="#"><i></i></a> <span class=" item_price">$ 329</span></h4>
                        </div>
                        <div class="srch">
                            <span>-50%</span>
                        </div>
                    </div>
                </div>
           </#list>
        </div>
    </div>
</div>
<div class="button-group center-div">
${pagerHelper.content}
</div>
<!--product-end-->
<!--information-starts-->
<!--information-end-->
<!--footer-starts-->
<div class="footer">
    <div class="container">
        <div class="footer-top">
            <div class="col-md-12 footer-right">
                <p>Copyright &copy; 2015.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!--footer-end-->
</body>
</html>