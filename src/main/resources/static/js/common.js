
function valid(){
    let title = document.getElementById('title').value;
    let writer = document.getElementById('writer').value;
    if(title.length > 50){
        alert('제목을 50자 이내로 작성해주세요.');
        $('#title').focus();
        return false;
    }
    if(writer.length > 15){
        alert('작성자는 15자 미만입니다.')
        $('#writer').focus();
        return false;
    }

    return true;
}

// 입력 Reset
function resetForm(){
    $('#title').val('');
    $('#writer').val('');
    $('#passwd').val('');
    $('#content').val('');
}

// 뒤로가기
function home(){
    window.location.href = "/";
}

function addFile(){
    $('#file-msg').css('display','none');
    let newUploadForm = '<div class="upload-form">' +
        '<input type="file" name="files" class="upload-name"/>' +
        '<button class="btn btn-danger remove-upload-form">삭제</button>' +
        '</div>';
    $("#upload-forms").append(newUploadForm);
}