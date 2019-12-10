ajaxGetData();
var cacheData= goTo();//读取缓存的对象
function ajaxGetData(currentPage) {
    $("#video_table tbody").empty();
    var currentPage=currentPage || 1;
    $.ajax({
        url:"/queryVideoByCourseAndName",
        type:"post",
        success:function(result){

            $.each(result,function(index,item2){

                var videoName = $("<td></td>").append(item2.videoName);
                var courseName = $("<td></td>").append(item2.courseName);


                var v_deleBtn=$("<button ></button>").addClass("btn btn-danger ")
                    .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");


                // onclick='javascript:this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);'

                //删除按钮
                v_deleBtn.attr("delete",item2.email);
                v_deleBtn.click(function () {

                    $.ajax({

                        url:"/deletevideo",
                        data:{
                            videoId: item2.videoId
                        },
                        type:"post",
                        success:function(result){
                            if(result.deletevideo==1){
                                alert("成功：该视频已删除");
                                ajaxGetData();
                            }else{
                                alert("失败：删除视频失败");
                            }
                        }
                    });
                });

                var btn = $("<td></td>").append(" ").append(v_deleBtn)

                $("<tr></tr>")
                    .append(videoName)
                    .append(courseName)

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
