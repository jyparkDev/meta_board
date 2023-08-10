function writeValid(){
    let title = $('#title').val();
    let writer = $('#writer').val();
    let content = $('#content').val();
    // 빈 공백일 경우
    if(title.replaceAll([' '],'').length == 0){
        alert('제목을 입력해주세요');
        $('#title').focus();
        return false;
    }
    if(writer.replaceAll([' '],'').length == 0){
        alert('작성자를 입력해주세요');
        $('#writer').focus();
        return false;
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

    return true;
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

function inputCheck(){
    $('#title').on( 'keyup', function() {
        let title = $('#title').val();
        if(title.length > 50){
            $('#title').val(title.substring(0,50));
            $('#title').focus();
            alert('제목 50자 초과');
        };
        if (event.keyCode === 13) {
            event.preventDefault();
        };
    });

    $('#title').bind('input paste', function(){
        $(this).trigger('keyup');
    });

    $('#writer').on( 'keyup', function() {
        let size = $( '#writer' ).val();
        if(size.length > 20){
            $('#writer').val(size.substring(0,20));
            $('#writer').focus();
            alert('작성자명 20자 초과');
        }
        if (event.keyCode === 13) {
            event.preventDefault();
        };
    });

    $('#writer').bind('input paste', function(){
        $(this).trigger('keyup');
    });

    $('#content').on( 'focus keydown', function() {
        let size = $( '#content' ).val();
        $('#count').text(size.length);
        if(size.length > 500){
            $('#content').val(size.substring(0,500));
            $('#content').focus();
            alert('500자 초과');
        }
    });

    $('#content').bind('input paste', function(){
        $(this).trigger('focus');
    });

}