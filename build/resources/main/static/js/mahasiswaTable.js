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
        url :  "/getMahasiswa?id="+id,
        type: "post",
        success: function(data) {
            item=JSON.parse(data);
            $('#itemNameDelete').text("are you sure delete "+item.nama+"?");
            $('#deletedId').val(item.id);
        },
        error: function(data) {
        },
    });
    pressed=true;
}
function deleteItem(){
     $.ajax({
        url :  "/deleteMahasiswa?id="+$('#deletedId').val(),
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
            url :  "/getMahasiswa?id="+id,
            type: "post",
            success: function(data) {
                item=JSON.parse(data);
                clearInput();
                if(item!=null){
                    $('#itemId').val(item.id);
                    $('#itemName').val(item.nama);
                    $('#itemType').val(item.jurusan);
//                    $('#itemDescription').val(item.description);
                    $('#itemPrice').val(item.nim);
                    $('#itemStock').val(item.ipk);
                    $('#itemCreatedDate').val(item.createdDateExt);
                    $('#itemUpdatedDate').val(item.updatedDateExt);
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
//    $('#itemDescription').val("")
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
    		url :  "/saveMahasiswa?name="+$('#itemNameNew').val()+"&jurusan="+$('#itemTypeNew').val()+
    		"&nim="+$('#itemPriceNew').val()+"&ipk="+$('#itemStockNew').val(),
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
    		url :  "/updateMahasiswa?id="+$('#itemId').val()+"&name="+$('#itemName').val()+"&jurusan="+$('#itemType').val()+
    		"&nim="+$('#itemPrice').val()+"&ipk="+$('#itemStock').val(),
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