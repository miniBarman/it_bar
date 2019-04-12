<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ITbar</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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

<#if allIngredients??>
<script>
	function add_ingredient() {
	    var i = $("[id *= 'ingredientList']").length;
	    var delete_btn_id = i-1;
		$("#ingredient_add").replaceWith("<input class='btn btn-danger btn-block' type='button' value='-' onclick='delete_ingredient(" + delete_btn_id + ")'>");
		$("<div class='row' id='ingredient_row" + i + "'><div class='col-4'><input type='text' id='ingredientList" + i + "' class='form-control' placeholder='Ингредиент'></div><div class='col-1'><input type='text' class='form-control' placeholder='100'></div><div class='col-1'><input type='text' class='form-control'></div><div class='col-1'><input class='btn btn-success btn-block' type='button' value='+' id='ingredient_add' onclick='add_ingredient()'></div></div>").appendTo(".ingredient_list");
		bindAutoComplete('form-control');
	};
	function delete_ingredient(id){
	    alert("#ingredient_row"+id);
	    $("#ingredient_row"+id).remove();
	};
	function bindAutoComplete(classname){
	    var availableIngredientList = [<#list allIngredients as ingredient>"${ingredient.name}"<#sep>, </#list>];
        $("input[id*='ingredientList']").autocomplete({
            source: availableIngredientList
        });
    };
</script>
</#if>
</body>
</html>
</#macro>