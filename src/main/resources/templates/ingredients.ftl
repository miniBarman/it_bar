<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>

<form class="ui-widget" method="get" action="/ingredients">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <label for="tags">Tags: </label>
    <input id="tags" type="name" name="filter" placeholder="Название" value="${filter?ifExists}">
    <button type="submit">Найти</button>
</form>



<#list ingredients as ingredient>
<div class="media mt-3">
    <img src="/img/${ingredient.imgAddress}" class="align-self-center mr-3" alt="Изображение ${ingredient.name}">
    <div class="media-body">
        <h5 class="mt-0"><i>${ingredient.name}</i></h5>
        <p>${ingredient.description}</p>
        <#if user??>
            <#if user.barIngredientIds?seqContains(ingredient.id)>
                <form action="/delete_ingredient_from_bar" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <input type="hidden" name="ingredient" value=${ingredient.id} />
                    <button class="btn btn-danger" type="submit">Удалить из Бара</button>
                </form>
            <#else>
                <form action="/add_ingredient_to_bar" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <input type="hidden" name="ingredient" value=${ingredient.id} />
                    <button class="btn btn-success" type="submit">Добавить в Бар</button>
                </form>

            </#if>
        </#if>
    </div>
</div>
<#else>
No ingredients
</#list>
</@c.page>