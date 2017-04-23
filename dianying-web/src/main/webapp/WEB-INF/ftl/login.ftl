<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>消息管理系统登陆</title>
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
    <form id="form" action="/login" method="post">

        <h2 class="form-signin-heading">请登录</h2>
        <div class="form-group">
            <input name="username"  type="text" class="form-control form-signin-name" placeholder="User name" autofocus>
        </div>
        <div class="form-group">
            <input name="password"  type="password" class="form-control form-signin-password" placeholder="Password">
        </div>
        <#if errorMsg??>
        <div class="alert alert-danger admin-login-alert">
            <strong class="admin-login-alert-msg">${errorMsg}</strong>
        </div>
        </#if>
        <label class="checkbox">
            <input type="checkbox" value="remember-me"> 记住我
        </label>
        <button  type="submit" class="btn btn-lg btn-primary btn-block " id="sign-in-btn" onclick="$('#form').parsley( 'validate' );">登陆</button>

    </form>
</div>
</div> <!-- /container -->
</body>
</html>

