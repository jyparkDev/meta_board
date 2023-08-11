let fileForm = ['pdf','hwp', 'ppt', 'jpg', 'gif', 'png', 'xlsx' , 'zip']
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
    allFileRemove();
}

// 뒤로가기
function home(){
    window.location.href = "/";
}

function addFile(){
    $('#file-msg').css('display','none');
    let newUploadForm = '<div class="upload-form">' +
        '<input type="file" name="files" class="upload-name" onchange="selectWriteFile(this)"/>' +
        '<button class="btn btn-danger remove-upload-form" type="button">삭제</button>' +
        '</div>';
    $("#upload-forms").append(newUploadForm);
}

function selectWriteFile(element){
    const file = element.files[0];
    const fileExt = file['name'].split('.')[1];
    if(!fileForm.includes(fileExt)){
        alert('[.pdf .hwp .ppt .jpg .gif .png .xlsx .zip]'+ "만 업로드 가능합니다.");
        element.value = '';
        return false;
    }
    if((Math.floor(fileSize + file['size']) / 1024) > 5000){
        alert('5MB를 초과했습니다.');
        element.value = '';
        return false;
    }

    fileSize += file['size'];

    $('#file-size').text(Math.floor(fileSize / 1024));

}

//
// lastModified :1691728690558
// lastModifiedDate : Fri Aug 11 2023 13:38:10 GMT+0900 (한국 표준시) {}
// name : "example (3).xlsx"
// size : 3548
// type : "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"


//

// // // 2. 파일 크기가 10MB를 초과하는 경우
// const fileSize = Math.floor(file.size / 1024 / 1024);
// if (fileSize > 1) {
//     alert('1MB 이하의 파일로 업로드해 주세요.');
//     // filename.value = '';
//     element.value = '';
//     return false;
// }
//
// // 1. 파일 선택 창에서 취소 버튼이 클릭된 경우
// if ( !file ) {
//     filename.value = '';
//     return false;
// }
//
//
// // 3. 파일명 지정
// console.log(file.name);
// filename.value = file.name;

