<#import "/macro.ftl" as m>
<@m.page_header title='后台' />
<div id="page-content-wrapper">
    <div id="page-title">
        <h3>
            评价列表
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
                <th>评价内容</th>
            </tr>
            </thead>
            <tbody>
            <#list list as pingjia >
            <tr >
                <td>
                    ${pingjia.user.id}
                </td>
                <td>
                    ${pingjia.content}
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

