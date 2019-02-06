<#import "parts/common.ftl" as c>

<@c.page>

<div>Список коктейлей</div>
<form method="get" action="/main">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input type="name" name="filter" placeholder="Название" value="${filter?ifExists}">
    <button type="submit">Найти</button>
</form>
<#list coctails as coctail>
<div>
    <b>${coctail.id}</b>
    <span>${coctail.name}</span>
    <i>${coctail.description}</i>
    <i>${coctail.authorName}</i>
    <div>
        <img src="/img/${coctail.imgAddress}">
    </div>
</div>
<#else>
No coctails
</#list>
</@c.page>