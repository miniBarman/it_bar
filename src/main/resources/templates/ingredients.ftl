<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>


<form method="get" action="/ingredients">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input type="name" name="filter" placeholder="Название" value="${filter?ifExists}">
    <button type="submit">Найти</button>
</form>

<#list ingredients as ingredient>
<div class="media mt-3">
    <img src="/img/${ingredient.imgAddress}" class="align-self-center mr-3" alt="Изображение ${ingredient.name}">
    <div class="media-body">
        <h5 class="mt-0"><i>${ingredient.name}</i></h5>
        <p>${ingredient.description}</p>
        <#if user??>
            <form action="/add_ingredient_to_bar" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="ingredient" value=${ingredient.id} />
                <input type="submit" value="Добавить в Бар"/>
            </form>
        </#if>
    </div>
</div>
<#else>
No ingredients
</#list>
</@c.page>