<div class="card-columns mt-3" style="column-count: 4">
    <#list ingredients as ingredient>
    <a href="/ingredient/${ingredient.id}">
        <style type="text/css">
            A:link {color: black;}
            A:hover {color: blue;}
        </style>
        <div class="card text-center" style="width: 17rem;">
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
</a>
</#list>
</div>