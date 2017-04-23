<form id="form-update-presale" action="/play/update" method="post" class="mrg10A"  enctype="multipart/form-data">
    <div class="form-row" >
        <div class="form-label col-md-3">
            <label for="">
                电影名:
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-input col-md-6">
            <select name="movieId" >
                <option value="">全部</option>
            <#list movieList as movie>
                <option value="${movie.id!''}" <#if movie.id == "${play.movieId!''}">selected</#if>>${movie.name!''}</option>
            </#list>
            </select>
        </div>
    </div>
    <div class="form-row" >
        <div class="form-label col-md-3">
            <label for="">
                影院名:
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-input col-md-6">
            <select name="cinemaId" >
                <option value="">全部</option>
            <#list cinemaList as cinema>
                <option value="${cinema.id!''}" <#if cinema.id == "${play.cinemaId!''}">selected</#if>>${cinema.name!''}</option>
            </#list>
            </select>
        </div>
    </div>
    <div class="form-row" >
        <div class="form-label col-md-3">
            <label for="">
                放映厅:
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-input col-md-6">
            <input type="text" name="videoHall" value="${play.videoHall}">
        </div>
    </div>
    <div class="form-row" >
        <div class="form-label col-md-3">
            <label for="">
                上映时间:
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-input col-md-6">
            <input type="text" name="showtime" value="${play.showtime?string('yyyy-MM-dd HH:mm')!'-'}" class="parsley-validated"  data-trigger="focus"  onClick="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM-dd HH:mm'})" style="clear:both;overflow:hidden;display:block"/>
        </div>
    </div>
    <div class="form-row" >
        <div class="form-label col-md-3">
            <label for="">
                剩余票数:
                <span class="required">*</span>
            </label>
        </div>
        <div class="form-input col-md-6">
            <input type="text" name="seatNum" value="${play.seatNum}">
        </div>
    </div>


</form>



