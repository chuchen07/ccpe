function role_test(){
    $.ajax(
        {
            url:"/data",
            data:{currentPage:1,pageAmount:5},
            type:"get",
            success:function(result){
                // console.log(result)
                alert(result)
                ;   }
        })
}