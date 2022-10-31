var oInp = document.getElementsByClassName("user_input")
var oBtn = document.getElementsByClassName("btn")[0]
var oSp = document.getElementsByClassName("errMess")

var user = /^[\u4e00-\u9fa5]{2,4}$/
var pass = /^[0-9a-zA-Z]{6,12}$/
var email = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/

var str;
var codeDiv = document.querySelector('#code_div');

// 验证码字符
var chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

// 生成m-n的数字
function getRandom(m, n) {
    return Math.floor(Math.random() * (n - m + 1) + m);
}

// 验证码禁用文本选中
codeDiv.addEventListener("selectstart", function (e) {
    e.preventDefault();
})

// 生成随机验证码
function run() {
    str = '';

    // 若验证码存在，则清除
    while (codeDiv.hasChildNodes()) {
        codeDiv.removeChild(codeDiv.firstChild);
    }

    // 生成6位数的验证码
    for (var i = 0; i < 4; i++) {
        var span = document.createElement('span');
        span.setAttribute('class', 'code_sp')
        span.innerHTML = chars[getRandom(0, chars.length - 1)]; //生成随机数，并取得对应值
        span.style.display = "inline-block";
        span.style.fontSize = getRandom(16, 24) + "px"; //随机字体大小
        span.style.color = 'rgb(' + getRandom(0, 200) + ',' + getRandom(0, 200) + ',' + getRandom(0, 200) +
            ')'; //随机字体颜色
        span.style.transform = 'translate(' + getRandom(-5, 5) + 'px,' + getRandom(-5, 5) + 'px) rotate(' + getRandom(-
            20, 20) + 'deg)'; //随机平移旋转
        str += span.innerHTML; //将str拼接，和input值对比
        codeDiv.appendChild(span);
    }
}

run(); //进入页面生成验证码
codeDiv.addEventListener("click", run);

// 用户框失焦判断事件
let verify = false;
oInp[0].onblur = function () {
    const userName = oInp[0].value;
    const url = '/login?method=findUserByName&user=' + userName;

    if (!user.test(oInp[0].value)){
        oSp[0].style.visibility = "visible";
        oSp[0].innerText = '用户名格式错误'
        verify = false;
    }else {
        // axios数据提交到 /login 中 findUserByName() 进行判断有没有重名的用户名
        axios.post(url).then(resp => {
            if(resp.data != 0){
                oSp[0].style.visibility = "visible";
                oSp[0].innerText = '用户名已存在'
                verify = false;
            }else{
                oSp[0].style.visibility = "hidden";
                verify = true;
            }
        })
    }
}
// 密码框失焦判断事件
oInp[1].onblur = function () {
    if (!pass.test(oInp[1].value)){
        oSp[1].style.visibility = "visible";
    }else {
        oSp[1].style.visibility = "hidden";
    }
}
// 确认密码框失焦判断事件
oInp[2].onblur = function () {
    if (oInp[2].value != oInp[1].value){
        oSp[2].style.visibility = "visible";
    }else {
        oSp[2].style.visibility = "hidden";
    }
}
// 邮箱框失焦判断事件
oInp[3].onblur = function () {
    if (!email.test(oInp[3].value)){
        oSp[3].style.visibility = "visible";
    }else {
        oSp[3].style.visibility = "hidden";
    }
}
// 验证码失焦判断事件
oInp[4].onblur = function () {
    if (str.toLowerCase() != oInp[4].value.toLowerCase()){
        oSp[4].style.visibility = "visible";
    }else {
        oSp[4].style.visibility = "hidden";
    }
}

// 注册按钮提交事件
oBtn.onclick = function (event) {
    if ((!oInp[0].value == false)
        || (!oInp[1].value == false)
        || (!oInp[2].value == false)
        || (!oInp[3].value == false)
        || (!oInp[4].value == false)){
        if (verify && (pass.test(oInp[1].value))
            && (oInp[2].value == oInp[1].value)
            && (email.test(oInp[3].value))
            && (str.toLowerCase() == oInp[4].value.toLowerCase())){
            console.log("注册成功！")
        }else {
            alert("格式错误")
            event.preventDefault()
        }
    }else {
        alert("内容不能为空！")
        event.preventDefault()
    }
}

// 密码显示或隐藏
var showPass = document.getElementById("show_pass")
showPass.onmousedown = function (){
    oInp[1].setAttribute('type', 'test')
    showPass.style.backgroundImage = 'url("static/img/眼睛_hidden.png")'
}
showPass.onmouseup = function (){
    oInp[1].setAttribute('type', 'password')
    showPass.style.backgroundImage = 'url("static/img/眼睛_show.png")'
}