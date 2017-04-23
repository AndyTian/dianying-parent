<#import "/macro.ftl" as m>
<@m.page_header title='后台' />
<div id="page-content-wrapper">
    <div id="page-title">
        <h3>
            订单列表
        </h3>
        <div id="breadcrumb-right">
            <div class="float-right">
            </div>
        </div>

    </div>

    <div id="page-content">
        <table class="table" id="exp">
            <thead>
            <tr>
                <th>用户名</th>
                <th>放映时间</th>
                <th>放映厅</th>
                <th>取票码</th>
                <th>支付状态</th>
            </tr>
            </thead>
            <tbody>
            <#list list as order >
            <tr >
                <td>
                    ${order.user.name}
                </td>
                <td>
                    ${order.play.showtime?string('yyyy-MM-dd HH:mm')!'-'}
                </td>
                <td>
                    ${order.play.videoHall}
                </td>
                <td>
                    ${order.tickerNum}
                </td>
                <td>
                    ${order.payState}
                </td>
                <td class="center">
                    <div class="dropdown">
                        <a href="javascript:;" title="" class="btn medium bg-blue" data-toggle="dropdown">
	                                        <span class="button-content">
	                                            <i class="glyph-icon font-size-11 icon-cog"></i>
	                                            <i class="glyph-icon font-size-11 icon-chevron-down"></i>
	                                        </span>
                        </a>
                        <ul class="dropdown-menu float-right">
                            <li>
                                <a href="javascript:;" title="" onclick="initUpdate(${order.id});">
                                    <i class="glyph-icon icon-edit mrg5R"></i>
                                    修改
                                </a>
                                <a href="javascript:;" title="" onclick="initDelete(${order.id});">
                                    <i class="glyph-icon icon-remove mrg5R"></i>
                                    删除
                                </a>
                            </li>
                        </ul>
                    </div>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
        <div class="button-group center-div">
        ${pagerHelper.content}
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function() {
        <#if msg!=null>
            $.jGrowl("${msg}", {sticky:!1,position:'bottom-right',theme:'bg-green'});
        </#if>


        });
        function searchDate(){
            if($("#nameInput").val() != ""){
                $("#pageNumber").val("1");
                $('#form').trigger('submit');
            }
        }
    </script>

