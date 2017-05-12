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
    <!--start-menu-->
    <script src="/assets/js/simpleCart.min.js"> </script>
    <link href="/assets/css/memenu.css" rel="stylesheet" type="text/css" media="all" />
    <link href="/assets/css/movie-detail.6b71afde.css" rel="stylesheet" type="text/css" media="all" />
    <link href="/assets/css/jBox.css" rel="stylesheet" type="text/css" media="all" />
    <!--dropdown-->
    <script src="/assets/js/jquery.easydropdown.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function() {
            var menu_ul = $('.menu_drop > li > ul'),
                    menu_a  = $('.menu_drop > li > a');

            menu_ul.hide();

            menu_a.click(function(e) {
                e.preventDefault();
                if(!$(this).hasClass('active')) {
                    menu_a.removeClass('active');
                    menu_ul.filter(':visible').slideUp('normal');
                    $(this).addClass('active').next().stop(true,true).slideDown('normal');
                } else {
                    $(this).removeClass('active');
                    $(this).next().stop(true,true).slideUp('normal');
                }
            });

        });
        function add_info(id)
        {
            var form_data = $("#form_data").serialize();
            if(!id)
            {
                alert('Error！');
                return false;
            }

            $.ajax(
                    {
                        url: "/pingjia/add/"+id,
                        data:form_data,
                        type: "post",
                        beforeSend:function()
                        {
                            $("#tip").html("<span style='color:blue'>正在处理...</span>");
                            return true;
                        },
                        success:function(data)
                        {
                            if(data["status"] == "success")
                            {
                                alert('操作成功');
                                $("#tip").html("<span style='color:blueviolet'>恭喜，评论成功！</span>");
                                location.reload();
                            }
                            else
                            {
                                $("#tip").html("<span style='color:red'>失败，请重试</span>");
                                alert('操作失败');
                            }
                        },
                        error:function()
                        {
                            alert('请求出错');
                        },
                        complete:function()
                        {
                            // $('#tips').hide();
                        }
                    });

            return false;

        }

        function play(movieId, cinemaId){
            if(!movieId)
            {
                alert('Error！');
                return false;
            }

            if(!cinemaId)
            {
                alert('Error！');
                return false;
            }

            $.ajax(
                    {
                        url: "/play/list/"+movieId+"/"+cinemaId,
                        type: "get",
                        beforeSend:function()
                        {
                            $("#tip").html("<span style='color:blue'>正在处理...</span>");
                            return true;
                        },
                        success:function(data)
                        {
                            for (var i=0;i<data.length;i++){
                                var $tr=$('<tr></tr>');
                                $("#playTable").append($tr);
                                var date = new Date(parseInt(data[i].showtime,
                                        10));
                                var $td1=$('<td>'+getDateTime(date)+'</td>');
                                $tr.append($td1);
                                var $td2=$('<td>'+data[i].videoHall+'</td>');
                                $tr.append($td2);
                                var $td3=$('<td>'+data[i].seatNum+'</td>');
                                $tr.append($td3);
                                var $td4=$('<td>  <a class="btn" href="/ord/add/'+data[i].id+'">购票</a></td>');
                                $tr.append($td4);
                            }
                        },
                        error:function()
                        {
                            alert('请求出错');
                        },
                        complete:function()
                        {
                            // $('#tips').hide();
                        }
                    });

            return false;
        }

        function pay(id) {
            if(!id)
            {
                alert('Error！');
                return false;
            }
            $.ajax(
                    {
                        url: "/order/add/"+id,
                        type: "get",
                        beforeSend:function()
                        {
                            $("#tip").html("<span style='color:blue'>正在处理...</span>");
                            return true;
                        },
                        success:function(data)
                        {

                        },
                        error:function()
                        {
                            alert('请求出错');
                        },
                        complete:function()
                        {
                            // $('#tips').hide();
                        }
                    });

            return false;
        }


        function getDateTime(date) {

            var year = date.getFullYear();

            var month = date.getMonth() + 1;

            var day = date.getDate();

            var hh = date.getHours();

            var mm = date.getMinutes();

            var ss = date.getSeconds();

            return year + "-" + month + "-" + day + " " + hh + ":" + mm + ":" + ss;
        }
    </script>
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
                <a href="/ord/list">
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
<!--bottom-header-->
<!--start-breadcrumbs-->
<!--end-breadcrumbs-->
<!--start-single-->
<div class="single contact">
    <div class="container">
        <div class="single-main">
            <div class="col-md-9 single-main-left">
                <div class="sngl-top">
                    <div class="col-md-5 single-top-left">
                        <div class="flexslider">
                            <ul class="slides">
                                <li data-thumb="${movie.pic}">
                                    <div class="thumb-image"> <img src="${movie.pic}" data-imagezoom="true" class="img-responsive" alt=""/> </div>
                                </li>
                            </ul>
                        </div>
                        <!-- FlexSlider -->
                        <script src="/assets/js/imagezoom.js"></script>
                        <script defer src="/assets/js/jquery.flexslider.js"></script>
                        <link rel="stylesheet" href="/assets/css/flexslider.css" type="text/css" media="screen" />

                        <script>
                            // Can also be used with $(document).ready()
                            $(window).load(function() {
                                $('.flexslider').flexslider({
                                    animation: "slide",
                                    controlNav: "thumbnails"
                                });
                            });
                        </script>
                    </div>
                    <div class="col-md-7 single-top-right">
                        <div class="single-para simpleCart_shelfItem">
                            <h2>${movie.name}</h2>
                            <h5 class="item_price">$ 95.00</h5>
                            <h6>导演：${movie.director}</h6>
                            <h6>主演：${movie.stars}</h6>
                            <h6>简介：</h6>
                            <p>${movie.summary}</p>
                        </div>
                    </div>
                    <div class="clearfix"> </div>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
<!--end-single-->
<!--information-starts-->
<!--information-end-->
<div class="single contact">
    <div class="container">
        <div class="headdings">
            <h3 class="ghj">电影院列表</h3>
            <div class="bs-example">
                <table class="table">
                    <tbody>
                    <#list cinemaList as cinema>
                    <tr> <td> <h4 class="head" id="h4.-bootstrap-heading">${cinema.name}</h4> <h5 class="head" id="h5.-bootstrap-heading">地址${cinema.summary}</h5> </td><td> <a class="add-cart item_add" data-toggle="modal" data-target="#play" onclick="play(${movie.id},${cinema.id})">购票</a></td> </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="single contact">
    <div class="container">
        <div class="module">
            <div class="mod-title">
                <h3>热门短评</h3>
            </div>
            <div class="mod-content">
                <div class="comment-list-container" data-hot=10>

                    <ul>
                        <#list pingjiaList as pingjia>
                        <li class="comment-container ">
                            <div class="main">
                                <div class="main-header clearfix">
                                    <div class="user">
                                        <span class="name">${pingjia.user.username}</span>
                                    </div>
                                    <div class="time" >
                                        <span>${pingjia.createDate?string('yyyy-MM-dd')!'-'}</span>
                                    </div>
                                </div>
                                <div class="comment-content">${pingjia.content}</div>
                            </div>
                        </li>
                        </#list>
                    </ul>
                </div>
            </div>
            <a class="comment-entry" data-toggle="modal" data-target="#pingjia">写短评</a>
        </div>
    </div>
</div>

<div class="modal fade" id="pingjia" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    短评价
                </h4>
            </div>
            <form id="form_data">
                <div class="modal-body">
                    内容: <textarea name="content" rows="3" cols="50"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" onclick="add_info(${movie.id})" class="btn btn-primary">
                        提交
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="play" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    放映列表
                </h4>
            </div>
            <div class="modal-body">
                <table class="table">
                    <caption>放映信息</caption>
                    <thead>
                    <tr>
                        <th>放映时间</th>
                        <th>放映厅</th>
                        <th>剩余座位数</th>
                        <th>确认购票</th>
                    </tr>
                    </thead>
                    <tbody id="playTable">
                    </tbody>
                </table>
            </div>
            </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
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
