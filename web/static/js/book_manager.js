function page(pageNo) {
    window.location.href="/manage?librarian=findAllBook&pageNo="+pageNo;
}
// 页数输入框
var pageInp = document.getElementById("page_inp")
// 页数确认按钮
var pageBut = document.getElementById("page_but")
// 隐藏的input框 接收后端发的数据
var pageMax = document.getElementById("page_max")
// 获取当前页码的input框
const pageNo = document.getElementById("page_No");
// 获取 active 当前页码的li
const active = $('.active');

if (active.text() == "1"){
    console.log(active.next().next())
    active.next().css('display', 'inline-block')
    active.next().next().css('display', 'inline-block')

}else if (active.text() == pageMax.value){
    active.prev().css('display', 'inline-block')
    active.prev().prev().css('display', 'inline-block')
}else{
    active.prev().css('display', 'inline-block')
    active.next().css('display', 'inline-block')
}

$('.pageLi').click(function () {
    page($(this).text())
})

pageInp.oninput = function () {
    let minPage = 1
    let maxPage = Number(pageMax.value)
    let valpage = Number(pageInp.value)
    if (valpage > maxPage || valpage < minPage){
        pageBut.style.background = '#ccc'
        pageBut.style.color = '#999'
        pageBut.setAttribute('disabled', true)
    }else {
        pageBut.style.background = ''
        pageBut.style.color = ''
        pageBut.removeAttribute("disabled")
    }
}

pageBut.onclick = function () {
    page(pageInp.value)
}