

// 삭제 기능
function postDelete(){
    if(!passwordValid){
        deletePasswordCheck();
        return;
    }
    delMessage();
}
function deletePasswordCheck(){
    let password = prompt("비밀번호를 입력해주세요?");
    if(password == null)
        return;
    let id = $("#id").val();
    $.ajax({
        type: "POST",
        url: "/board/passwd-check",
        data: JSON.stringify({password: password,id: id}),
        contentType: 'application/json',
        dataType : "json",
        success: function (response) {
            passwordValid = true;
            delMessage();
        },
        error: function (){
            alert("패스워드 불일치");
            deletePasswordCheck();
        }
    });
}
function delMessage(){
    let check = confirm("정말 삭제하시겠습니까?");
    if (check){
        let id = $("#id").val();
        window.location.href="/board/remove?id="+id+"&check="+check;
    }
}

// 수정 기능
function update(){
    if (!passwordValid){
        updatePasswordCheck();
        return;
    }
}

function updatePasswordCheck(){
    let password = prompt("비밀번호를 입력해주세요?");
    if(password == null)
        return;
    let id = $("#id").val();
    $.ajax({
        type: "POST",
        url: "/board/passwd-check",
        data: JSON.stringify({password: password,id: id}),
        contentType: 'application/json',
        dataType : "json",
        success: function (response) {
            window.location.href = '/board/update?id='+id;
        },
        error: function (){
            alert("패스워드 불일치");
            updatePasswordCheck()
            return;
        }
    });
}
function replyForm(){
    let id = $('#id').val();
    location.href = "/reply/write?id="+id;
}

// 팝업 기능
function open_etc(){
    $('#etc').css("display","block");
}
function close_all(){
    $('#etc').css("display","none");
}
