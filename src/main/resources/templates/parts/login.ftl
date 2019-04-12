<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">User Name :</label>
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control" placeholder="User name" required/>
        </div>
    </div>
    <#if isRegisterForm>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Email :</label>
        <div class="col-sm-6">
            <input type="email" name="email" class="form-control" placeholder="email@email.com" aria-describedby="emailHelpBlock" required/>
            <small id="emailHelpBlock" class="form-text text-muted">
                Мы обещаем использовать ваш e-mail только при необходимости. Никакого спама не будет!
            </small>
        </div>
    </div>
    </#if>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Password" required/>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <#if !isRegisterForm><a class="btn btn-primary" href="/registration">Новый Пользователь</a></#if>
    <button class="btn btn-success" type="submit"><#if isRegisterForm>Create<#else>Войти</#if></button>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit" class="btn btn-light">Выйти</button>
</form>
</#macro>