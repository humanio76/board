package domain;

import java.util.Date;

import javax.servlet.jsp.tagext.TryCatchFinally;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor    // 자동생성자  default 생성자
@AllArgsConstructor   // 자동생성자  모든 맴버변수가 포함되어 있는 생성자
@Getter
@Setter
@ToString
public class BoardVO {
	private int bno;
	private String name;
	private String password;
	private String title;
	private String content;
	private String attach;
	private int re_ref;
	private int re_lev;
	private int re_seq;
	private int readcount;
	private Date regdate;
	
	
	
}
