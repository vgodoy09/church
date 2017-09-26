<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%--     pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="bower_components/metisMenu/dist/metisMenu.min.css" />
<link rel="stylesheet" type="text/css" href="dist/css/sb-admin-2.css" />
<link rel="stylesheet" type="text/css" href="bower_components/font-awesome/css/font-awesome.min.css" />
<title>Pagina Inicial</title>
</head>
<body>
	<jsp:include page="/menu/menu.jsp"></jsp:include>
	<div id="page-wrapper">
	   <div class="row">
           <div class="col-lg-12">
               <h1 class="page-header">Aplicações</h1>
           </div>
           <!-- /.col-lg-12 -->
       </div>
       <div class="row">
	       <div class="col-lg-12">
		       <div class="panel panel-default">
		       	   <div class="panel-heading">
		                Aplicações
		           </div>
				   <div class="panel-body">
				       <c:forEach items="${listarAplicacoe}" var="app">
				  	   		<a href="ControleAplicacoes?id=${app.id}" class="btn btn-primary">${app.nome}</a> 
				  	   </c:forEach>
			       </div>	    
			   </div>
	       </div>
       </div>
	</div>
	<!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="bower_components/raphael/raphael-min.js"></script>
    <script src="bower_components/morrisjs/morris.min.js"></script>
    <script src="js/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
		          
</body>
</html>