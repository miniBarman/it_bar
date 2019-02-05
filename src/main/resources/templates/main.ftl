<#import "parts/common.ftl" as c>

<@c.page>

<div>Список коктейлей</div>
<form method="get" action="/main">
    <input type="name" name="filter" placeholder="Название" value="${filter?ifExists}">
    <button type="submit">Найти</button>
</form>
<#list coctails as coctail>
<div>
    <b>${coctail.id}</b>
    <span>${coctail.name}</span>
    <i>${coctail.description}</i>
</div>
<#else>
No coctails
</#list>
</@c.page>