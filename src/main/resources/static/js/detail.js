// 페이지 로딩 시 처리
function existCheck(){
    let exist = $('#exist').val();
    console.log(exist);
    if (exist === '0'){
        console.log(exist);
        $('#comment-write-area').css({'border':'none','padding':'0px'});
        $('#comment-write-area').empty();
        $('.empty-comment').empty();
        $('.empty-comment').css('border','none');
    }
}
function scrollCheck(){
    var scroll = $('#scroll').val();
    if(scroll === 'true'){
        window.location.href = "#comment-count";
    }
}

// 삭제 기능
function postDelete(){
    if(!passwordValid){
        deletePasswordCheck();
        return;
    }
    delMessage();
}
function deletePasswordCheck(){
    let passwd = prompt("비밀번호를 입력해주세요?");
    if(passwd == null)
        return;
    let id = $("#id").val();
    $.ajax({
        type: "POST",
        url: "/board/passwd-check",
        data: JSON.stringify({passwd: passwd,id: id}),
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
    let passwd = prompt("비밀번호를 입력해주세요?");
    if(passwd == null)
        return;
    let id = $("#id").val();
    $.ajax({
        type: "POST",
        url: "/board/passwd-check",
        data: JSON.stringify({passwd: passwd,id: id}),
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




// 댓글 기능

// 댓글 작성
function sendComment(){
    let content = $('#content').val().trim();
    let writer = $('#writer').val().trim();
    let pw = $('#passwd').val().trim();

    if(content.replaceAll([' '],'').length == 0){
        alert('댓글을 작성해주세요');
        $('#content').focus();
        return;
    }
    if(writer.replaceAll([' '],'').length == 0){
        alert('작성자가 비었습니다');
        $('#writer').focus();
        return;
    }

    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
    if((pw.length < 8 || pw.length > 20) || (num < 0 || eng < 0 || spe < 0)){
        alert("비밀번호 영문,숫자, 특수문자 혼합(8-20자)");
        $('#passwd').focus();
        return false;
    }
    if(pw.search(/\s/) != -1){
        alert("비밀번호는 공백 없이 입력해주세요.");
        $('#passwd').focus();
        return false;
    }

    $('#writer').val(writer.trim());
    $('#content').val(content.trim());

    let id = $('#id').val()
    $.ajax({
        type: "POST",
        url: "/comment/write",
        data: JSON.stringify({id: id,passwd: pw, content: content, writer: writer}),
        contentType: 'application/json',
        dataType : "json",
        success: function (res) {
            let pagehandler = res['page'];
            let comments = res['comments'];

            // 페이지 생성
            drawComment(comments);
            drawPage(pagehandler);

            $('#writer').val('');
            $('#content').val('');
            $('#passwd').val('');
        },
        error: function (){
        }
    });
}




// 댓글 생성
function drawComment(comments){
    $('#comment-view-area').empty();
    let tmp_html = ``;
    for(var i = 0 ; i < comments.length; i++){
        let writer = comments[i]['writer'];
        let content = comments[i]['content'];
        let create_date = comments[i]['create_date'];
        let id = comments[i]['id'];
        tmp_html += `<div class="card">
                                    <div class="card-header">
                                        <div>${writer}</div>
                                        <div class="create_date">${createDate}</div>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text">${content}</p>
<!--                                        <a class="btn btn-secondary" onclick="updateComment(${id});">수정</a>-->
                                        <a class="btn btn-danger" onclick="removeComment(${id})">삭제</a>
                                    </div>
                                </div>`
    }
    $('#comment-view-area').html(tmp_html);
}


// 페이지네이션 생성
function drawPage(pagehandler){
    $('#page-area').empty();
    let tmp_html = ``;
    tmp_html += pagehandler['first']
        ? `<li class="page-item cursor" onclick="commentPage(1)">
                                    <a class="page-link disabled" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                               </li>`
        : `<li class="page-item">
                                    <a class="page-link disabled" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>`;
    tmp_html += pagehandler['prev']
        ? `<li class="page-item cursor">
                                    <a class="page-link disabled" aria-label="Previous">
                                        <span aria-hidden="true">&lt;</span>
                                    </a>
                                </li>`
        : `<li class="page-item">
                                    <a class="page-link disabled" aria-label="Previous">
                                        <span aria-hidden="true">&lt;</span>
                                    </a>
                                </li>`;
    let page = pagehandler['page'];

    for(let i = pagehandler['beginPage'] ; i <= pagehandler['endPage']; i++){
        if (page == i){
            tmp_html += `<li class="page-item"><a class="page-link active page-num">
                                            ${i}</a></li>`;
        }else{
            tmp_html += `<li class="page-item" onclick="commentPage(${i})"><a class="page-link page-num cursor">
                                            ${i}</a></li>`;
        }
    }
    tmp_html += pagehandler['next']
        ? `<li class="page-item cursor">
                                    <a class="page-link disabled" aria-label="Previous">
                                        <span aria-hidden="true">&gt;</span>
                                    </a>
                                </li>`
        : `<li class="page-item">
                                    <a class="page-link disabled" aria-label="Previous">
                                        <span aria-hidden="true">&gt;</span>
                                    </a>
                                </li>`;

    tmp_html += pagehandler['end']
        ? `<li class="page-item cursor" onclick="commentPage(${pagehandler['totalPage']})">
                                    <a class="page-link disabled" aria-label="Previous">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>`
        : `<li class="page-item">
                                    <a class="page-link disabled" aria-label="Previous">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>`;
    $('#page-area').html(tmp_html);
    $('#comment-count').text(pagehandler['totalCnt']);
}

// 페이지 별 댓글 처리
function commentPage(page){
    let id = $("#id").val();
    $.ajax({
        type: "GET",
        url: "/comment/search?page="+page+"&id="+id,
        success: function (res) {
            let comments = res['comments'];
            let pagehandler = res['page'];
            drawComment(comments);
            drawPage(pagehandler);

            $('#c_page').val(pagehandler['page']);
            window.location.href = "#comment-count"
        },
        error: function (){
        }
    });
}

// 댓글 수정
function updateComment(c_id){
    let c_page = $('#c_page').val();
    let id = $('#id').val();
    if(commentPasswordCheck(c_id)){

    }
}


// 댓글 삭제
function removeComment(c_id){
    let c_page = $('#c_page').val();
    let id = $('#id').val();
    if(commentPasswordCheck(c_id)){
        var isDelete = confirm("해당 댓글을 삭제하시겠습니까?");
        if (isDelete){
            $.ajax({
                type: "GET",
                url: "/comment/delete?id="+id+"&page="+c_page+"&c_id="+c_id,
                success: function (res) {
                    let comments = res['comments'];
                    let pagehandler = res['page'];
                    $('#c_page').val(pagehandler['page']);
                    if (pagehandler['totalCnt'] > 0){
                        drawComment(comments);
                        drawPage(pagehandler);
                    } else{
                        let tmp_html =
                            `<div class="empty-comment">
                                <span class="material-symbols-outlined">mode_comment</span><span>첫 번째 댓글을 작성해보세요.</span>
                            </div>`;

                        $('#page-area').empty();
                        $('#comment-view-area').empty();
                        $('#comment-view-area').html(tmp_html);

                    }
                }
            });
        }
    }
}

function commentPasswordCheck(id){
    let isSuccess = false;
    let passwd = prompt("비밀번호를 입력해주세요?");
    if(passwd.replaceAll([' '],'').length === 0){
        alert("패스워드를 입력해주세요");
        commentPasswordCheck(id);
    }
    $.ajax({
        type: "POST",
        url: "/comment/passwdCheck",
        async: false,
        data: JSON.stringify({passwd : passwd, id: id }),
        contentType: 'application/json',
        dataType : "json",
        success: function (res) {
            if(res['msg'] !== "ok"){
                alert("패스워드 불일치");
                commentPasswordCheck(id);
            }else {
                isSuccess = true;
            }
        },
    });
    return isSuccess;
}



