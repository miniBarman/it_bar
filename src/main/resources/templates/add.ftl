<#import "parts/common.ftl" as c>

<@c.page>

<div>Добавление нового коктейля</div>
<div>
    <form method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="name" name="name" placeholder="Название" />
        <input type="name" name="description" placeholder="Описание">
        <button type="submit">Добавить</button>
    </form>
</div>
${message?ifExists}
</@c.page>