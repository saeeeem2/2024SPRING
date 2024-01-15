<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<div class="container-md">
<jsp:include page="../layout/header.jsp"></jsp:include> <br>
<jsp:include page="../layout/nav.jsp"></jsp:include> <br><br>

<h2>Board List Page</h2><br>

	<!-- search line(검색라인) -->
	<div>
		<form action="/board/list" method="get">
		  <div class="input-group mb-3">
		  <c:set value="${ph.pgvo.type}" var="typed"></c:set>
		    <select class="form-select" name="type" id="input">
		    	<option ${ph.pgvo.type eq null ? 'selected':'' }>choose...</option>
		    	<option value="t" ${typed eq 't' ? 'selected':'' }>title</option>
		    	<option value="w" ${typed eq 'w' ? 'selected':'' }>writer</option>
		    	<option value="c" ${typed eq 'c' ? 'selected':'' }>content</option>
		    	<option value="twc" ${typed eq 'twc' ? 'selected':'' }>All</option>
		    </select>
		      <input type="hidden" name="pageNo" value="1">
		      <input type="hidden" name="qty" value="${ph.pgvo.qty }">
		      <input class="form-control me-2" type="search" name="keyword" value="${ph.pgvo.keyword }" placeholder="Search" aria-label="Search">
		      <button class="btn btn-outline-success" type="submit">Search
		      	 <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
				    ${ph.totalCount }
				    <span class="visually-hidden">unread messages</span>
				  </span>
		      </button>
		   </div>
		</form>
	</div>

    
    <table class="table">
	  <thead>
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">Title</th>
	      <th scope="col">Writer</th>
	      <th scope="col">reg_date</th>
	      <th scope="col">comment_count</th>
	      <th scope="col">file_count</th>
	      <th scope="col">read_count</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:forEach items="${list }" var="bvo">
	    <tr>
	      <th scope="row">${bvo.bno }</th>
	      <td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
	      <td>${bvo.writer }</td>
	      <td>${bvo.regAt }</td>
	      <td>${bvo.cmtQty }</td>
	      <td>${bvo.hasFile }</td>
	      <td>${bvo.readCount }</td>
	    </tr>
	  	</c:forEach>
	 </tbody>
	</table>
	
	
	<!-- 페이징 라인 -->
	<nav aria-label="Page navigation example">
	  <ul class="pagination">
	  <!-- 이전 -->
	  <c:if test="${ph.prev }">
	    <li class="page-item">
	      <a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	    </c:if>
	    
	    <!-- 페이지 번호 -->
		<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
	    	<li class="page-item"><a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a></li>
	    </c:forEach>
	    
	    <!-- 다음 -->
	    <c:if test="${ph.next }">
	    <li class="page-item">
	      <a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	    </c:if>
	      <li class="page-item"><a class="page-link" href="/board/list"> 전체보기</a></li>
	  </ul>
	</nav>

	
	
<jsp:include page="../layout/footer.jsp"></jsp:include>

</div>