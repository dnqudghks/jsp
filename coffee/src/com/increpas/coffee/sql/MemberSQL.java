package com.increpas.coffee.sql;

public class MemberSQL {
	public final int SEL_LOGIN = 1001;
	public final int SEL_ID_CNT = 1002;
	public final int SEL_ID_INFO = 1003;
	
	public final int ADD_MEMB = 3001;

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
		case SEL_ID_CNT:
		buff.append("Select ");
		buff.append("	count(*) cnt ");
		buff.append("from ");
		buff.append("	member ");
		buff.append("where ");
		buff.append("	id = ? ");
		break;
		case SEL_ID_INFO:
		buff.append("SELECT ");
		buff.append("	mno, id, name, mail, tel, member.gen, ano, savename avatar, joindate ");
		buff.append("FROM ");
		buff.append("	member, avatar ");
		buff.append("WHERE ");
		buff.append("	avt = ano ");
		buff.append("	AND isshow = 'Y' ");
		buff.append("	AND id = ? ");
		break;
			
		case ADD_MEMB:
		buff.append("INSERT INTO ");
		buff.append("	member(mno, id, pw, name, mail, tel, gen, avt) ");
		buff.append("VALUES( ");
		buff.append("	(SELECT NVL(MAX(mno)+ 1, 1001) FROM member), ");
		buff.append("	?, ?, ?, ?, ?, ?, ? ");
		buff.append(") ");
		break;
		}
		return buff.toString();
	}
}
