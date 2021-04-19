package com.increpas.coffee.sql;

public class MemberSQL {
	public final int SEL_LOGIN = 1001;

	public String getSQL(int code) {
		StringBuffer buff= new StringBuffer();
		switch(code) {
		case SEL_LOGIN:
			buff.append("Select ");
			buff.append("count(*) cnt ");
			buff.append("from ");
			buff.append("member ");
			buff.append("where ");
			buff.append("isshow = 'Y' ");
			buff.append("And id = ? ");
			buff.append("And pw = ? ");
			break;
		}
		return buff.toString();
	}
}
