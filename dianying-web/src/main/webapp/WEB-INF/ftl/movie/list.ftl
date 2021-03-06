<#import "/macro.ftl" as m>
<@m.page_header title='后台' />
<div id="page-content-wrapper">
    <div id="page-title">
        <h3>
            电影列表
        </h3>
        <div id="breadcrumb-right">
            <div class="float-right">
            </div>
        </div>

    </div>
    <div class="form-input col-md-2">
        <a href="javascript:;" class="btn large primary-bg radius-all-4" id="search" title="Validate!" onclick="initAdd();">
			            <span class="button-content">
			                 添加电影
			            </span>
        </a>
    </div>

    <div id="page-content">
        <table class="table" id="exp">
            <thead>
            <tr>
                <th>电影名称</th>
                <th>图片链接</th>
                <th>导演</th>
                <th>主演</th>
                <th>上映时间</th>
            </tr>
            </thead>
            <tbody>
            <#list list as movie >
            <tr >
                <td>
                    ${movie.name}
                </td>
                <td>
                    ${movie.pic}
                </td>
                <td>
                    ${movie.director}
                </td>
                <td>
                    ${movie.stars}
                </td>
                <td>
                ${(movie.showtime?string('yyyy-MM-dd'))!'-'}
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
                                <a href="javascript:;" title="" onclick="initUpdate(${movie.id});">
                                    <i class="glyph-icon icon-edit mrg5R"></i>
                                    修改
                                </a>
                                <a href="javascript:;" title="" onclick="initDelete(${movie.id});">
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

        function initUpdate(id){
            $.get( "/admin/movie/showUpdate/" + id, function( data ){

                var html = '<div id="dialog" class="hide" title="修改电影信息"><div class="mrg10A">' + data + '</div></div>';
                $( html ).dialog({
                    resizable:!0,
                    minWidth:600,
                    minHeight:300,
                    modal:!0,
                    dialogClass:"modal-dialog",
                    closeOnEscape:!0,
                    close : function() {
                        $( this ).dialog( "destroy" );
                    },
                    buttons: {
                        提交: function() {
                            var valid = $('#dialog form').parsley( 'validate' );
                            if(!valid){
                                return;
                            }

                            $.post( '/admin/movie/update/' + id, $( '#dialog form' ).serialize() )
                                    .done(function(data){
                                        if(data["status"] == "success"){
                                            $.jGrowl("修改成功", {sticky:!1,position:"top-right",theme:"bg-green"});
                                            $( "#dialog" ).dialog( "close" );
                                            document.location.reload();
                                        }else{
                                            $.jGrowl(data["message"], {sticky:!1,position:"top-right",theme:"bg-red"});
                                        }

                                    })
                                    .fail(function(){
                                        $.jGrowl("修改失败", {sticky:!1,position:"top-right",theme:"bg-red"});

                                    });
                        }
                    }
                });
            });

        };

        function initAdd(){
            $.get( "/admin/movie/showAdd", function( data ){

                var html = '<div id="dialog" class="hide" title="添加"><div class="mrg10A">' + data + '</div></div>';
                $( html ).dialog({
                    resizable:!0,
                    minWidth:600,
                    minHeight:300,
                    modal:!0,
                    dialogClass:"modal-dialog",
                    closeOnEscape:!0,
                    close : function() {
                        $( this ).dialog( "destroy" );
                    },
                    buttons: {
                        提交: function() {
                            var valid = $('#dialog form').parsley( 'validate' );
                            if(!valid){
                                return;
                            }


                            $.post( '/admin/movie/add', $( '#dialog form' ).serialize() )
                                    .done(function(data){
                                        if(data["status"] == "success"){
                                            $.jGrowl("成功", {sticky:!1,position:"top-right",theme:"bg-green"});
                                            $( "#dialog" ).dialog( "close" );
                                            document.location.reload();
                                        }else{
                                            $.jGrowl(data["message"], {sticky:!1,position:"top-right",theme:"bg-red"});
                                        }

                                    })
                                    .fail(function(){
                                        $.jGrowl("失败", {sticky:!1,position:"top-right",theme:"bg-red"});
                                    });
                        }
                    }
                });
            });

        };

        function initDelete(id){
            $.messager.confirm( "提示", "确认删除吗?", function(){
                $.post( '/admin/movie/delete/'+id)
                        .done(function(data){
                            if(data["status"] == "success"){
                                $.jGrowl("删除成功", {sticky:!1,position:"top-right",theme:"bg-green"});
                                $( "#dialog" ).dialog( "close" );
                                location.reload();
                            }else{
                                $.jGrowl(data["message"], {sticky:!1,position:"top-right",theme:"bg-red"});
                            }

                        })
                        .fail(function(){
                            $.jGrowl("删除失败", {sticky:!1,position:"top-right",theme:"bg-red"});

                        });
            });
        }
    </script>

