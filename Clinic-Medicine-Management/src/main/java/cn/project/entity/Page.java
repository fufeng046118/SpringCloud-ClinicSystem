package cn.project.entity;

import java.util.List;

public class Page<T> {
    private int pageNo;
    private int pageSize;
    private int totalCount;
    private int totalPageCount;
    private List<T> list;

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalCount(int totalCount) {
        if(totalCount > 0){
            this.totalCount = totalCount;
            this.totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize +1;
        }
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public List<T> getList() {
        return list;
    }
}
