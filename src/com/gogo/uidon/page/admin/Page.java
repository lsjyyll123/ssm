package com.gogo.uidon.page.admin;

import org.springframework.stereotype.Component;

/**
 * paeg lei
 * @author lsj
 *
 */
@Component
public class Page {
	private int page = 1;//当前野马
	
	private int rows;//每页显示数目
	
	private int offset;//对应数据库中的额偏移量

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getOffset() {
		this.offset = (page - 1) * rows;
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
}
