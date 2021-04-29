package com.increpas.cafe.sql;

public class ReBoardSQL {
	public final int SEL_ALL_LIST 			= 	1001;
	public final int SEL_RNO_INFO 			= 	1002;
	
	public final int EDIT_REBOARD 			= 	2001;
	
	public final int ADD_REBOARD 			= 	3001;
	public final int ADD_REPLY 				= 	3002;
	public final int ADD_REBOARD_MNO 		= 	3003;
	
	public final int DEL_REBOARD 			= 	4001;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case SEL_ALL_LIST:
			buff.append("SELECT ");
			buff.append("    rno, mno, id, savename avatar, ");
			buff.append("    title, body, upno, wdate, level - 1 as step ");
			buff.append("FROM ");
			buff.append("    reboard r, member m, avatar a ");
			buff.append("WHERE ");
			buff.append("    wmno = mno ");
			buff.append("    AND avt = ano ");
			buff.append("    AND r.isshow = 'Y' ");
			buff.append("START WITH ");
			buff.append("    upno IS NULL ");
			buff.append("CONNECT BY ");
			buff.append("    PRIOR rno = upno ");
			buff.append("ORDER SIBLINGS BY "); // 정렬방식 설정절
			buff.append("    wdate DESC ");
			break;
		case SEL_RNO_INFO:
			buff.append("SELECT ");
			buff.append("    r.rno, r.title, r.body, r.upno, u.title uptitle, mno, savename avatar ");
			buff.append("FROM ");
			buff.append("    reboard r, reboard u, member m, avatar a ");
			buff.append("WHERE ");
			buff.append("    r.wmno = mno ");
			buff.append("    AND r.upno = u.rno(+) ");
			buff.append("    AND avt = ano ");
			buff.append("    AND mno = ( ");
			buff.append("                SELECT ");
			buff.append("                    mno ");
			buff.append("                FROM ");
			buff.append("                    member ");
			buff.append("                WHERE ");
			buff.append("                    id = ? ");
			buff.append("                ) ");
			buff.append("    AND r.rno = ? ");
			buff.append("    AND r.isshow = 'Y' ");
			break;
		case ADD_REBOARD:
			buff.append("INSERT INTO ");
			buff.append("    reboard(rno, wmno, title, body) ");
			buff.append("VALUES( ");
			buff.append("    (SELECT NVL(MAX(rno) + 1, 100001) FROM reboard), ");
			buff.append("    (SELECT mno FROM member WHERE id = ?), ");
			buff.append("    ?, ? ");
			buff.append(") ");
			break;
		case ADD_REBOARD_MNO:
			buff.append("INSERT INTO ");
			buff.append("    reboard(rno, wmno, title, body) ");
			buff.append("VALUES( ");
			buff.append("    (SELECT NVL(MAX(rno) + 1, 100001) FROM reboard), ");
			buff.append("    ?, ?, ? ");
			buff.append(") ");
			break;
		case ADD_REPLY:
			buff.append("INSERT INTO ");
			buff.append("    reboard(rno, wmno, title, body, upno) ");
			buff.append("VALUES( ");
			buff.append("    (SELECT NVL(MAX(rno) + 1, 100001) FROM reboard), ");
			buff.append("    (SELECT mno FROM member WHERE id = ?), ");
			buff.append("    ?, ?, ? ");
			buff.append(") ");
			break;
		case DEL_REBOARD:
			buff.append("UPDATE ");
			buff.append("    reboard ");
			buff.append("SET ");
			buff.append("    isshow = 'N' ");
			buff.append("WHERE ");
			buff.append("    rno IN ( ");
			buff.append("                SELECT ");
			buff.append("                    rno ");
			buff.append("                FROM ");
			buff.append("                    reboard ");
			buff.append("                START WITH ");
			buff.append("                    rno = ? ");
			buff.append("                CONNECT BY ");
			buff.append("                    PRIOR rno = upno ");
			buff.append("            ) ");
			break;
		}
		
		return buff.toString();
	}
}