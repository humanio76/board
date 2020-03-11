package action;

import java.util.HashMap;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import lombok.AllArgsConstructor;
import persistence.BoardDAO;
import upload.UploadUtil;


@AllArgsConstructor
public class WriteAction implements Action {
	
	private String path;
	

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
//		//qna_board_write.jsp에서 넘긴 값 가져오기(일반 게시판일 때 이방식, 파일 첨부할 때는 다른 방식을 사용한다. - UploadUtil)
//		String name=req.getParameter("name");
//		String password=req.getParameter("password");
//		String title=req.getParameter("title");
//		String content=req.getParameter("content");
//		String attach=req.getParameter("attach");
//		
//		//DB작업
//		BoardVO vo = new BoardVO();
//		vo.setName(name);
//		vo.setPassword(password);
//		vo.setTitle(title);
//		vo.setContent(content);
//		vo.setAttach(attach);
//		
//		BoardDAO dao = new BoardDAO();
//		int result = dao.insertArticle(vo);
		
		//request객체를 UploadUtil에 넘겨주기 
		UploadUtil uploadUtil = new UploadUtil();
		HashMap<String, String> dataMap=uploadUtil.getItem(req);
		
		
		
		//map에 들어 있는 정보를 vo에 담기
		BoardVO vo = new BoardVO();
		vo.setName(dataMap.get("name"));
		vo.setPassword(dataMap.get("password"));
		vo.setTitle(dataMap.get("title"));
		vo.setContent(dataMap.get("content"));
//		vo.setAttach(dataMap.get("attach"));
        if(dataMap.containsKey("file")) {
        	vo.setAttach(dataMap.get("file"));
	          }
		
		//DB 작업
		BoardDAO dao = new BoardDAO();
		int result=dao.insertArticle(vo);
		
		
		//result == 1 => 원래 경로 유지
		//result == 0 => path는 실패한 페이지로 변경
//		if(result==1) {
//			//path="view/qna_board_list.jsp"  factory에 경로 있어서 안써줘도 됨.
//		}else {
//			path="view/qna_board_write.jsp";					
//		}
		if(result==0) {
			path="view/qna_board_write.jsp";
		}
		
		
		return new ActionForward(path, true);
	}

}
