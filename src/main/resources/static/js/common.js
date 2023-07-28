
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

// 뒤로가기
function home(){
    window.location.href = "/";
}