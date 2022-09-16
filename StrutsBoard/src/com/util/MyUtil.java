package com.util;

public class MyUtil {

	//전체페이지                  총 34p          3p
	public int getPageCount(int numPerPage,int dataCount) {

		int pageCount = 0;

		pageCount = dataCount / numPerPage;

		if(dataCount%numPerPage !=0) {  //나눌 때 나머지가 영으로 떨어지지 않으면
			pageCount++;  //페이지 하나 더 만들기
		}
		return pageCount;

	}

	//페이지 처리 메소드              
	public String pageIndexList(int currentPage,int totalPage,String listUrl) {

		int numPerBlock=5;  //◀이전 6 7 8 9 10 다음▶6~10까지 갯수
		int currentPageSetup;  // ◀ 
		int page; // 6 7 8 9 10 

		StringBuffer sb = new StringBuffer();  //String을 돕는 버퍼 

		if(currentPage==0 || totalPage==0) {
			return "";
		}

		//list.jsp?searchKey=subject&searchValue=aa&pageNum=2
		if (listUrl.indexOf("?")!=-1) { //물음표가 있으면 
			//list.jsp 얘만 오는 경우 
			listUrl = listUrl + "&";  //list.jsp&
		}else {
			listUrl = listUrl +"?";
		}

		// 1 2 3 4 5 다음▶
		//◀이전 6 7 8 9 10 다음▶
		//◀이전 11 12 13 14 15 다음▶
		//  14p     /    5              5
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		//자기 자신이 선택될 때 검사 
		if (currentPage % numPerBlock==0) {
			//이전페이지 5 또는 10이 currentPageSetup
			currentPageSetup = currentPageSetup - numPerBlock;
		}  //원리는 모르겠으나 이전 페이지에 들어가는 숫자 구하는 공식이었다고 한다

		//◀이전
		if (totalPage > numPerBlock && currentPageSetup > 0) {

			sb.append("<a href=\""+listUrl+"pageNum="
					+currentPageSetup+ "\">◀이전</a>&nbsp;");
			//<a href="list.jsp?pageNum=5>◀이전</a>" 이걸 만드는 것임 		
		}

		//바로가기 페이지 : 6 7 8 9 10 만들기 
		page = currentPageSetup + 1; //시작값 5p
		//     6   <=   12     &&  6   <= 5+5(10까지 찍어야 되니까) 
		while(page <=totalPage && page <=(currentPageSetup+numPerBlock)) {
			//현재 페이지 색깔 바꾸기 
			if(page == currentPage) {
				sb.append("<font color=\"Fuchsia\">"+page+ "</font>&nbsp;");
				//<font color = "Fuchsia">9</font>&nbsp; 이것 만든 거임 

			}else {
				sb.append("<a href=\""+listUrl+"pageNum="+page+
						"\">"+page+"</a>&nbsp;");
				//<a href="list.jsp?pageNum=10">7</a>&nbsp;
			}
			page++;
		}

		//다음▶
		//◀이전 6 7 8 9 10 다음▶
		//◀이전 11 12 
		//      12   -   5   >   
		if (totalPage - currentPageSetup > numPerBlock) {

			sb.append("<a href=\""+listUrl+"pageNum="+page+"\">다음▶</a>&nbsp;");
			//<a href="list.jsp?pageNum=11">다음▶</a>&nbsp;
		}
		return sb.toString();  //sb를 문자화해서 반환해라 


	}

	//자바스크립트로 페이징 처리
		public String pageIndexList(int currentPage, int totalPage) {
			
			int numPerBlock = 5;
			int currentPageSetup;
			
			int page;
			String strList = "";
			
			if(currentPage==0) {
				return "";
			}
			
			//표시할 첫페이지
			currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
			if(currentPage % numPerBlock == 0) {
				currentPageSetup = currentPageSetup - numPerBlock;
			}
			
			//◀이전
			if(totalPage>numPerBlock && currentPageSetup>0) {
				
				strList = "<a onclick='listPage(" 
						+ currentPageSetup + ");'>◀이전</a>&nbsp;";
				
			}
			
			//페이지
			page = currentPageSetup + 1;
			
			while((page<=totalPage) && (page<=currentPageSetup + numPerBlock)) {
				
				if(page==currentPage) {
					strList += "<font color='Fuchsia'>" + page + "</font>&nbsp;";
				}else {
					strList += "<a onclick='listPage(" + page + ");'>" +
								page + "</a>&nbsp;";
				}
				
				page++;
				
			}
			
			//다음▶
			if(totalPage-currentPageSetup>numPerBlock) {
				strList += "<a onclick='listPage(" + page + ");'>다음▶</a>&nbsp;";
			}
			
			return strList;
			
		}
		

	}

