$(function() {
    // 1.点击上部的li，当前li 添加current类，其余兄弟移除类
    $(".bar li").click(function() {
        // 链式编程操作
        $(this).addClass("type").siblings().removeClass("type");
        // 2.点击的同时，得到当前li 的索引号
        var index = $(this).index();
        console.log(index);
        // 3.让下部里面相应索引号的item显示，其余的item隐藏
        $(".tab .view").eq(index).show().siblings().hide();
    });
})


$(function() {
    // 1.点击上部的li，当前li 添加current类，其余兄弟移除类
    $(".col-md-3 li").click(function() {
        // 链式编程操作
        $(this).addClass("current").siblings().removeClass("current");
        // 2.点击的同时，得到当前li 的索引号
        var index = $(this).index();
        console.log(index);
        // 3.让下部里面相应索引号的item显示，其余的item隐藏
        $(".col-md-9 .item").eq(index).show().siblings().hide();
    });
})


$(function() {
    // 1.点击上部的li，当前li 添加current类，其余兄弟移除类
    $(".col-md-3 li").click(function() {
        // 链式编程操作
        $(this).addClass("current2").siblings().removeClass("current2");
        // 2.点击的同时，得到当前li 的索引号
        var index = $(this).index();
        console.log(index);
        // 3.让下部里面相应索引号的item显示，其余的item隐藏
        $(".col-md-9 .item2").eq(index).show().siblings().hide();
    });
})
$(function() {
    // 1.点击上部的li，当前li 添加current类，其余兄弟移除类
    $(".col-md-3 li").click(function() {
        // 链式编程操作
        $(this).addClass("current3").siblings().removeClass("current3");
        // 2.点击的同时，得到当前li 的索引号
        var index = $(this).index();
        console.log(index);
        // 3.让下部里面相应索引号的item显示，其余的item隐藏
        $(".col-md-9 .item3").eq(index).show().siblings().hide();
    });
})


//分页
ajaxGetData();
var cacheData= goTo();//读取缓存的对象
function ajaxGetData(currentPage) {
    $("#users_table tbody").empty();
    var currentPage=currentPage || 1;
    $.ajax({
        url:"/data",
        data:{currentPage:currentPage,pageAmount:5},
        type:"get",
        success:function(result){

            // var data= JSON.parse(doc);
            // cacheData.set()(currentPage,data);
            // console.log("请求成功");

            var users = result.list;
            $.each(users,function(index,item){
                var userId =$("<td></td>").append(item.userId);
             var userName = $("<td></td>").append(item.userName);
             var email = $("<td></td>").append(item.email);
            var userCode = $("<td></td>").append(item.userCode);

                var editBtn	= $("<button></button>").addClass("btn btn-primary btn-sm")
                    .append($("<span></span>").addClass("glyphicon glyphicon-edit")).append("修改");
                var deleBtn=$("<button onclick='javascript:this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);'></button>").addClass("btn btn-danger ")
                    .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
                var btn = $("<td></td>").append(editBtn).append(" ").append(deleBtn)


                // function deleteRow(r){
                //     var i=r.parentNode.parentNode.rowIndex;
                //     document.getElementById('users_table').deleteRow(i);
                //
                // }


                $("<tr></tr>").append(userId)
                    .append(userName)
                    .append(email)
                    .append(userCode)
                    .append(btn)
                    .appendTo("#users_table tbody");
            })

        }
    })
}
//绑定a点击分页
var oLi= document.getElementsByTagName("a");
for (var i=0;i<oLi.length;i++){
    oLi[i].onclick=function () {
        var currentPage=this.innerHTML;
        ajaxGetData(currentPage);
    }
}


function goTo() {
    var cache = {};//闭包缓存池
    return{
        set:function(currentPage,data){
        cache[currentPage] =data;
        },//添加方法
        get:function (currentPage) {
            if (currentPage in cache){
                console.log("数据已缓存，无需再请求");
            }else {
                ajaxGetData();
            }
        }//读取方法
    }
}

