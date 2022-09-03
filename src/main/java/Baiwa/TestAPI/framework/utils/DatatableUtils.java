package Baiwa.TestAPI.framework.utils;

import java.util.List;

import org.apache.logging.log4j.util.Strings;

import Baiwa.TestAPI.framework.constant.OpConstant;
import Baiwa.TestAPI.framework.model.DatatableFilter;
import Baiwa.TestAPI.framework.model.DatatableSort;


public class DatatableUtils {

    public static String limitForDataTable(String sql, int page, int length, List<DatatableSort> sort, List<DatatableFilter> filter, List<Object> param) {
        page = page * length;
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" SELECT * FROM ");
        if (sql.startsWith("SELECT") || sql.startsWith("select")) {
            sqlBuilder.append(" (");
            sqlBuilder.append(sql);
            sqlBuilder.append(" ");
            sqlBuilder.append(createSortFilterString(sort, filter,param));
            sqlBuilder.append(" OFFSET " + page + " ROWS FETCH NEXT " + length + " ROWS ONLY ");
            sqlBuilder.append("  ) AS TB");
        } else {
            sqlBuilder.append(sql);
            sqlBuilder.append("  TB");
            sqlBuilder.append(createSortFilterString(sort, filter,param));
            sqlBuilder.append(" OFFSET " + page + " ROWS FETCH NEXT " + length + " ROWS ONLY ");
        }
        return sqlBuilder.toString();
    }

    private static String createSortFilterString(List<DatatableSort> sort, List<DatatableFilter> filter, List<Object> param) {
        StringBuilder sqlBuilder = new StringBuilder();
        if (filter.size() != 0 && filter != null) {
            sqlBuilder.append("  WHERE");
            int i = 0;
            for (DatatableFilter item : filter) {
                if (i > 0) {
                    sqlBuilder.append(" AND ");
                }
                sqlBuilder.append(" ").append(item.getColumn()).append(" ");
                addOparator(param, sqlBuilder, item);
                i++;
            }
        }

        sqlBuilder.append("  ORDER BY");
        if (sort.size() != 0 && sort != null) {
            int i = 0;
            for (DatatableSort item : sort) {
                if (i > 0) {
                    sqlBuilder.append(" , ");
                }
                sqlBuilder.append(" ").append(item.getColumn()).append(" ").append(item.getOrder());
                i++;
            }
        } else {
            sqlBuilder.append("  1 ASC ");
        }
        return sqlBuilder.toString();
    }

    public static String countForDatatable(String sql, List<DatatableFilter> filter, List<Object> param) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select count(1) from  ");
        if (sql.startsWith("select") || sql.startsWith("SELECT")) {
            sqlBuilder.append(" (");
            sqlBuilder.append(sql);
            sqlBuilder.append(createFilterString(filter, param));
            sqlBuilder.append("  ) as tb");
        } else {
            sqlBuilder.append(sql);
            sqlBuilder.append("  tb");
            sqlBuilder.append(createFilterString(filter, param));
        }
        return sqlBuilder.toString();
    }

    private static String createFilterString(List<DatatableFilter> filter, List<Object> param) {
        StringBuilder sqlBuilder = new StringBuilder();
        if (filter.size() != 0 && filter != null) {
            sqlBuilder.append("  where");
            int i = 0;
            for (DatatableFilter item : filter) {
                if (i > 0) {
                    sqlBuilder.append(" and ");
                }
                sqlBuilder.append(" ").append(item.getColumn()).append(" ");
                addOparator(param, sqlBuilder, item);
                i++;

            }
        }
        return sqlBuilder.toString();
    }
    
    public static String customQueryWithPageCount(String sql, List<DatatableFilter> filter) {
		StringBuilder sqlBuilger = new StringBuilder();
		sqlBuilger.append("select count(1) from  ");
		sqlBuilger.append(sql);
		sqlBuilger.append(addOparator(filter));
		return sqlBuilger.toString();
	}

    private static Object addOparator(List<DatatableFilter> filter) {
    	StringBuilder sqlBuilger = new StringBuilder();
		if (filter.size() != 0 && filter != null) {
			sqlBuilger.append("  where");
			int i = 0;
			for (DatatableFilter item : filter) {
				if (i > 0) {
					sqlBuilger.append(" and ");
				}
				if (item.getOp().equals("date")) {
					sqlBuilger.append(" FORMAT (" + item.getColumn() + ", 'dd/MM/yyyy', 'en-US')").append(" ");
				} else {
					sqlBuilger.append(" ").append(item.getColumn()).append(" ");
				}
				if (item.getOp().equals("startWith")) {
					sqlBuilger.append(" LIKE ");
					sqlBuilger.append(" '%").append(item.getValue()).append("' ");
				} else if (item.getOp().equals("notthing")) {

				} else if (item.getOp().equals("null")) {
					sqlBuilger.append(" is null ");
				} else if (item.getOp().equals("endWith")) {
					sqlBuilger.append(" LIKE ");
					sqlBuilger.append(" '").append(item.getValue()).append("%' ");
				} else if (item.getOp().equals("contain")) {
					sqlBuilger.append(" LIKE ");
					sqlBuilger.append(" '%").append(item.getValue()).append("%' ");
				} else if (item.getOp().equals("date")) {
					sqlBuilger.append(" LIKE ");
					sqlBuilger.append(" '%").append(item.getValue()).append("%' ");
				}
				else if (item.getOp().equals("IS NOT NULL")) {
					
				}
				else if (item.getOp().equals("between")) {
					sqlBuilger.append(" BETWEEN ");
					sqlBuilger.append(" '" + item.getValue() + "' ");
				} else if (item.getOp().equals("in")) {
					sqlBuilger.append(" IN ");
					sqlBuilger.append(" " + item.getValue() + " ");
				}  else if (item.getOp().equals("IS NOT NULL")) {
					sqlBuilger.append(" IS NOT NULL ");
					
				}else {
					sqlBuilger.append(item.getOp()).append(" '").append(item.getValue()).append("' ");
				}
				i++;
			}
		}
		return sqlBuilger.toString();
	}

	private static void addOparator(List<Object> param, StringBuilder sqlBuilder, DatatableFilter item) {
        if (OpConstant.equals.equals(item.getOp())) {
            sqlBuilder.append(" = ? ");
            param.add(item.getValue());
        } else if (OpConstant.moreThan.equals(item.getOp())) {
            sqlBuilder.append(" > ? ");
            param.add(item.getValue());
        } else if (OpConstant.lessThan.equals(item.getOp())) {
            sqlBuilder.append(" < ? ");
            param.add(item.getValue());
        } else if (OpConstant.moreThanEquals.equals(item.getOp())) {
            sqlBuilder.append(" >= ? ");
            param.add(item.getValue());
        } else if (OpConstant.lessThanEquals.equals(item.getOp())) {
            sqlBuilder.append(" <= ? ");
            param.add(item.getValue());
        } else if (OpConstant.startWith.equals(item.getOp())) {
            sqlBuilder.append(" LIKE ");
            sqlBuilder.append(" '%").append(item.getValue()).append("' ");
        } else if (OpConstant.endWith.equals(item.getOp())) {
            sqlBuilder.append(" LIKE ");
            sqlBuilder.append(" '").append(item.getValue()).append("%' ");
        } else if (OpConstant.contain.equals(item.getOp())) {
            sqlBuilder.append(" LIKE ");
            sqlBuilder.append(" '%").append(item.getValue()).append("%' ");
        } else if (OpConstant.isNull.equals(item.getOp())) {
            sqlBuilder.append(" IS NULL ");
            sqlBuilder.append(item.getValue().trim());
        } else if (OpConstant.isNotNull.equals(item.getOp())) {
            sqlBuilder.append(" IS NOT NULL ");
        } else if (OpConstant.between.equals(item.getOp())) {
        	sqlBuilder.append(" between ");
        	sqlBuilder.append(" '").append(item.getValue()).append("' ");
        	sqlBuilder.append(" and ");
        	sqlBuilder.append(" '").append(item.getValue1()).append("' ");
        }
    }
	
	public static String customQueryWithPage(String sql, int page, int length, List<DatatableFilter> filter,
			String groupBy, String orderBy, List<DatatableSort> sort) {
		page = page * length;
		StringBuilder sqlBuilger = new StringBuilder();
		sqlBuilger.append(sql);
		sqlBuilger.append(addOparator(filter));
		if (Strings.isNotBlank(groupBy))
			sqlBuilger.append(" group by " + groupBy);

		if (Strings.isNotBlank(orderBy) && sort.size() == 0) {

			if (orderBy.indexOf("desc") != -1 || orderBy.indexOf("asc") != -1) {
				sqlBuilger.append(" order by " + orderBy);
			} else {
				sqlBuilger.append(" order by " + orderBy + " desc");
			}

		} else {
			sqlBuilger.append(" order by ");
			if (sort.size() == 0) {
				sqlBuilger.append(" 1 ");
				if (sort.size() != 0) {
					sqlBuilger.append(" , ");
				}

			}
		}
		if (sort.size() != 0 && sort != null) {
			int i = 0;
			for (DatatableSort item : sort) {
				if (i > 0)
					sqlBuilger.append(" , ");
				sqlBuilger.append(" ").append(item.getColumn()).append(" ").append(item.getOrder());
				i++;
			}
		}

		sqlBuilger.append(" LIMIT " + length + " OFFSET " + page);
		System.out.println(sqlBuilger);
		return sqlBuilger.toString();
	}
	
}
