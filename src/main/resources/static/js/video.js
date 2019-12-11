/*Data();
//var cacheData= goTo();//读取缓存的对象
function Data() {
    $("#video_table tbody").empty();

    $.ajax({
        url: "/queryVideoByCourseAndName",
        type: "post",
        success: function (result) {

            $.each(result, function (index, item2) {

                var videoName = $("<td></td>").append(item2.videoName);
                var courseName = $("<td></td>").append(item2.courseName);


                var v_deleBtn = $("<button ></button>").addClass("btn btn-danger ")
                    .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");


                // onclick='javascript:this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);'

                //删除按钮
                v_deleBtn.attr("delete", item2.email);
                v_deleBtn.click(function () {

                    $.ajax({

                        url: "/deletevideo",
                        data: {
                            videoId: item2.videoId
                        },
                        type: "post",
                        success: function (result) {
                            if (result.deletevideo == 1) {
                                alert("成功：该视频已删除");
                                Data();
                            } else {
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
}*/

function showvideolist(start,size) {
    $("#video_table tbody").empty();
    $.ajax({
            url: "/queryVideoByCourseAndName",
            type: "post",
            data:{
                start: start,
                size: size,
            },
        success:function (videolist) {
            $.each(videolist,function (index,video) {
                var videoName = $("<td></td>").append(video.videoName);
                var courseName = $("<td></td>").append(video.courseName);
                var v_deleBtn = $("<button ></button>").addClass("btn btn-danger ")
                    .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
                v_deleBtn.click(function () {

                    $.ajax({

                        url: "/deletevideo",
                        data: {
                            videoId: video.videoId
                        },
                        type: "post",
                        success: function (result) {
                            if (result.deletevideo == 1) {
                                alert("成功：该视频已删除");
                                showvideolist(0,5);
                            } else {
                                alert("失败：删除视频失败");
                            }
                        }
                    });
                });

                var btn = $("<td></td>").append(" ").append(v_deleBtn);
                $("<tr></tr>")
                    .append(videoName)
                    .append(courseName)

                    .append(btn)
                    .appendTo("#video_table tbody");

            });
        }
        }
    )
}

showvideolist(0,5);
layui.use(['laypage','layer'],function () {
    var laypage = layui.laypage, layer = layui.layer;
    laypage.render(
        {
            elem: 'pagen',
            count: 50,
            theme: '#FF5722',
            limit: 5,
            curr: 1,
            prev: '上一页',
            next: '下一页',
            jump: function (obj,first) {
                if(!first){
                    showvideolist(obj.curr*obj.limit - obj.limit,obj.limit);
                }

            }
        }
    );
    
});


