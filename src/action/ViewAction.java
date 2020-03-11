package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import lombok.AllArgsConstructor;
import persistence.BoardDAO;
import persistence.SearchVO;

@AllArgsConstructor
public class ViewAction implements Action {

	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// 페이지 나누기 후에 추가된 정보
		int page = Integer.parseInt(req.getParameter("page"));
		
		//bno 가져오기
		int bno = Integer.parseInt(req.getParameter("bno"));
		
		//검색정보 가져오기
		String criteria=req.getParameter("criteria");
		String keyword=req.getParameter("keyword");
		
		BoardDAO dao = new BoardDAO();
		//조회수 업데이트(DB 내용 변경)
		//(update)
		dao.readCountUpdate(bno);
		
		//bno에 해당하는 내용 request 객체에 담기(글쓴이,제목,내용,파일첨부, bno-화면에는 없지만 끌고 다녀야 함
		//(select)
		BoardVO vo = dao.getRow(bno);
		
		if(vo!=null) {
			path+="?page="+page;
			req.setAttribute("vo", vo);
			req.setAttribute("search", new SearchVO(criteria,keyword));
		}
				
		return new ActionForward(path, false);
	}

}
