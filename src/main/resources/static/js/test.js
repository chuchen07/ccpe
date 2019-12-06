ajaxGetData();
var cacheData= goTo();//读取缓存的对象
function ajaxGetData(currentPage) {

    $.ajax({
        url:"/queryAllPaperName",
        type:"get",
        success:function(result){

            //用户信息管理结束
            var test = result.list;
            $.each(test,function(index,item3){
                var paper_Name = $("<td></td>").append(item3.paper_Name);
                var t_deleBtn=$("<button ></button>").addClass("btn btn-danger ")
                    .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");

                //删除按钮
                t_deleBtn.attr("paper_Name",item3.paper_Name);
                t_deleBtn.click(function () {

                    $.ajax({

                        url:"/deleteSubject?time="+new Date().getTime(),//解决数据缓存
                        data:"paper_Name="+t_deleBtn.attr("paperName"),
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

                var btn = $("<td></td>").append(" ").append(t_deleBtn)

                $("<tr></tr>")
                    .append(paper_Name)
                    .append(btn)
                    .appendTo("#test_table tbody");
            })
        }
    })


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
