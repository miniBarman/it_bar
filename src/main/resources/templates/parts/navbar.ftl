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
            <li class="nav-item">
                <a class="nav-link" href="/help">Помощь</a>
            </li>
        </ul>

        <#if user??>
        <div class="btn-group">
            <div class="btn-group dropleft" role="group">
                <button type="button" class="btn btn-light btn-secondary dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="sr-only">Toggle Dropleft</span>
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="/add_coctail">Добавить новый коктейль</a>
                    <a class="dropdown-item" href="/add_ingredient">Добавить новый ингредиент</a>
                    <a class="dropdown-item" href="/possible_coctails">Коктейли из бара</a>
                </div>
            </div>
            <a class="btn btn-light btn-secondary" href="/user/profile" role="button">${name}</a>
        </div>
        <@l.logout />

        <#else>

        <form action="/main_login" method="get">
            <button type="submit" class="btn btn-light">Авторизоваться</button>
        </form>

        </#if>

    </div>
</nav>
