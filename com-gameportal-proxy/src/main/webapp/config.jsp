<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="/proxy/"/>
<jsp:useBean id="todayTime" class="java.util.Date"/>
<fmt:formatDate value="${todayTime}" pattern="yyyy-MM-dd" var="daytime"/> 
<%
	String basePath = "/";
%>