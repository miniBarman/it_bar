<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>

<form class="input-group mb-3" method="get" action="/ingredients">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input id="tags" type="name" name="filter" class="form-control" placeholder="Название" value="${filter?ifExists}" aria-describedby="button-addon2">
    <div class="input-group-append">
        <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Найти</button>
    </div>
</form>

<div class="card-columns mt-3">

<#list ingredients as ingredient>
<div class="card text-center" style="width: 18rem;">
    <img src="/img/initial_data/ico/ingredient_ico.jpg" class="card-img-top" alt="Изображение ${ingredient.name}">
    <div class="card-body">
        <h5 class="card-title">${ingredient.name}</h5>
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
</#list>
</div>

</@c.page>