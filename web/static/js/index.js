// 页数输入框
const pageInp = document.getElementById("page_inp")
// 页数确认按钮
const pageBut = document.getElementById("page_but")
// 隐藏的input框 接收后端发的数据
const pageMax = document.getElementById("page_max")
// 获取当前页码的input框
const pageNo = document.getElementById("page_No");
// 获取 active 当前页码的li
const active = $('.active');
// 获取最小价格输入框
const minInp = $('#min_inp')
// 获取最大价格输入框
const maxInp = $('#max_inp')
// 获取价格查询按钮
const priceBut = $('#price_but')

function page(pageNo) {
    console.log(minInp.val())
    console.log(maxInp.val())
    if(minInp.val() != false && maxInp.val() != false){
        window.location.href="/index.do?min=" + minInp.val()+"&max="+maxInp.val()+"&pageNo="+pageNo;
    }else {
        window.location.href="/index.do?pageNo="+pageNo;
    }
}

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

$('#price_but').click(function () {
    if(minInp.val() != false && maxInp.val() != false){
        window.location.href="/index.do?min=" + minInp.val()+"&max="+maxInp.val();
    }else {
        window.location.href="/index.do?";
    }
})

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

function addCart(bookId){
    window.location.href='cart?operate=addCart&bookId='+bookId;
}