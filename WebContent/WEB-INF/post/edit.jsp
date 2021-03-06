<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- include header -->
<jsp:include page="../layout/header.jsp"></jsp:include>
<!-- body -->
<div class="container">
	<h3 class="text-center text-primary">投稿編集</h3>
	<hr>
	<div class="text-primary text-center">${message }</div>
	<div class="text-danger text-center">${error }</div>
	<form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/post/edit">
		<div class="form-group">
			<input type="hidden" name="post_id"
				value=${post_user_tag.post.postId } /> <label for="title"
				class="col-sm-2 control-label">題名</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="title" placeholder="題名"
					name="post_name" required="required"
					value=${post_user_tag.post.postName }>
			</div>
		</div>
		<div class="form-group">
			<label for="content" class="col-sm-2 control-label">内容</label>
			<div class="col-sm-10">
				<textarea class="form-control" id="content" rows="10" name="content"
					required="required" placeholder="内容">${post_user_tag.post.content }</textarea>
			</div>
		</div>
		<div class="col-md-offset-2">
			<c:forEach var="tag" items="${post_user_tag.tags }">
				<a
					href="${pageContext.request.contextPath }/tag/search?tag_id=${tag.tagId }"><i
					class="glyphicon glyphicon-tag"></i>&nbsp;${tag.tagName }</a>
			</c:forEach>
		</div>
		<div class="form-group">
			<label for="tags" class="col-sm-2 control-label">タグ</label>
			<div class="col-sm-10">
				<input id="tags" name="tags" class="form-control" type="text"
					value="" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary btn-block">編集</button>
			</div>
		</div>
	</form>
</div>
<!-- include footer -->
<jsp:include page="../layout/footer.jsp"></jsp:include>