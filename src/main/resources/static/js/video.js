ajaxGetData();
var cacheData= goTo();//读取缓存的对象
function ajaxGetData(currentPage) {
    $("#video_table tbody").empty();
    var currentPage=currentPage || 1;
    $.ajax({
        url:"/data",
        data:{currentPage:currentPage,pageAmount:5},
        type:"get",
        success:function(result){




            //用户信息管理结束
            var video = result.list;
            $.each(video,function(index,item2){

                var userName = $("<td></td>").append(item2.userName);
                var email = $("<td></td>").append(item2.email);


                var v_deleBtn=$("<button ></button>").addClass("btn btn-danger ")
                    .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");


                // onclick='javascript:this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);'

                //删除按钮
                v_deleBtn.attr("email",item2.email);
                v_deleBtn.click(function () {

                    $.ajax({

                        url:"/deleteUser?time="+new Date().getTime(),//解决数据缓存
                        data:"email="+v_deleBtn.attr("email"),
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

                var btn = $("<td></td>").append(" ").append(v_deleBtn)

                $("<tr></tr>")
                    .append(userName)
                    .append(email)

                    .append(btn)
                    .appendTo("#video_table tbody");
            })
        }
    })


//以上用户信息管理分页结束


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
