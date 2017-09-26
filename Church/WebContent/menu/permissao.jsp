<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="bower_components/metisMenu/dist/metisMenu.min.css" />
<link rel="stylesheet" type="text/css" href="dist/css/sb-admin-2.css" />
<link rel="stylesheet" type="text/css" href="bower_components/font-awesome/css/font-awesome.min.css" />
<title>Permissão</title>
</head>
<body>
	<jsp:include page="/menu/menu.jsp"></jsp:include>
	<div id="page-wrapper">
	   <div class="row">
           <div class="col-lg-12">
               <h1 class="page-header">Permissão</h1>
           </div>
           <!-- /.col-lg-12 -->
       </div>
       <div class="row">
	       <div class="col-lg-12">
		       <div class="panel panel-default">
		       	   <div class="panel-heading">
		                Permissão
		           </div>
				   <div class="panel-body">
				   		<div class="form-group">
					   		<form role="form" name="form" action="ControleUser" method="post">
						   		<div class="input-group custom-search-form">
		                            <input  type="text" name="search" class="form-control" placeholder="Search...">
			                        <div class="input-group-btn">
				                        <button class="btn btn-default" name="btSearch" type="submit">
				                        	<i class="fa fa-search"></i>
				                        </button> 
			                        </div>
		                        </div>
	                        </form>
                        </div>
						<div class="dataTable_wrapper">
							<table class="table table-striped table-bordered table-hover" id="dataTables-example">
								<thead>
									<tr>
										<th width="150px">Id do Usuario</th>
										<th>Nome do Usuario</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listarUser}" var="usu">
										<tr class="odd gradeC" onclick="location.href = 'ControleUser?proximaTela=&id=${usu.id_usuario}';">
											<td>${usu.id_usuario}</td>
											<td>${usu.nome}</td>
										</tr>
									</c:forEach>         
								</tbody>
							</table>
						</div>
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