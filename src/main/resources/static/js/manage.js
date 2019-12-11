
//上面的导航条
$(function() {
        // 1.点击上部的li，当前li 添加current类，其余兄弟移除类
        $(".bar li").click(function () {
            // 链式编程操作
            $(this).addClass("type").siblings().removeClass("type");
            // 2.点击的同时，得到当前li 的索引号
            var index = $(this).index();
            console.log(index);
            // 3.让下部里面相应索引号的item显示，其余的item隐藏
            $(".tab .view").eq(index).show().siblings().hide();
        });
        $.ajax({
            url: "/getAllRolePermission?time=" + new Date().getTime(),
            success: function (result) {
                $.each(result, function (index, n) {
                    var rid = $("<td style='width: 200px' bgcolor='#d3d3d3'></td>").append(n.rid);
                    var rname = $("<td style='width: 200px' ></td>").append(n.rname);
                    var pid = $("<td style='width: 200px' bgcolor='#d3d3d3'></td>").append(n.pid);
                    var pname = $("<td style='width: 200px'></td>").append(n.pname);
                    var oradio =$("<input type='radio'/>");
                    oradio.attr("rid",n.rid);
                    oradio.attr("pid",n.pid);
                    $("<tr></tr>").append(rid)
                        .append(rname).append(pid).append(pname).append(oradio).appendTo($("#test"));
                })
            }

        })

    }

)

//用户和权限的切换
$(function() {
    // 1.点击上部的li，当前li 添加current类，其余兄弟移除类
    $(".col-md-2 li").click(function() {

        // 链式编程操作
        $(this).addClass("current").siblings().removeClass("current");
        // 2.点击的同时，得到当前li 的索引号
        var index = $(this).index();
        console.log(index);
        // 3.让下部里面相应索引号的item显示，其余的item隐藏

            $(".col-md-10 .item").eq(index).show().siblings().hide();






    });
})

//视频管理和上传的切换
$(function() {
    // 1.点击上部的li，当前li 添加current类，其余兄弟移除类
    $(".col-md-2 li").click(function() {
        // 链式编程操作
        $(this).addClass("current2").siblings().removeClass("current2");
        // 2.点击的同时，得到当前li 的索引号
        var index = $(this).index();

        console.log("index");
        // 3.让下部里面相应索引号的item显示，其余的item隐藏

        console.log(index);
        // 3.让下部里面相应索引号的item3显示，其余的item2隐藏

        $(".col-md-10 .item2").eq(index).show().siblings().hide();
    });
})

//题库管理和上传的切换
$(function() {
    // 1.点击上部的li，当前li 添加current类，其余兄弟移除类
    $(".col-md-2 li").click(function() {

        // 链式编程操作
        $(this).addClass("current3").siblings().removeClass("current3");
        // 2.点击的同时，得到当前li 的索引号
        var index = $(this).index();
        console.log(index);
        // 3.让下部里面相应索引号的item显示，其余的item隐藏
        $(".col-md-10 .item3").eq(index).show().siblings().hide();
    });
})


//分页
// ajaxGetData();
// var cacheData= goTo();//读取缓存的对象
 function ajaxGetData(currentPage) {

     $("#users_table tbody").empty();
     var currentPage = currentPage || 1;
     $.ajax({
         url: "/data",
         data: {currentPage: currentPage, pageAmount: 5},
         type: "get",
         success: function (result) {

//用户信息管理分页
             var users = result.list;
             $.each(users, function (index, item) {
                 var userId = $("<td></td>").append("<input type='text' style='border: 0px' disabled='ture' value=" + item.userId + ">");
                 var userName = $("<td></td>").append("<input type='text' style='border: 0px' value=" + item.userName + ">");
                 var email = $("<td></td>").append("<input type='text' style='border: 0px' disabled='ture' value=" + item.email + ">");
                 var userCode = $("<td></td>").append("<input type='text' style='border: 0px' value=" + item.userCode + ">");
                 var userState = $("<td></td>").append("<input type='text' style='border: 0px' value=" + item.userState + ">");
                 var editBtn = $("<button style='width: 68px;height: 34px'></button>").addClass("btn btn-primary btn-sm")
                     .append($("<span></span>").addClass("glyphicon glyphicon-edit")).append("编辑");
                 var deleBtn = $("<button ></button>").addClass("btn btn-danger ")
                     .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");


                 // onclick='javascript:this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);'

                 //删除按钮
                 deleBtn.attr("email", item.email);
                 deleBtn.click(function () {

                     $.ajax({

                         url: "/deleteUser?time=" + new Date().getTime(),//解决数据缓存
                         data: "email=" + deleBtn.attr("email"),
                         type: "get",
                         success: function (result) {
                             if (result == true) {
                                 alert("成功：该用户已删除");
                                 ajaxGetData();
                             } else {
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
                     editBtn.attr("edit_userName", item.userName);
                     editBtn.attr("edit_userCode", item.userCode);
                     editBtn.attr("edit_userState", item.userState);
                     alert(editBtn.attr("edit_userName"));
                     $.ajax({
                         url: "/updateUser?" + new Date().getTime(),
                         type: "post",
                         data:
                             "userName=" + editBtn.attr("edit_userName") +
                             "&userCode=" + editBtn.attr("edit_userCode") +
                             "&userState=" + editBtn.attr("edit_userState"),
                         success: function (result) {
                             alert(result);
                         }
                     });

                 });
                 var btn = $("<td></td>").append(editBtn).append(" ").append(deleBtn);

                 $("<tr></tr>").append(userId)
                     .append(userName)
                     .append(email)
                     .append(userCode)
                     .append(userState)
                     .append(btn)
                     .appendTo("#users_table tbody");
             })


         }
     })}
ajaxGetData(1);
//绑定a点击分页
var oLi = $('.aa');
$.each(oLi, function (index, data) {

    $(this).click(function () {

        ajaxGetData(index+1);
        $(this).parent().parent().children().removeClass("active");
        $(this).parent().addClass("active");

    })
})


//以上用户信息管理分页结束


// function goTo() {
//     var cache = {};//闭包缓存池
//     return{
//         set:function(currentPage,data){
//         cache[currentPage] =data;
//         },//添加方法
//         get:function (currentPage) {
//             if (currentPage in cache){
//                 console.log("数据已缓存，无需再请求");
//             }else {
//                 ajaxGetData();
//             }
//         }//读取方法
//     }
// }}


