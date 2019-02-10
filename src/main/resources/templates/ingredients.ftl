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
        <p class="mb-0">List of ingredients</p>
    </div>
</div>
<#else>
No ingredients
</#list>
</@c.page>