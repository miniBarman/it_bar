<#import "parts/common.ftl" as c>

<@c.page>

<h2>Создание нового коктейля</h2>
<br>

<form method="post" enctype="multipart/form-data">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />

    <div class="form-group">
        <label for="coctailName">Название коктейля</label>
        <input type="name" name="name" class="form-control" id="coctailName" placeholder="Название" required>
    </div>
    <div class="form-group">
        <label for="coctailDescription">Описание коктейля</label>
        <textarea class="form-control" type="text" name="description" id="coctailDescription" rows="4" placeholder="Описание"></textarea>
    </div>
    <div class="custom-file">
        <input type="file" class="custom-file-input" id="coctailPic" name="file">
        <label class="custom-file-label" for="coctailPic">Изображение коктейля</label>
    </div>

    <br><br>
    <h4 class="col-sm-2 col-form-label">Ингредиенты</h4>

    <div class="ingredient_list">
        <div class="row" id="ingredient_row[0]">
            <div class="col-4">
                <input type="text" id="ingredientList" class="form-control" placeholder="Ингредиент">
            </div>
            <div class="col-1">
                <input type="text" class="form-control" placeholder="100">
            </div>
            <div class="col-1">
                <input type="text" class="form-control">
            </div>
            <div class="col-1">
                <input class="btn btn-success btn-block" type="button" value="+" id="ingredient_add" onclick="add_ingredient()">
            </div>
        </div>
    </div>

    <input type="hidden" name="ingredients" value="1:50:мл/260:40:гр" />

    <button type="submit" class="btn btn-primary float-right mt-3">Создать коктейль</button>
</form>

</@c.page>