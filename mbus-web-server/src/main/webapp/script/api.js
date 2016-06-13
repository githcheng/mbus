function add(){

    var type = $("#type").val();
    var startUrl = $("#startUrl").val();
    var content = $("#content").val();
    var isBrowse = $("#isBrowse").val();
    var operator = $("#operator").val();

    $.ajax({
        url: '/api/add',
        type: 'GET',
        data:{
            "type":type,
            "startUrl":startUrl,
            "content":content,
            "isBrowse":isBrowse,
            "operator":operator
        },
        dataType:'json',
        success: function (data) {
            var code = data.code;
            if(code == 0){
                alert("success")
            }else{
                alert("fail")
            }
        },
        error: function (xhr, errorInfo, ex) {

        }
    });

}

function fConfirm(id){

    $.ajax({
        url: '/api/confirm',
        type: 'GET',
        data:{
            "id":id
        },
        dataType:'json',
        success: function (data) {
            var code = data.code;
            if(code == 0){
                alert("success")
            }else{
                alert("fail")
            }
        },
        error: function (xhr, errorInfo, ex) {

        }
    });

}

function modify(id){

    var type = $("#type_"+id).val();
    var state = $("#state_"+id).val();
    var startUrl = $("#startUrl_"+id).val();
    var content = $("#content_"+id).val();
    var isBrowse = $("#isBrowse_"+id).val();
    var operator = $("#operator_"+id).val();

    $.ajax({
        url: '/api/modify',
        type: 'GET',
        data:{
            "id":id,
            "type":type,
            "state":state,
            "startUrl":startUrl,
            "content":content,
            "isBrowse":isBrowse,
            "operator":operator
        },
        dataType:'json',
        success: function (data) {
            var code = data.code;
            if(code == 0){
                alert("success")
            }else{
                alert("fail")
            }
        },
        error: function (xhr, errorInfo, ex) {

        }
    });

}

function copy(id){

    var type = $("#type_"+id).val();
    var state = $("#state_"+id).val();
    var startUrl = $("#startUrl_"+id).val();
    var content = $("#content_"+id).val();
    var isBrowse = $("#isBrowse_"+id).val();
    var operator = $("#operator_"+id).val();

    $.ajax({
        url: '/api/copy',
        type: 'GET',
        data:{
            "id":id,
            "type":type,
            "state":0,
            "startUrl":startUrl,
            "content":content,
            "isBrowse":isBrowse,
            "operator":operator
        },
        dataType:'json',
        success: function (data) {
            var code = data.code;
            if(code == 0){
                alert("success")
            }else{
                alert("fail")
            }
        },
        error: function (xhr, errorInfo, ex) {

        }
    });

}




function del(id){

    $.ajax({
        url: '/api/del',
        type: 'GET',
        data:{
            "id":id
        },
        dataType:'json',
        success: function (data) {
            var code = data.code;
            if(code == 0){
                alert("success")
            }else{
                alert("fail")
            }
        },
        error: function (xhr, errorInfo, ex) {

        }
    });
}

function convert(){

    var URL_POST = $("#URL_POST").val();
    var title = $("#title").val();
    var content = $("#content").val();
    var URL_LIST = $("#URL_LIST").val();

    $.ajax({
        url: '/web/convert',
        type: 'GET',
        data:{
            "URL_POST":URL_POST,
            "title":title,
            "content":content,
            "URL_LIST":URL_LIST
        },
        dataType:'json',
        success: function (data) {
            var code = data.code;
            if(code == 0){
                $("#returninfor").html(JSON.stringify(data.data));
            }else{
                alert("fail")
            }
        },
        error: function (xhr, errorInfo, ex) {
            alert("fail")
        }
    });
}

