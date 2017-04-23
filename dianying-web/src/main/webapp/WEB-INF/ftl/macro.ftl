<#macro page_header title='' >
<!-- AUI Framework -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>${title} - 票务管理系统</title>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>

    <!-- Favicons -->

    <link rel="apple-touch-icon-precomposed" sizes="144x144"
          href="/assets/images/icons/apple-touch-icon-144-precomposed.png"/>
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
          href="/assets/images/icons/apple-touch-icon-114-precomposed.png"/>
    <link rel="apple-touch-icon-precomposed" sizes="72x72"
          href="/assets/images/icons/apple-touch-icon-72-precomposed.png"/>
    <link rel="apple-touch-icon-precomposed" href="/assets/images/icons/apple-touch-icon-57-precomposed.png"/>
    <link rel="shortcut icon" href="/assets/images/icons/favicon.png"/>

    <!--[if lt IE 9]>
    <script src="/assets/js/minified/core/html5shiv.min.js"></script>
    <script src="/assets/js/minified/core/respond.min.js"></script>
    <![endif]-->

    <!-- Fides Admin CSS Core -->

    <link rel="stylesheet" type="text/css" href="/assets/css/minified/aui-production.min.css"/>

    <!-- Theme UI -->

    <link id="layout-theme" rel="stylesheet" type="text/css"
          href="/assets/themes/minified/fides/color-schemes/dark-blue.min.css"/>

    <!-- Fides Admin Responsive -->

    <link rel="stylesheet" type="text/css" href="/assets/themes/minified/fides/common.min.css"/>
    <link rel="stylesheet" type="text/css" href="/assets/themes/minified/fides/responsive.min.css"/>


    <link rel="stylesheet" type="text/css" href="/assets/css/jquery.tree-multiselect.min.css"/>


    <!-- Fides Admin JS -->

    <script type="text/javascript" src="/assets/js/minified/aui-production.js"></script>
    <script type="text/javascript" src="/assets/js/messager.js"></script>
    <script type="text/javascript" src="/assets/js/util.js"></script>
    <script type="text/javascript" src="/assets/timeplug/WdatePicker.js"></script>
    
    <!-- 自己写的  JS -->
    
    <script type="text/javascript" src="/assets/js/dateUtil.js"></script>
    
    
     <#--<script type="text/javascript" src="/assets/js/jquery-1.9.1.min.js"></script>-->
    <script type="text/javascript" src="/assets/js/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="/assets/js/jquery.iframe-transport.js"></script>
    <script type="text/javascript" src="/assets/js/jquery.fileupload.js"></script>
    <script type="text/javascript" src="/assets/js/jquery.xdr-transport.js"></script>

    <script>
        jQuery(window).load(
                function () {
                    var wait_loading = window.setTimeout(function () {
                                $('#loading').slideUp('fast');
                                jQuery('body').css('overflow', 'auto');
                            }, 1000
                    );
                });
    </script>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body style="overflow: hidden;">


<div id="loading" class="ui-front loader ui-widget-overlay bg-white opacity-100">
    <img src="/assets/images/loader-dark.gif" alt=""/>
</div>

<div id="page-wrapper" class="demo-example">

<div class="theme-customizer">
    <a href="javascript:;" class="change-theme-btn" title="Change theme">
        <i class="glyph-icon icon-cog"></i>
    </a>

    <div class="theme-wrapper">

        <div class="popover-title">Boxed layout options</div>
        <div class="pad10A clearfix">
            <a class="fluid-layout-btn hidden bg-blue-alt medium btn" href="javascript:;" title=""><span
                    class="button-content">Full width layout</span></a>
            <a class="boxed-layout-btn bg-blue-alt medium btn" href="javascript:;" title=""><span
                    class="button-content">Boxed layout</span></a>
        </div>
        <div class="popover-title">Boxed layout backgrounds</div>
        <div class="pad10A clearfix">
            <a href="javascript:;" class="choose-bg" boxed-bg="#000" style="background: #000;" title=""></a>
            <a href="javascript:;" class="choose-bg" boxed-bg="#333" style="background: #333;" title=""></a>
            <a href="javascript:;" class="choose-bg" boxed-bg="#666" style="background: #666;" title=""></a>
            <a href="javascript:;" class="choose-bg" boxed-bg="#888" style="background: #888;" title=""></a>
            <a href="javascript:;" class="choose-bg" boxed-bg="#383d43" style="background: #383d43;" title=""></a>
            <a href="javascript:;" class="choose-bg" boxed-bg="#fafafa"
               style="background: #fafafa; border: #ccc solid 1px;" title=""></a>
            <a href="javascript:;" class="choose-bg" boxed-bg="#fff" style="background: #fff; border: #eee solid 1px;"
               title=""></a>
        </div>
        <div class="popover-title">Color schemes</div>
        <div class="pad10A clearfix change-layout-theme">
            <p class="font-gray-dark font-size-11 pad0B">More color schemes will be available soon!</p>

            <div class="divider mrg10T mrg10B"></div>
            <a href="javascript:;" class="choose-theme" layout-theme="dark-blue" title="">
                <span style="background: #2381E9;"></span>
            </a>
            <a href="javascript:;" class="choose-theme opacity-30 mrg15R" layout-theme="white-blue" title="">
                <span style="background: #2381E9;"></span>
            </a>
            <a href="javascript:;" class="choose-theme" layout-theme="dark-green" title="D">
                <span style="background: #78CE12;"></span>
            </a>
            <a href="javascript:;" class="choose-theme opacity-30 mrg15R" layout-theme="white-green" title="D">
                <span style="background: #78CE12;"></span>
            </a>
            <a href="javascript:;" class="choose-theme" layout-theme="dark-orange" title="">
                <span style="background: #FF6041;"></span>
            </a>
            <a href="javascript:;" class="choose-theme opacity-30 mrg15R" layout-theme="white-orange" title="">
                <span style="background: #FF6041;"></span>
            </a>
        </div>

    </div>
</div>

<div id="page-header" class="clearfix">
    <div id="header-logo">
        <a href="javascript:;" class="tooltip-button" data-placement="bottom" title="Close sidebar" id="close-sidebar">
            <i class="glyph-icon icon-caret-left"></i>
        </a>
        <a href="javascript:;" class="tooltip-button hidden" data-placement="bottom" title="Open sidebar"
           id="rm-close-sidebar">
            <i class="glyph-icon icon-caret-right"></i>
        </a>
        <a href="javascript:;" class="tooltip-button hidden" title="Navigation Menu" id="responsive-open-menu">
            <i class="glyph-icon icon-align-justify"></i>
        </a>
        票务管理系统
    </div>
    <div class="user-profile dropdown">
        <a href="javascript:;" title="" class="user-ico clearfix" data-toggle="dropdown">
            <img width="36" src="/assets/images/gravatar.jpg" alt=""/>
            <span>${session_miz_usersession}</span>
            <i class="glyph-icon icon-chevron-down"></i>
        </a>
        <ul class="dropdown-menu float-right">
            <li>
                <a href="/admin/logout" title="">
                    <i class="glyph-icon icon-signout font-size-13 mrg5R"></i>
                    <span class="font-bold">退出</span>
                </a>
            </li>
            <li>
	            <a href="${cas_account_url}/rolemanagement/modifypassword" title="">
	                <i class="glyph-icon icon-edit font-size-13 mrg5R"></i>
	                <span class="font-bold">修改密码</span>
	            </a>
            </li>
        </ul>
    </div>
    <div class="top-icon-bar">
    </div>

</div><!-- #page-header -->

<div id="page-sidebar" class="scrollable-content">

    <div id="sidebar-menu">
        <ul>
        
        
         <li>
         <a href="javascript:;" title="Users">
                    <i class="glyph-icon icon-check"></i>
                后台管理
          </a>
          
          <ul>
                 <li>
                    <a href="/admin/user/list?pageNumber=1&pageSize=2" title="Users" title="Users" id="paylogLink">
                        <i class="glyph-icon icon-unlink"></i>
               	       用户列表
                    </a>
                </li>

              <li>
                  <a href="/admin/movie/list?pageNumber=1&pageSize=2" title="Users" title="Users" id="paylogLink">
                      <i class="glyph-icon icon-unlink"></i>
                      电影列表
                  </a>
              </li>

              <li>
                  <a href="/admin/cinema/list?pageNumber=1&pageSize=2" title="Users" title="Users" id="paylogLink">
                      <i class="glyph-icon icon-unlink"></i>
                      影院列表
                  </a>
              </li>

              <li>
                  <a href="/admin/message/list?pageNumber=1&pageSize=2" title="Users" title="Users" id="paylogLink">
                      <i class="glyph-icon icon-unlink"></i>
                      消息列表
                  </a>
              </li>

              <li>
                  <a href="/admin/ord/list?pageNumber=1&pageSize=2" title="Users" title="Users" id="paylogLink">
                      <i class="glyph-icon icon-unlink"></i>
                      订单列表
                  </a>
              </li>

              <li>
                  <a href="/admin/pingjia/list?pageNumber=1&pageSize=2" title="Users" title="Users" id="paylogLink">
                      <i class="glyph-icon icon-unlink"></i>
                      评价列表
                  </a>
              </li>

              <li>
                  <a href="/admin/play/list?pageNumber=1&pageSize=2" title="Users" title="Users" id="paylogLink">
                      <i class="glyph-icon icon-unlink"></i>
                      放映列表
                  </a>
              </li>
                 
             </ul>
        </li> 

        
                       
        </ul>
    </div>

</div><!-- #page-sidebar -->

<div id="page-content">
</#macro>


<#macro page_footer>




</div><!-- #page-content -->
</div><!-- #page-main -->
</div><!-- #page-wrapper -->

</body>


</html>
</#macro>



<#macro p page totalpage>
    <@compress single_line=true>
        <#if (request.queryString)??>
            <#assign requestParams=request.queryString?replace("\\&?p=(\\d+)\\&?","","r") />
            <#if requestParams?has_content>
                <#assign requestParams = '&' + requestParams />
            </#if>
        </#if>
        <#assign currentPage=page?number >
        <#if currentPage-4 gt 0>
            <#assign beginPage = currentPage-4 />
        <#else>
            <#assign beginPage = 1 />
        </#if>
        <#if totalpage-currentPage lt 4>
            <#assign beginPage = beginPage - (4-totalpage + currentPage)  />
            <#if beginPage lt 1>
                <#assign beginPage = 1 />
            </#if>
        </#if>
        <#if currentPage-1 gt 0>
        <a class="page_a page_prev" href="?p=${currentPage-1}${requestParams}"></a>
        <#else>
        <span class="page_a page_prev" class="disabled"></span>
        </#if>
        <#if currentPage gt 5 && totalpage gt 10 >
        <a class="page_a" href="?p=1${requestParams}">1</a> <span>...</span>
        </#if>
        <#assign endPage=beginPage+8 />
        <#if endPage gt totalpage>
            <#assign endPage=totalpage />
            <#assign beginPage=endPage-8 />
        </#if>
        <#if beginPage lt 1>
            <#assign beginPage = 1 />
        </#if>
        <#if endPage lt 1>
            <#assign endPage = 1 />
        </#if>
        <#list beginPage..endPage as x>
            <#if x == currentPage>
            <span class="current page_a page_a_now">${x}</span>
            <#else>
            <a class="page_a" href="?p=${x}${requestParams}">${x}</a>
            </#if>
        </#list>
        <#if currentPage lte totalpage - 5 && totalpage gt 10>
        <span class="page_dots">...</span> <a href="?p=${totalpage}${requestParams}" class="page_a">${totalpage}</a>
        </#if>
        <#if currentPage lt totalpage>
        <a class="page_a page_next" href="?p=${currentPage+1}${requestParams}"></a>
        <#else>
        <span class="disabled page_a page_next"></span>
        </#if>
    </@compress>
</#macro>

