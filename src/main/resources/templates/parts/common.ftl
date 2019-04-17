<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ITbar</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="static/jquery.tagsinput-revisited.css">
    <style>
        .card{
            overflow: hidden;
            max-width: 205px;
            min-width: 200px;
            width: 205px;
           }

        .card-text{
            word-wrap: break-word;
        }
        .required{
            color: red;
        }
    </style>
</head>
<body>
<#include "navbar.ftl">
<div class="container mt-5">
    <#include "system_message.ftl">
    <#nested>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="static/jquery.tagsinput-revisited.js"></script>
<script src="static/validator.js"></script>

<#if allIngredients??>
    <script type="text/javascript">
    $( function() {
        var availableIngredientList = [<#list allIngredients as ingredient>"${ingredient.name}"<#sep>, </#list>];
        $( "#ingredientList" ).autocomplete({
            source: availableIngredientList
        });
    });
    </script>
</#if>

<#if coctails??>
<script type="text/javascript">
    $( function() {
        var availableCoctailList = [<#list allCoctails as coctail>"${coctail.name}"<#sep>, </#list>];
        $( "#coctailList" ).autocomplete({
            source: availableCoctailList
        });
    });
    </script>
</#if>

<script>
// Add the following code if you want the name of the file appear on select
$(".custom-file-input").on("change", function() {
  var fileName = $(this).val().split("\\").pop();
  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});
</script>

<#if viewName?? && viewName == "add_coctail">
<script>
    $( function() {
        var availableIngredientList = [<#list allIngredients as ingredient>"${ingredient.name}"<#sep>, </#list>];
        var previousValue = "";
        var currentValue = "";
        $( "#ingredientList" ).autocomplete({
            source: availableIngredientList
        }).blur(function() {
            var isValid = false;
            for (i in availableIngredientList) {
                if (availableIngredientList[i].toLowerCase() == this.value.toLowerCase()) {
                    currentValue = availableIngredientList[i];
                    isValid = true;
                }
            }
            if (!isValid) {
                this.value = previousValue;
                currentValue = "";
                $(this).addClass('is-invalid');
            } else {
                previousValue = this.value;
                this.value = currentValue;
                $(this).addClass('is-valid');
            }
        });;
    });

	function add_ingredient() {
	    var delete_btn_id = $(".ingredient_list").children().last().attr('id').slice(14);
	    var i = Number(delete_btn_id)+1;
		$("#ingredient_add").replaceWith("<input class='btn btn-danger btn-block' type='button' value='-' onclick='delete_ingredient(" + delete_btn_id + ")'>");
		$(".ingredient_list").append("<div class='row' id='ingredient_row" + i + "'><div class='col-4'><input type='text' id='ingredientList" + i + "' name='ingredients' class='form-control' placeholder='Ингредиент' required></div><div class='col-1'><input type='text' class='form-control' name='volumes' placeholder='100' required></div><div class='col-2'><select class='form-control' name='units' required><#list unitList as unit><option>${unit}</option></#list></select></div><div class='col-1'><input class='btn btn-success btn-block' type='button' value='+' id='ingredient_add' onclick='add_ingredient()'></div></div>");
		bindAutoComplete();
	};
	function delete_ingredient(id){
	    $("#ingredient_row"+id).remove();
	};
	function bindAutoComplete(){
	    var availableIngredientList = [<#list allIngredients as ingredient>"${ingredient.name}"<#sep>, </#list>];
        $("input[id*='ingredientList']").autocomplete({
            source: availableIngredientList
        });
    };
</script>
</#if>

<#if coctailLabels??>
<script>
    $('#coctail_labels').tagsInput({
    'autocomplete': {
    source: [<#list coctailLabels as label>'${label.name}'<#sep>, </#list>]
    },
    interactive: true,
    placeholder: 'Введите тэг',
    width: 'auto',
    height: 'auto',
    hide: true,
    delimiter: ',',
    removeWithBackspace: true,
    unique: true
});
</script>
</#if>

</body>
</html>
</#macro>