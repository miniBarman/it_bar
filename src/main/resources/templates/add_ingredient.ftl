<#import "parts/common.ftl" as c>

<@c.page>

<div>Добавление нового ингредиента</div>
<div>
    <form method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="name" name="name" placeholder="Название" />
        <input type="name" name="description" placeholder="Описание">
        <input type="file" name="file">
        <button type="submit">Добавить</button>
    </form>
</div>
${message?ifExists}
</@c.page>