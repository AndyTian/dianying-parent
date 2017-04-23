<form id="form-add-presale" action="/cinema/add" method="post" class="mrg10A"  enctype="multipart/form-data">
   <input type="hidden" name="pic" id="pic"/>
    <div class="form-row">
        <div class="form-label col-md-3">
            <label for="">
                上传图片:
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-input col-md-6">
            <input type ="file" name ="uploadFile" id="uploadFile" data-url="/pic/upload" multiple>
        </div>
        <div class="form-label col-md-3" id="picture">
            <img src="http://localhost:8080/uploadPic/1484117229533025.jpg" height="100px" width="60px"/>
        </div>
    </div>
    <div class="form-row" >
        <div class="form-label col-md-3">
            <label for="">
                电影院名称:
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-input col-md-6">
           <input type="text" name="name"/>
        </div>
    </div>
    <div class="form-row" >
        <div class="form-label col-md-3">
            <label for="">
                影院简介:
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-input col-md-6">
            <input type="text" name="summary"/>
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


