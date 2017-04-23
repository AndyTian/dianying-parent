<form id="form-update-presale" action="/admin/movie/update" method="post" class="mrg10A"  enctype="multipart/form-data">
   <input type="hidden" name="pic" id="pic" value="${movie.pic}"/>
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
            <img src="${movie.pic}" height="100px" width="60px"/>
        </div>
    </div>
    <div class="form-row" >
        <div class="form-label col-md-3">
            <label for="">
                电影名称:
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-input col-md-6">
           <input type="text" name="name" value="${movie.name}"/>
        </div>
    </div>
    <div class="form-row" >
        <div class="form-label col-md-3">
            <label for="">
                导演:
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-input col-md-6">
            <input type="text" name="director" value="${movie.director}"/>
        </div>
    </div>
    <div class="form-row" >
        <div class="form-label col-md-3">
            <label for="">
                主演:
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-input col-md-6">
            <input type="text" name="stars" value="${movie.stars}"/>
        </div>
    </div>
    <div class="form-row" >
        <div class="form-label col-md-3">
            <label for="">
                上映日期:
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-input col-md-6">
            <input type="text"  name="showtime" class="parsley-validated"  data-trigger="focus" value="${movie.showtime?string('yyyy-MM-dd')!'-'}" onClick="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM-dd'})" style="clear:both;overflow:hidden;display:block"/>
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


