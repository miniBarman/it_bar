<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>

<form class="input-group mb-3" method="get" action="/ingredients">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input id="ingredientList" type="name" name="filter" class="form-control" placeholder="Название" value="${filter!}" aria-describedby="button-addon2">
    <div class="input-group-append">
        <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Найти</button>
    </div>
</form>

<#list ingredientsByGroups as group, ingredients>
<div class="container">
    <h4>${group}</h4>
    <#include "parts/ingredient_list.ftl">
</div>
</#list>


</@c.page>