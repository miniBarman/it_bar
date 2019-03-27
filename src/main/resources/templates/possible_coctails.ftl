<#import "parts/common.ftl" as c>

<@c.page>


<form class="input-group mb-3" method="get" action="/main">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input id="coctailList" type="name" name="filter" class="form-control" placeholder="Название" value="${filter!}" aria-describedby="button-addon2">
    <div class="input-group-append">
        <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Найти</button>
    </div>
</form>

<#list cocteilsByExistenceInBar as count, coctailMap>
    <h4>
        <#if count == 0>
            Можно готовить
        <#elseif count == 1>
            Не хватает одного ингридиента
        <#else>
            Не хватает ${count} ингридиентов
        </#if>
    </h4>
    <#list coctailMap as coctail, missingIngredients>
        <div class="media mt-3">
            <img src="/img/coctail.jpg" class="align-self-center mr-3" alt="${coctail.name} picture">
            <div class="media-body">
                <h5 class="mt-0"><i>${coctail.name}</i> by ${coctail.authorName}</h5>
                <p>${coctail.description}</p>

                <p class="mb-0">
                <#list coctail.coctailIngredients as ingredient>
                    <p><a href="/ingredient/${ingredient.ingredient.id}"> ${ingredient.volume} ${ingredient.unit}  ${ingredient.ingredient.name}</a></p>
                </#list>
                </p>

                <#if count != 0>
                <p class="mb-0">
                    Не хватает:
                    <br>
                    <#list missingIngredients as missingIngredient><a style="color: red" href="/ingredient/${missingIngredient.id}">${missingIngredient.name}</a><#sep>, </#list>
                </p>
                </#if>
            </div>
        </div>
    </#list>
</#list>


</@c.page>