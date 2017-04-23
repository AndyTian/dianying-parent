<form id="form-add-presale" action="/message/add" method="post" class="mrg10A"  enctype="multipart/form-data">
    <div class="form-row" >
        <div class="form-label col-md-3">
            <label for="">
                消息接收人:
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-input col-md-6">
            <select name="userId" >
                <option value="">全部</option>
            <#list userList as user>
                <option value="${user.id!''}">${user.name!''}</option>
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
            <input type="text" name="content"/>
        </div>
    </div>




</form>




