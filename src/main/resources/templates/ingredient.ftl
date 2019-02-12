<#import "parts/common.ftl" as c>

<@c.page>
<p><h3>${ingredient.name}</h3>></p>
<img src="/img/${ingredient.imgAddress}" class="align-self-center mr-3" alt="${ingredient.name} picture">
<p>${ingredient.description}</p>

<#list coctails as coctail>
<div class="media mt-3">
    <img src="/img/${coctail.imgAddress}" class="align-self-center mr-3" alt="${coctail.name} picture">
    <div class="media-body">
        <h5 class="mt-0"><i>${coctail.name}</i> by ${coctail.authorName}</h5>
        <p>${coctail.description}</p>
        <p class="mb-0">
            <#list coctail.coctailIngredients as ingredient>
        <p>
            <a href="/ingredient/${ingredient.ingredient.id}"> ${ingredient.volume} ${ingredient.unit}  ${ingredient.ingredient.name}</a>
        </p>
        <#else>
        No ingredients
    </#list>
    </p>
</div>
</div>
<#else>
No coctails
</#list>

</@c.page>