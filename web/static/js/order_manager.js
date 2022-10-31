function page(pageNo) {
    window.location.href="/order?operate=findAll&pageNo="+pageNo;
}
// 隐藏的input框 接收后端发的数据
const pageMax = document.getElementById("page_max");
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