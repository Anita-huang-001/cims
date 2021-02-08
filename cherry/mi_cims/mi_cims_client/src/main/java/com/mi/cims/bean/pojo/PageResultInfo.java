package com.mi.cims.bean.pojo;

import java.util.Collection;
import java.util.List;

public class PageResultInfo<T> {
	
	
	
	private String templateName;
	
	private int count;
	
	

	/**
	 * 页码，从1开始
	 */
	private Integer pageNum;

	/**
	 * 页面大小
	 */
	private Integer pageSize;

	/**
	 * 总数
	 */
	private Long total;

	/**
	 * 总页数
	 */
	private Integer pages;

	/**
	 * 数据
	 */
	private List<T> list;

	// 当前页的数量
	private int size;
	// 前一页
	private int prePage;
	// 下一页
	private int nextPage;

	// 是否为第一页
	private boolean isFirstPage = false;
	// 是否为最后一页
	private boolean isLastPage = false;
	// 是否有前一页
	private boolean hasPreviousPage = false;
	// 是否有下一页
	private boolean hasNextPage = false;
	// 导航页码数
	private int navigatePages;
	// 所有导航页号
	private int[] navigatepageNums;
	// 导航条上的第一页
	private int navigateFirstPage;
	// 导航条上的最后一页
	private int navigateLastPage;

	public PageResultInfo() {
	}

	/**
	 * 包装Page对象
	 *
	 * @param list
	 */
//	public PageResultVo(List<T> list) {
//		
//		this(list, 8);
//	}

	/**
	 * 包装Page对象
	 *
	 * @param list
	 *            page结果
	 * @param navigatePages
	 *            页码数量
	 */
	public PageResultInfo(List<T> list, int navigatePages, int pageNum, int pageSize, long total, Integer pages) {
		this.list = list;
		this.pageNum = pageNum;
		this.setPageSize(pageSize);
		this.setTotal(total);
		this.pages=pages;

		if (list instanceof Collection) {
			this.navigatePages = navigatePages;
			// 计算导航页
			calcNavigatepageNums();
			// 计算前后页，第一页，最后一页
			calcPage();
			// 判断页面边界
			judgePageBoudary();
		}
	}

	/**
	 * 计算导航页
	 */
	private void calcNavigatepageNums() {
		// 当总页数小于或等于导航页码数时
		if (pages <= navigatePages) {
			navigatepageNums = new int[pages];
			for (int i = 0; i < pages; i++) {
				navigatepageNums[i] = i + 1;
			}
		} else { // 当总页数大于导航页码数时
			navigatepageNums = new int[navigatePages];
			int startNum = pageNum - navigatePages / 2;
			int endNum = pageNum + navigatePages / 2;

			if (startNum < 1) {
				startNum = 1;
				// (最前navigatePages页
				for (int i = 0; i < navigatePages; i++) {
					navigatepageNums[i] = startNum++;
				}
			} else if (endNum > pages) {
				endNum = pages;
				// 最后navigatePages页
				for (int i = navigatePages - 1; i >= 0; i--) {
					navigatepageNums[i] = endNum--;
				}
			} else {
				// 所有中间页
				for (int i = 0; i < navigatePages; i++) {
					navigatepageNums[i] = startNum++;
				}
			}
		}
	}

	/**
	 * 计算前后页，第一页，最后一页
	 */
	private void calcPage() {
		if (navigatepageNums != null && navigatepageNums.length > 0) {
			navigateFirstPage = navigatepageNums[0];
			navigateLastPage = navigatepageNums[navigatepageNums.length - 1];
			if (pageNum > 1) {
				prePage = pageNum - 1;
			}
			if (pageNum < pages) {
				nextPage = pageNum + 1;
			}
		}
	}

	/**
	 * 判定页面边界
	 */
	private void judgePageBoudary() {
		isFirstPage = pageNum == 1;
		isLastPage = pageNum == pages;
		hasPreviousPage = pageNum > 1;
		hasNextPage = pageNum < pages;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Deprecated
	// firstPage就是1, 此函数获取的是导航条上的第一页, 容易产生歧义
	public int getFirstPage() {
		return navigateFirstPage;
	}

	@Deprecated
	public void setFirstPage(int firstPage) {
		this.navigateFirstPage = firstPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	@Deprecated
	// 请用getPages()来获取最后一页, 此函数获取的是导航条上的最后一页, 容易产生歧义.
	public int getLastPage() {
		return navigateLastPage;
	}

	@Deprecated
	public void setLastPage(int lastPage) {
		this.navigateLastPage = lastPage;
	}

	public boolean isIsFirstPage() {
		return isFirstPage;
	}

	public void setIsFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public boolean isIsLastPage() {
		return isLastPage;
	}

	public void setIsLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getNavigatePages() {
		return navigatePages;
	}

	public void setNavigatePages(int navigatePages) {
		this.navigatePages = navigatePages;
	}

	public int[] getNavigatepageNums() {
		return navigatepageNums;
	}

	public void setNavigatepageNums(int[] navigatepageNums) {
		this.navigatepageNums = navigatepageNums;
	}

	public int getNavigateFirstPage() {
		return navigateFirstPage;
	}

	public int getNavigateLastPage() {
		return navigateLastPage;
	}

	public void setNavigateFirstPage(int navigateFirstPage) {
		this.navigateFirstPage = navigateFirstPage;
	}

	public void setNavigateLastPage(int navigateLastPage) {
		this.navigateLastPage = navigateLastPage;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("PageInfo{");
		sb.append(", prePage=").append(prePage);
		sb.append(", nextPage=").append(nextPage);
		sb.append(", isFirstPage=").append(isFirstPage);
		sb.append(", isLastPage=").append(isLastPage);
		sb.append(", hasPreviousPage=").append(hasPreviousPage);
		sb.append(", hasNextPage=").append(hasNextPage);
		sb.append(", navigatePages=").append(navigatePages);
		sb.append(", navigateFirstPage").append(navigateFirstPage);
		sb.append(", navigateLastPage").append(navigateLastPage);
		sb.append(", navigatepageNums=");
		if (navigatepageNums == null)
			sb.append("null");
		else {
			sb.append('[');
			for (int i = 0; i < navigatepageNums.length; ++i)
				sb.append(i == 0 ? "" : ", ").append(navigatepageNums[i]);
			sb.append(']');
		}
		sb.append('}');
		return sb.toString();
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
