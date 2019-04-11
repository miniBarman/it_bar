<#import "parts/common.ftl" as c>

<@c.page>

<h2>Добавление нового коктейля</h2>
<br>

<form method="post" enctype="multipart/form-data">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />

    <div class="form-group">
        <label for="coctailName">Название коктейля</label>
        <input type="name" name="name" class="form-control" id="coctailName" placeholder="Название">
    </div>
    <div class="form-group">
        <label for="coctailDescription">Описание коктейля</label>
        <textarea class="form-control" type="text" name="description" id="coctailDescription" rows="4" placeholder="Описание"></textarea>
    </div>
    <div class="custom-file">
        <input type="file" class="custom-file-input" id="coctailPic" name="file">
        <label class="custom-file-label" for="coctailPic">Изображение коктейля</label>
    </div>
    <input type="hidden" name="ingredients" value="1:50:мл/260:40:гр" />

    <button type="submit" class="btn btn-primary mt-3">Добавить</button>
</form>

</@c.page>