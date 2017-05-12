<!DOCTYPE html>
<html>
<head>
    <title>Single</title>
    <link href="/assets/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <!--jQuery(necessary for Bootstrap's JavaScript plugins)-->
    <script src="/assets/js/jquery-1.11.0.js"></script>
    <!--Custom-Theme-files-->
    <!--theme-style-->
    <link href="/assets/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <!--//theme-style-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Luxury Watches Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!--dropdown-->
    <script src="/assets/js/jquery.easydropdown.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
    <a href="http://localhost:8080/?pageNumber=1&pageSize=2"><h1>爱丽丝影院</h1></a>
</div>
<div class="single contact">
    <div class="container">
        <div class="headdings">
            <h3 class="ghj">订单列表</h3>
            <div class="bs-example">
                <table class="table">
                    <thead>
                    <tr>
                        <th>用户名</th>
                        <th>电影名</th>
                        <th>放映时间</th>
                        <th>放映厅</th>
                        <th>取票码</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list list as ord>
                    <tr>
                        <td>
                            ${ord.user.username}
                        </td>
                        <td>${ord.play.movie.name}</td>
                        <td>${ord.play.showtime?string('yyyy-MM-dd')!'-'}</td>
                        <td>${ord.play.videoHall}</td>
                        <td>${ord.ticketNum}</td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>