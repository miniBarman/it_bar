<#import "parts/common.ftl" as c>

<@c.page>
<p>${ingredient.name}</p>
<img src="/img/${ingredient.imgAddress}" class="align-self-center mr-3" alt="${ingredient.name} picture">
<p>${ingredient.description}</p>



</@c.page>