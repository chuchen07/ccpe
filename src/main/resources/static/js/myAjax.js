function myajax(options){
//新建一个局部对象 用来存放用户输入的各种参数
    var opt={
        type:options.type||"get",//获取用户输入的传输方法，可选，不写为get
        data:options.data||"",//获取用户输入的数据
        dataType:options.dataType||"",//获取用户输入的数据类型比如json 或者xml
        url:options.url||"",//用户输入的url
        success:options.success||null//成功函数。
    }

    if(opt.url==""){//如果用户没有输入URL，这样是不允许的 。直接返回，不执行以后的操作
        alert("url不能为空");
        return;
    }
    if(options.type){
        opt.type=options.type.toLowerCase();//将用户输入的POST等方法变成小写
    }
//新建一个连接对象。标准浏览器中 都创建XMLHttpRequest 对象。非标准的浏览器中新建ActiveXObject
    var xhr=null;
    try{
        xhr=new XMLHttpRequest;
    }catch(e){
        xhr=new ActiveXObject("Micsoft.XMLHTTP");
    }
//如果用户用get方法，则需要拼接URL，将用户的数据放到URL传到后台
    if(opt.type=="get"&&opt.data){
        opt.url+="?"+opt.data;
    }
    xhr.open(opt.type,opt.url,true);//调用xhr.open方法连接后台借口
//如果是get方法，则send函数不传值、
    if(opt.type="get"){
        xhr.send(null);
    }else{
//如果是post方法 则需要加一个连接头。且send函数中 传入用户的数据
        xhr.setRequestHeader('content-type','application/x-www-form-urlencoded');
        xhr.send(opt.data);
    }
//连接完毕，等后台返回结果和数据
    xhr.onreadystatechange=function(){
        if(xhr.readyState==4){//readyState有5种状态码。0,1,2,3,4
            if(xhr.status==200){//http状态码为200代表成功
                var data=xhr.responseText;//新建一个变量保存后台返回的数据
                if(opt.dataType.toLowerCase()=="xml"){
                    data=xhr.responseXML;//如果后台返回的XML格式的数据.用responseXML来获取
                }
                if(opt.dataType.toLowerCase()=="json"){
                    data=JSON.parse(data);//如果是json 则用parse来将字符串转化为对象
                }
                if(typeof opt.success==='function'){
                    opt.success(data);//如果有成功的回调函数 则将后台数据当做回调函数的参数返回过去
                }
            }else{
                alert("出错啦"+xhr.status)//如果状态码不为200，连接失败，返回http状态码
            }

        }

    }
}