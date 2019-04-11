<div class="card-group mt-3">
    <#list ingredients as ingredient>
    <a href="/ingredient/${ingredient.id}">
        <style type="text/css">
				A:link {color: black;}
				A:hover {color: blue;}
		</style>
        <div class="card <#if ingredient.author.id == user.id>border-warning</#if> text-center mb-4 mr-1 ml-1">
            <img src="/img/initial_data/ico/ingredient_ico.jpg" class="card-img-top" alt="Изображение ${ingredient.name}">
            <div class="card-body">
                <h6 class="card-text" white-space="normal">${ingredient.name}</h6>
                <#if user??>
                <#if user.barIngredientIds?seq_contains(ingredient.id)>
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
</a>
<#if ingredient?counter % 2 == 0>
<div class="w-100 d-none d-sm-block d-md-none"><!-- wrap every 2 on sm--></div>
<#elseif ingredient?counter % 3 == 0>
<div class="w-100 d-none d-md-block d-lg-none"><!-- wrap every 3 on md--></div>
<#elseif ingredient?counter % 4 == 0>
<div class="w-100 d-none d-lg-block d-xl-none"><!-- wrap every 4 on lg--></div>
<#elseif ingredient?counter % 5 == 0>
<div class="w-100 d-none d-xl-block"><!-- wrap every 5 on xl--></div>
</#if>
</#list>
</div>