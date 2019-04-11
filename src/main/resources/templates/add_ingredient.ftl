<#import "parts/common.ftl" as c>

<@c.page>

<h2>Добавление нового ингредиента</h2>
<br>

<form method="post" enctype="multipart/form-data">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />

    <div class="form-group">
        <label for="ingredientName">Название ингредиента</label>
        <input type="name" name="name" class="form-control" id="ingredientName" placeholder="Название" required>
    </div>
    <div class="form-group">
        <label for="ingredientDescription">Описание ингредиента</label>
        <textarea class="form-control" type="text" name="description" id="ingredientDescription" rows="4" placeholder="Описание"></textarea>
    </div>
    <div class="form-group">
        <label for="ingredientGroup">Группа</label>
        <select class="form-control" id="ingredientGroup" name="groupName">
            <#list ingredientGroups as group>
                <option>${group}</option>
            </#list>
        </select>
    </div>
    <div class="custom-file">
        <input type="file" class="custom-file-input" id="ingredientPic" name="file">
        <label class="custom-file-label" for="ingredientPic">Изображение ингредиента</label>
    </div>

    <button type="submit" class="btn btn-primary mt-3">Добавить</button>
</form>

</@c.page>