package cn.Bookspf.model.RO;

import java.util.ArrayList;

import cn.Bookspf.model.DO.DBSort;

public class SortResponse extends Response{
	private ArrayList<DBSort> sorts;
	
	public SortResponse() {}
	public SortResponse(ArrayList<DBSort> sorts) {
		this.sorts=sorts;
	}

	public ArrayList<DBSort> getSorts() {
		return sorts;
	}

	public void setSorts(ArrayList<DBSort> sorts) {
		this.sorts = sorts;
	}
}
