<form id="form-update-presale" action="/message/update" method="post" class="mrg10A"  enctype="multipart/form-data">
    <div class="form-row" >
        <div class="form-label col-md-3">
            <label for="">
                消息通知人:
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-input col-md-6">
            <select name="userId" >
                <option value="">全部</option>
            <#list userList as user>
                <option value="${user.id!''}" <#if user.id == "${message.userId!''}">selected</#if>>${user.name!''}</option>
            </#list>
            </select>
        </div>
    </div>
    <div class="form-row" >
        <div class="form-label col-md-3">
            <label for="">
                消息内容:
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-input col-md-6">
            <input type="text" name="summary" value="${message.content}"/>
        </div>
    </div>


</form>

<script>
    $('#uploadFile').fileupload({
        dataType: 'json',

        done: function (e, data) {
            $('#picture').html("<img src='"+data.result.url+"' height='100px' width='60px'/>");
            $('#pic').val(data.result.url);
        }
    });
</script>


