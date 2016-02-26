package com.choose.Message.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * mybatis公用查询表参数类
 * 
 * @version 1.0
 * @author 周化益
 * @since 2016-02-15
 */
public class Params {
	/**查询的列*/
	private String[] columns;
	
	/**查询的实体*/
	private String tables;
	
	/**查询的条件*/
	private String whereSql;
	
	/**查询参数拼接*/
	private List<ParamCondition> paramList;
	
	/**起始位置*/
	private Integer start;
	
	/**结束位置*/
	private Integer end;
	
	/**排序字段*/
	private String sortColumn;
	
	/**升序或倒序*/
	private String sort;
	
	/**添加参数*/
	private Map<String, Object> insertMap;
	
	/**接受返回的主键ID*/
	private Long id;
	
	/**自己拼接的SQL语句*/
	private String sql;
	
	/**
	 * 返回查询的SQL语句
	 * 
	 * @author 周化益
	 * @return
	 */
	public String getSearchSql() {
		StringBuffer sb = new StringBuffer();
		sb.append("select ").append(this.columns[0]).append(" from ").append(this.tables)
		.append(" where 1 = 1 ");
		if(this.paramList != null && this.paramList.size() > 0) {
			for (ParamCondition condition : this.paramList) {
				sb.append(condition.getConnSymbol()).append(" ").append(condition.getColumn())
				.append(" ").append(condition.getJudgeSymbol()).append(" ")
				.append(condition.getValue()).append(" ")
				.append(condition.getSymbolEnd() == null ? "" : condition.getSymbolEnd());
			}
		}
		return sb.toString();
	}
	
	/**
	 * 返回添加的SQL语句
	 * 
	 * @author 周化益
	 * @return
	 */
	public String getAddhSql() {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ").append(this.tables).append("(");
		Iterator<String> it = this.insertMap.keySet().iterator();
		StringBuffer column = new StringBuffer();
		StringBuffer value = new StringBuffer();
		
		while(it.hasNext()) {
			String key = it.next();
			column.append(key).append(',');
			value.append(insertMap.get(key)).append(',');
		}
		
		sb.append(column.substring(0, column.length() - 1))
		.append(") values(").append(value.substring(0, value.length() - 1)).append(')');
		
		return sb.toString();
	}
	
	/**
	 * 返回修改的SQL语句
	 * 
	 * @author 周化益
	 * @return
	 */
	public String getUpdateSql() {
		StringBuffer sb = new StringBuffer();
		sb.append("update ").append(this.tables).append(" set ");
		Iterator<String> it = this.insertMap.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			sb.append(key).append('=').append(insertMap.get(key)).append(',');
		}
		
		sb.append(" where 1 = 1");
		
		if(this.paramList != null && this.paramList.size() > 0) {
			for (ParamCondition condition : this.paramList) {
				sb.append(condition.getConnSymbol()).append(condition.getColumn())
				.append(condition.getJudgeSymbol()).append(condition.getValue())
				.append(condition.getSymbolEnd() == null ? "" : condition.getSymbolEnd());
			}
		}
		return sb.toString();
	}
	
	/**
	 * 返回删除的SQL语句
	 * 
	 * @author 周化益
	 * @return
	 */
	public String getDeleteSql() {
		StringBuffer sb = new StringBuffer();
		sb.append("delete from ").append(this.tables).append(" where 1 = 1");
		if(this.paramList != null && this.paramList.size() > 0) {
			for (ParamCondition condition : this.paramList) {
				sb.append(condition.getConnSymbol()).append(condition.getColumn())
				.append(condition.getJudgeSymbol()).append(condition.getValue())
				.append(condition.getSymbolEnd() == null ? "" : condition.getSymbolEnd());
			}
		}
		return sb.toString();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public String getTables() {
		return tables;
	}

	public void setTables(String tables) {
		this.tables = tables;
	}

	public String getWhereSql() {
		return whereSql;
	}

	public void setWhereSql(String whereSql) {
		this.whereSql = whereSql;
	}

	public List<ParamCondition> getParamList() {
		return paramList;
	}

	public void setParamList(List<ParamCondition> paramList) {
		this.paramList = paramList;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Map<String, Object> getInsertMap() {
		return insertMap;
	}

	public void setInsertMap(Map<String, Object> insertMap) {
		this.insertMap = insertMap;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
}
