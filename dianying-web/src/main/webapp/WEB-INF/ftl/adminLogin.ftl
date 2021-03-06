<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>消息管理系统登陆</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />


    <link rel="stylesheet" type="text/css" href="/assets/css/minified/aui-production.min.css" />
    <link id="layout-theme" rel="stylesheet" type="text/css" href="/assets/themes/minified/fides/color-schemes/dark-blue.min.css" />
    <script type="text/javascript" src="/assets/js/minified/aui-production.js"></script>
    <script>
        jQuery(window).load(
        	function(){
                var wait_loading = window.setTimeout( function(){
                            $('#loading').slideUp('fast');
                            jQuery('body').css('overflow','auto');
                },1000
             );
         });
    </script>
</head>
<body style="overflow: hidden;">

<div id="loading" class="ui-front loader ui-widget-overlay bg-white opacity-100">
    <img src="/assets/images/loader-dark.gif" alt="" />
</div>
<div id="page-wrapper" class="demo-example">
<div id="page-header" class="clearfix">
</div><!-- #page-header -->
<div id="page-content">
<div class="pad20A mrg25T">
    <div class="row mrg25T">
        <form id="form" action="/admin/login" class="col-md-4 center-margin form-vertical mrg25T" method="POST" enctype="multipart/form-data">
            <div class="ui-dialog modal-dialog mrg25T" id="login-form" style="position: relative !important;">
                <div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
                    <span class="ui-dialog-title">票务管理系统登陆</span>
                </div>
                <div class="pad20A pad0B ui-dialog-content ui-widget-content">
                	<#if errorMsg??>
                		<div class="infobox error-bg mrg20B">
		                    <p>${errorMsg}</p>
		                </div>
                	</#if>
                    <div class="form-row">
                        <div class="form-label col-md-2">
                            <label for="">
                                用户名:
                            </label>
                        </div>
                        <div class="form-input col-md-10">
                            <div class="form-input-icon">
                                <i class="glyph-icon icon-phone ui-state-default"></i>
                                <input placeholder="用户名" value="" type="text" name="username" id="username" data-required="true">
                            </div>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-label col-md-2">
                            <label for="">
                                密码:
                            </label>
                        </div>

                        <div class="form-input col-md-10">
                            <div class="form-input-icon">
                                <i class="glyph-icon icon-unlock-alt ui-state-default"></i>
                                <input placeholder="密码" value="" type="password" name="password" id="pass" data-required="true">
                            </div>
                        </div>
                    </div>
                    <#if ipIllegal == true>
                        <input type="hidden" name="certify" value="true"/>
                        <div class="form-row">
                            <div class="form-label col-md-2">
                                <label for="">
                                    验证码:
                                </label>
                            </div>
                            <div class="form-input col-md-10">
                                <div class="form-input-icon">
                                    <i class="glyph-icon icon-unlock-alt ui-state-default"></i>
                                    <input placeholder="验证码" value="" type="text" name="code" id="code" style="width: auto">
                                    <#--<input type="button" onclick="sendCode()" class="btn" style="width: auto" value="发送验证码">-->
                                    <a href="javascript:;" class="btn large primary-bg radius-all-2" id="search" title="Validate!" onclick="sendCode();">
                                    <span class="button-content">
                                       发送验证码
                                    </span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    <#else>
                        <input type="hidden" name="certify" value="false"/>
                    </#if>
                </div>
                <div class="ui-dialog-buttonpane text-center">

                    <button type="submit" class="btn large primary-bg text-transform-upr font-bold font-size-11 radius-all-4" id="demo-form-valid" title="Validate!"
                    	onclick="$('#form').parsley( 'validate' );">
                        <span class="button-content">
                            登陆
                        </span>
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>

</div><!-- #page-content -->
</div><!-- #page-main -->
</div><!-- #page-wrapper -->
</body>
<script>

    function sendCode(){
        $.get("/sendcode", {username:$("#username").val() }, function(data){
            if("" != data){
                alert(data);
            }
        });
    }

</script>
</html>
