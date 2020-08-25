var pressed=false;
function searchItem(){
    $("#searchItem").on("input", function(){
            if($(this).val().length>3)
                alert($(this).val());
        });
}

function deleteItemPopup(id){
    $("#deleteModal").modal('show');
    var item;
    $.ajax({
        url :  "/getItem?id="+id,
        type: "post",
        success: function(data) {
            item=JSON.parse(data);
            $('#itemNameDelete').text("are you sure delete "+item.itemName+"?");
            $('#deletedId').val(item.id);
        },
        error: function(data) {
        },
    });
    pressed=true;
}
function deleteItem(){
     $.ajax({
        url :  "/deleteItem?id="+$('#deletedId').val(),
        type: "post",
        success: function(data) {
            location.reload();
        },
        error: function(data) {
        },
    });
}
function showDetail(id){
    if(!pressed){
        $("#editModal").modal('show');
        var item;
        $.ajax({
            url :  "/getItem?id="+id,
            type: "post",
            success: function(data) {
                item=JSON.parse(data);
                clearInput();
                if(item!=null){
                    $('#itemId').val(item.id);
                    $('#itemName').val(item.itemName);
                    $('#itemType').val(item.itemType);
                    $('#itemDescription').val(item.description);
                    $('#itemPrice').val(item.price);
                    $('#itemStock').val(item.stock);
                    $('#itemCreatedDate').val(item.createdDate);
                    $('#itemUpdatedDate').val(item.updatedDate);
                }
            },
            error: function(data) {
            },
        });
    }
    else{
        pressed=false;
    }
}
function clearInput(){
    $('#itemId').val("")
    $('#itemName').val("")
    $('#itemType').val("")
    $('#itemDescription').val("")
    $('#itemPrice').val("")
    $('#itemStock').val("")
    $('#itemCreatedDate').val("")
    $('#itemUpdatedDate').val("")
}

function addItem(){
    $("#newItemForm").submit(function(event){
    	event.preventDefault(); //prevent default action
    	var post_url = $(this).attr("action"); //get form action url
    	var request_method = $(this).attr("method"); //get form GET/POST method
    	var form_data = $(this).serialize(); //Encode form elements for submission
    	$.ajax({
    		url :  "/saveItem?name="+$('#itemNameNew').val()+"&type="+$('#itemTypeNew').val()+"&desc="+
    		$('#itemDescriptionNew').val()+"&price="+$('#itemPriceNew').val()+"&stock="+$('#itemStockNew').val(),
    		type: request_method,
            success: function(data) {
                location.reload();
            },
            error: function(data) {
            },
    	});

    });
}
function updateItem(){
    $("#updateItemForm").submit(function(event){
    	event.preventDefault(); //prevent default action
    	var post_url = $(this).attr("action"); //get form action url
    	var request_method = $(this).attr("method"); //get form GET/POST method
    	var form_data = $(this).serialize(); //Encode form elements for submission
    	$.ajax({
    		url :  "/updateItem?id="+$('#itemId').val()+"&name="+$('#itemName').val()+"&type="+$('#itemType').val()+"&desc="+
    		$('#itemDescription').val()+"&price="+$('#itemPrice').val()+"&stock="+$('#itemStock').val(),
    		type: request_method,
            success: function(data) {
                location.reload();
            },
            error: function(data) {
            },
    	});

    });
}

$(document).ready( function () {
    updateItem();
    addItem();
    searchItem();
})