package com.pla.query;

import java.util.List;

public class Pager<T> {
    public static final int DEF_PGSIZE = 20;

    private int pageSize = DEF_PGSIZE;
    private int pageNo = 1;
    private int totalCount = 0;
    private List<T> list;
    private int maxIndexPages = 9;

    public Pager() {
        init();
    }

    public Pager(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;

        init();
    }

    public Pager(int totalCount, List<T> list) {
        this.totalCount = totalCount;
        this.list = list;

        init();
    }

    public Pager(int pageNo, int pageSize, int totalCount, List<T> list) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.list = list;

        init();
    }

    private void init() {
        if (totalCount <= 0) {
            totalCount = 0;
        }
        if (pageSize <= 0) {
            pageSize = DEF_PGSIZE;
        }
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if (totalCount != 0 && (pageNo - 1) * pageSize >= totalCount) {
            pageNo = totalCount / pageSize;
        }
    }

    public List<T> getList() {
        return list;
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

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        int totalPage = totalCount / pageSize;
        if (totalCount % pageSize != 0 || totalPage == 0) {
            totalPage++;
        }
        return totalPage;
    }

    public boolean getIsFirst() {
        return pageNo <= 1;
    }

    public boolean getIsLast() {
        return pageNo >= getTotalPage();
    }

    public boolean getHasNext() {
        return pageNo < getTotalPage();
    }

    public boolean getHasPrev() {
        return pageNo > 1;
    }

    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }

    public void setMaxIndexPages(int maxIndexPages) {
        this.maxIndexPages = maxIndexPages;
    }

    /**
     * 分页索引导航
     */
    public int[] getIndexPages() {
        int firstPage = getFirstIndexPage();
        int lastPage = getLastIndexPage(firstPage);

        int[] indexPages = new int[lastPage - firstPage + 1];

        for (int i = 0; i < lastPage - firstPage + 1; i++) {
            indexPages[i] = firstPage + i;
        }
        return indexPages;
    }

    /**
     * 获取分页索引首页
     */
    private int getFirstIndexPage() {
        int firstPage;
        int halfIndexPages = maxIndexPages / 2;
        int totalPage = this.getTotalPage();

        if (pageNo <= halfIndexPages) {
            firstPage = 1;
        } else if (pageNo > totalPage - halfIndexPages && totalPage - halfIndexPages > 0) {
            firstPage = totalPage - maxIndexPages + 1;
            firstPage = Math.max(1, firstPage);
        } else {
            firstPage = pageNo - halfIndexPages;
        }

        return firstPage;
    }

    /**
     * 获取分页索引尾页
     */
    private int getLastIndexPage(int firstPage) {
        int lastPage = firstPage + maxIndexPages - 1;
        int totalPage = this.getTotalPage();

        return Math.min(lastPage, totalPage);
    }

}
