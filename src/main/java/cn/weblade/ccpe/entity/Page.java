package cn.weblade.ccpe.entity;



import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Page<T> implements Serializable {

    public String msg;
    private Integer currentPage;  //当前页码
    private Integer pageAmount; //页面数量
    private Integer totalCount;//总共条数
    private Integer totalPage;    //总页数
    private Integer startLineNum; //起始行号
    private boolean firstPage; //是否是第一页
    private boolean lastPage; //是否是最后一页
    private boolean hasNextPage; //是否有下一页
    private boolean hasPrePage; //是否有上一页

    private List<T> list;

    /*
    *  version：1.0 chuchen07
    * 该分页类的作用是生成 作为后端->前端的中间值对象。
    *
    * 2.需求：前端发送数据请求，传来参数：a.当前页码，
    *                                 b.请求数量
    *
    *         后端接收请求，传递给此类：a.当前页码
    *                                b.请求数量
    *                                c.元数据
    *                                d.总条数
    *
    * 3.我们返回前端需要的数据，而不是让前端再去计算逻辑
    *   需要注意：~pageAmount，页面数量并不是一直都由前端决定的，比如要5条数据，
    *   但数据库只有3条，所以这里的参数pageAmount就会是3。
    *
    * */


    public void initPage(List<T> custom_list,Integer custom_currentPage,Integer custom_pageAmount,Integer custom_totalCount){
        list = custom_list;
        pageAmount = list.size();
        totalCount = custom_totalCount;
        currentPage = custom_currentPage;

        if(custom_pageAmount!=0){
            totalPage = totalCount/custom_pageAmount;
            if(totalCount%custom_pageAmount!=0){
                totalPage++;
            }
        }else{
            totalPage = 1;
        }

        if(currentPage==totalPage){
            hasNextPage=false;
            lastPage=true;
        }else{
            hasNextPage=true;
            lastPage=false;
        }


        if(currentPage==1){
            hasPrePage=false;
            firstPage=true;
        }else{
            hasPrePage=true;
            firstPage=false;
        }

        //起始行号从1开始
        startLineNum = (currentPage-1)*custom_pageAmount+1;
    }

    public Integer getStartLineNum(Integer custom_currentPage,Integer custom_pageAmount){
        int num = (custom_currentPage-1)*custom_pageAmount;
        return num;
    }

}
