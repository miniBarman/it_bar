<#if message??>
    <#if message.type == "INFO">
        <div class="alert alert-info" role="alert">
    <#elseif message.type == "SUCCESS">
        <div class="alert alert-success" role="alert">
    <#elseif message.type == "ERROR">
        <div class="alert alert-danger" role="alert">
    </#if>
    ${message.text}
</div>
</#if>