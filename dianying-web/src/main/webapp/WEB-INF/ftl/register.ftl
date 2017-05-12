<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>爱丽丝影院注册</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />


    <link rel="stylesheet" type="text/css" href="/assets/css/bootstrap.css" />
    <style>
        body{
            margin-left:auto;
            margin-right:auto;
            margin-TOP:100PX;
            width:20em;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form id="form" action="/register" method="post" enctype="multipart/form-data">

            <h2 class="form-signin-heading">账号注册</h2>
            <div class="form-group">
                <input name="username"  type="text" class="form-control form-signin-name" placeholder="用户名" autofocus>
            </div>
            <div class="form-group">
                <input name="password"  type="password" class="form-control form-signin-password" placeholder="密码">
            </div>
            <div class="form-group">
                <input name="nickname"  type="text" class="form-control" placeholder="昵称">
            </div>
            <div class="form-group">
                <input name="name"  type="text" class="form-control" placeholder="真实姓名">
            </div>
            <div class="form-group">
                <input type="radio" name="gender" class="form-control" value="male" checked> 男
                <input type="radio" name="gender" class="form-control" value="female"> 女
            </div>
            <div class="form-group">
                <input name="phonenumber"  type="text" class="form-control" placeholder="手机号码">
            </div>
        <#if errorMsg??>
            <div class="alert alert-danger admin-login-alert">
                <strong class="admin-login-alert-msg">${errorMsg}</strong>
            </div>
        </#if>
            <button  type="submit" class="btn btn-lg btn-primary btn-block " id="sign-in-btn" onclick="$('#form').parsley( 'validate' );">注册</button>

        </form>
    </div>
</div> <!-- /container -->
</body>
</html>