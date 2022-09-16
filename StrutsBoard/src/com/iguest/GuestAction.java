package com.iguest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.util.MyUtil;
import com.util.dao.CommonDAO;
import com.util.dao.CommonDAOImpl;

public class GuestAction extends ActionSupport
implements Preparable, ModelDriven<GuestDTO>{

	private static final long serialVersionUID = 1L;

	private GuestDTO dto;
	
	/*public GuestDTO getDto() { 데이터는 ajax가 넘겨서 getter가 필요 없음 
		return dto;
	}*/

	@Override
	public GuestDTO getModel() {
		return dto;
	}

	@Override
	public void prepare() throws Exception {
		dto = new GuestDTO();
		
	}

	public String created() throws IOException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		
		int numMax = dao.getIntValue("iguest.numMax");
		
		dto.setNum(numMax+1);
		dto.setIpAddr(request.getRemoteAddr());
		
		dao.insertData("iguest.insertData", dto);
		
		return list(); //내가 직접 찾아가고자 하는 메서드를 써준다 
		//리다이랙트하면 새로고침돼서 안된다
	}
	
	public String list() throws IOException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		MyUtil myUtil = new MyUtil();
		
		int numPerPage = 5;
		int totalPage = 0;
		int totalDataCount = 0;
		
		String pageNum = request.getParameter("pageNum");
		
		int currentPage = 1;

		if(pageNum!=null && !pageNum.equals("")) {
			currentPage = Integer.parseInt(pageNum);
		}
		
		// 전체 데이터 개수 구하기
		totalDataCount = dao.getIntValue("iguest.dataCount");

		if(totalDataCount!=0) {
					totalPage = myUtil.getPageCount(numPerPage, totalDataCount);
		}
		
		// 삭제가 있을때 문제가 생길때
		if(currentPage > totalPage) {
			currentPage = totalPage;
		}
		
		Map<String, Object> hMap = new HashMap<String,Object>();
		
		int start = (currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;

		hMap.put("start", start);
		hMap.put("end", end);
		
		List<Object> lists = dao.getListData("iguest.listData",hMap);
		
		// 일련번호를 만들기 (사용자 정의)
		int listNum,n=0;
		Iterator<Object> it = lists.iterator();

		while(it.hasNext()) {
			// dto를 하나꺼내서 만들어서 vo에 listNum에 넣어준다.
			GuestDTO vo = (GuestDTO)it.next();
			listNum = totalDataCount-(start+n-1);
			vo.setListNum(listNum); // 삭제가 되도 일련번호가 만들어짐
			
			vo.setContent(vo.getContent().replaceAll("\n", "<br/>")); //빼온 김에 
			
			n++;

		}
		
		String pageIndexList = myUtil.pageIndexList(currentPage, totalPage);
		
		request.setAttribute("lists", lists);
		request.setAttribute("pageIndexList", pageIndexList);
		request.setAttribute("totalDataCount", totalDataCount);
		request.setAttribute("pageNum", currentPage);
		
		return SUCCESS; //리다이랙트하고 SUCCESS 값만 가져가면 되는 것임 
		
	}
	
	public String deleted() throws IOException {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		dao.deleteData("iguest.deleteData", num);
		
		return list(); //리다이랙트 금지 
		
	}
}
