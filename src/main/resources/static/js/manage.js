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
                var userId =$("<td></td>").append("<input type='text' disabled='ture' value="+item.userId+">");
                var userName = $("<td></td>").append("<input type='text'  value="+item.userName+">");
                var email = $("<td></td>").append("<input type='text' disabled='ture' value="+item.email+">");
                var userCode = $("<td></td>").append("<input type='text'  value="+item.userCode+">");
                var userState = $("<td></td>").append("<input type='text'  value="+item.userState+">");
                var editBtn	= $("<button></button>").addClass("btn btn-primary btn-sm")
                    .append($("<span></span>").addClass("glyphicon glyphicon-edit")).append("编辑");
                var deleBtn=$("<button ></button>").addClass("btn btn-danger ")
                    .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");


                // onclick='javascript:this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);'

           //删除按钮
                deleBtn.attr("email",item.email);
                deleBtn.click(function () {

                     $.ajax({

                        url:"/deleteUser?time="+new Date().getTime(),//解决数据缓存
                         data:"email="+deleBtn.attr("email"),
                          type:"get",
                        success:function(result){
                        if(result==true){
                              alert("成功：该用户已删除");
                             ajaxGetData();
                            }else{
                               alert("失败：删除用户失败");
                        }
                     }
                  });
                });

            //编辑按钮
                editBtn.click(function () {
                    // var edit_userName = $(this).parent().parent().children().eq(1).children().val();
                    // var edit_userCode = $(this).parent().parent().children().eq(3).children().val();
                    // var edit_userState = $(this).parent().parent().children().eq(4).children().val();
                    editBtn.attr("edit_userName",item.userName);
                    editBtn.attr("edit_userCode",item.userCode);
                    editBtn.attr("edit_userState",item.userState);
                    alert(editBtn.attr("edit_userName"));
                    $.ajax({
                        url: "/updateUser?" + new Date().getTime(),
                        type: "post",
                        data:
                            "userName="+editBtn.attr("edit_userName")+
                            "&userCode="+editBtn.attr("edit_userCode")+
                            "&userState="+editBtn.attr("edit_userState"),
                        success: function (result) {
                            alert(result);
                        }


                    });

                });
                var btn = $("<td></td>").append(editBtn).append(" ").append(deleBtn)

                $("<tr></tr>").append(userId)
                    .append(userName)
                    .append(email)
                    .append(userCode)
                    .append(userState)
                    .append(btn)
                    .appendTo("#users_table tbody");
            })


    }
})
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
}}


