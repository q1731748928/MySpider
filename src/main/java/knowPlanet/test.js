let clientHeight  = document.documentElement.clientHeight; //浏览器高度
let scrollHeight = document.body.scrollHeight;
let scrollTop = document.documentElement.scrollTop;

let distance = 50;  //距离视窗还用50的时候，开始触发；

if ((scrollTop + clientHeight) &gt;= (scrollHeight - distance)) {
    console.log("到底了，开始加载数据");
}
