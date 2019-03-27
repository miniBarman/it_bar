<#import "parts/common.ftl" as c>

<@c.page>


<form class="input-group mb-3" method="get" action="/main">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input id="coctailList" type="name" name="filter" class="form-control" placeholder="Название" value="${filter?ifExists}" aria-describedby="button-addon2">
    <div class="input-group-append">
        <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Найти</button>
    </div>
</form>

<#include "parts/coctail_list.ftl">

</@c.page>