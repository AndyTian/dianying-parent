<#import "/macro.ftl" as m>
<@m.page_header title='后台' />
<div id="page-content-wrapper">
    <div id="page-title">
        <h3>
            用户列表
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
                <th>昵称</th>
                <th>真实姓名</th>
                <th>性别</th>
                <th>创建时间</th>
            </tr>
            </thead>
            <tbody>
            <#list list as user >
            <tr >
                <td>
                    ${user.username}
                </td>
                <td>
                    ${user.nickname}
                </td>
                <td>
                    ${user.name}
                </td>
                <td>
                    ${user.gender}
                </td>
                <td>
                ${(user.createDate?string('yyyy-MM-dd HH:mm'))!'-'}
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

        function initQuery(id){
            $.get( "/notifyLog/initQuery/"+ id, function( data ){
                var html = '<div id="dialog" class="hide" title="查看日志详情"><div class="mrg10A">' + data + '</div></div>';
                $( html ).dialog({
                    resizable:!0,
                    minWidth:600,
                    minHeight:500,
                    modal:!0,
                    dialogClass:"modal-dialog",
                    closeOnEscape:!0,
                    close : function() {
                        $( this ).dialog( "destroy" );
                    }
                });
            });
        }
    </script>

