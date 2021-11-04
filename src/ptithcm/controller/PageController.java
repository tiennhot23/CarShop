package ptithcm.controller;

import java.util.List;

import org.springframework.beans.support.PagedListHolder;


public class PageController {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <E> PagedListHolder getPageList(List<E> list, int page, int maxrow){
		PagedListHolder pagedListHolder = new PagedListHolder(list);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(maxrow);
       return pagedListHolder;
   }
}
