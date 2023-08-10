function findAllFile(){
    const boardId = $('#id').val();

    $.getJSON(`/boards/${boardId}/files`, function (data){

        if(data.length == 0){
            return false;
        }

        $('#file-msg').css('display','none');

        for(let i = 0; i < data.length ; i++){
            addFiles();
        }

        // 5. 파일 선택 & 삭제 이벤트 재선언 & 파일명 세팅
        const filenameInputs = document.querySelectorAll('.upload-forms input[type="text"]');
        filenameInputs.forEach((input, i) => {
            console.log(input);
            const fileInput = input.nextElementSibling;
            const fileRemoveBtn = input.parentElement.nextElementSibling;
            fileInput.setAttribute('onchange', `selectFile(this, ${data[i].id})`);
            fileRemoveBtn.setAttribute('onclick', `removeFile(this, ${data[i].id})`);
            input.value = data[i].originalName;
        })

    })
}

// 파일 추가
function addFiles() {
    const fileDiv = document.createElement('div');
    fileDiv.innerHTML =`
            <div class="upload-form">
                <input class="file-name" type="text" readonly />
                 <input type="file" name="files" class="upload-name" onchange="selectFile(this);" />
            </div>
            <button class="btn btn-danger remove-upload-form"><span>삭제</span></button>
        `;
    document.querySelector('.upload-forms').appendChild(fileDiv);

}

// 파일 삭제 처리용 익명 함수

