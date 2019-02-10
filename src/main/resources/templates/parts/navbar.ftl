<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">ITbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/main">Коктейли</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/ingredients">Ингредиенты</a>
            </li>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/add_coctail">Добавить новый коктейль</a>
                </li>
            <li class="nav-item">
                <a class="nav-link" href="/add_ingredient">Добавить новый ингредиент</a>
            </li>
            </#if>
        </ul>

        <#if user??>
            <div class="navbar-text mr-3">${name}</div>
            <@l.logout />
        <#else>
        <form action="/main_login" method="get">
            <input type="submit" value="Log In"/>
        </form>
        </#if>

    </div>
</nav>
