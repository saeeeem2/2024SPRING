<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-md">
<jsp:include page="../layout/header.jsp"></jsp:include> <br>
<jsp:include page="../layout/nav.jsp"></jsp:include> <br><br>

	<!-- email,pwd,nick_name -->
	<form action="/member/register" method="post">
		<div class="mb-3">
		  <label for="e" class="form-label">email</label>
		  <input type="email" class="form-control" name="email" id="email" placeholder="example@000.com">
		</div>
		<div class="mb-3">
		  <label for="p" class="form-label">password</label>
		  <input type="password" class="form-control" name="pwd" id="pwd" placeholder="*******">
		</div>
		<div class="mb-3">
		  <label for="n" class="form-label">nickname</label>
		  <input type="text" class="form-control" name="nickName" id="nickName" placeholder="nickname">
		</div>
		
		<button type="submit" class="btn btn-primary">sign up</button>
	</form>

<jsp:include page="../layout/footer.jsp"></jsp:include>
</div>