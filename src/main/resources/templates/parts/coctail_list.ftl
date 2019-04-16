<#list coctails as coctail>
<div class="media mt-3">
    <img src="/img/coctail.jpg" class="align-self-center mr-3" alt="${coctail.name} picture">
    <div class="media-body">
        <h5 class="mt-0"><i>${coctail.name}</i> by ${coctail.authorName}</h5>
        <p>${coctail.description}</p>
        <p class="mb-0">
            <ul style="list-style-type:none; padding: 0;">
            <#list coctail.coctailIngredients as ingredient>
                <li><a href="/ingredient/${ingredient.ingredient.id}"> ${ingredient.volume} ${ingredient.unit}  ${ingredient.ingredient.name}</a></li>
            <#else>
                No ingredients
            </#list>
            </ul>
        </p>
        <#list coctail.labels as label>
        <span class="badge badge-primary">${label.name}</span>
        </#list>
    </div>
</div>
<#else>
No coctails
</#list>