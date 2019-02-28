<#import "parts/common.ftl" as c>

<@c.page>
<h5>${userName}</h5>

<form method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Password" />
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
<button class="btn btn-primary" type="submit">Save</button>

</@c.page>