function writeValid(){
    let title = $('#title').val();
    let writer = $('#writer').val();
    let content = $('#content').val();
    // 빈 공백일 경우
    if(title.replaceAll([' '],'').length == 0){
        alert('제목 비었습니다.');
        $('#title').focus();
        return;
    }
    if(writer.replaceAll([' '],'').length == 0){
        alert('작성자가 비었습니다.');
        $('#writer').focus();
        return;
    }

    var pw = $("#passwd").val();
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
    dataTrim();

    let form = document.getElementById('submit-form');
    form.method ="POST";
    form.action = "/board/write";
    form.submit();
}
function updateValid(){

    let title = $('#title').val();
    let writer = $('#writer').val();
    let content = $('#content').val();
    // 빈 공백일 경우
    if(title.replaceAll([' '],'').length == 0){
        alert('제목 비었습니다.');
        $('#title').focus();
        return;
    }
    if(writer.replaceAll([' '],'').length == 0){
        alert('작성자가 비었습니다.');
        $('#writer').focus();
        return;
    }

    dataTrim();

    let form = document.getElementById('submit-form');
    form.method ="POST";
    form.action = "/board/update";
    form.submit();
}

function dataTrim(){
    let title = $('#title').val();
    let writer = $('#writer').val();
    let content = $('#content').val();

    $('#title').val(title.trim());
    $('#writer').val(writer.trim());
    $('#content').val(content.trim());
}