function deleteSubject(rowLocation) {
    rowLocation=parseInt(rowLocation);
var paperName=$("#queryAllName").find("tr").eq(rowLocation+1).find("td").eq(1).find("input").val();
console.log(paperName);
    $.ajax({
        url:"/deleteSubject",
        data:{"paperName":paperName},
        type:"post",
        success:function(result){
            console.log(result);

        }
    })

}